package api.teachers.day09.section01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import api.teachers.day09.section01.pojo.ApiCaseDetail;
import api.teachers.day09.section01.pojo.HeaderOptObject;


/**
 * http发包工具类
 * 
 * 	[{"parameterName":"memberId","jsonPath":"$.data.id"},
 * {"parameterName":"leaveAmount","jsonPath":"$.data.leaveamount"}
 * ]
 * @author happy
 * @date 2019年3月30日
 * @desc 
 * @email
 */
public class HttpUtils {
	
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	//logger:只有我这个类用，并且一个对象好了，提供给类方法和对象方法取用

	/**
	 *  发起get请求
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		//发起请求之前，先进行参数替换
		String replacedRequestData = ParameterUtils.getReplacedStr(apiCaseDetail.getRequestData());
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(replacedRequestData);
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();

		return get(baseUrl, parametersMap,apiCaseDetail);
	}

	/**
	 * get方法
	 * @param baseUrl 基础url
	 * @param parametersMap 参数列表
	 * @param apiCaseDetail 
	 * @return
	 */
	public static String get(String baseUrl, Map<String, String> parametersMap, ApiCaseDetail apiCaseDetail) {
		System.out.println("---------------------------------------");
		try {
			//准备一个参数容器，httpclient需要
			List<NameValuePair> parameters = null;
			//如果不为空才需要遍历
			if (parametersMap != null) {
				//实例化容器
				parameters = new ArrayList<NameValuePair>();
				//map的迭代
				Set<String> keySet = parametersMap.keySet();
				for (String key : keySet) {
					String value = parametersMap.get(key);
					//添加名值对到容器
					parameters.add(new BasicNameValuePair(key, value));
				}
			}
			//参数的编码
			String encodeParamStr = URLEncodedUtils.format(parameters, "utf-8");
			//get请求实例化
			HttpGet get = new HttpGet(baseUrl + "?" + encodeParamStr);
			
			if (!StringUtils.isEmpty(apiCaseDetail.getReqHeader())) {
				handleReqHeader(get,apiCaseDetail);
			}
			
			//准备发包客户端
			CloseableHttpClient httpClient = HttpClients.createDefault();
			//获得相应
			CloseableHttpResponse response = httpClient.execute(get);
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + "--->" + header.getValue());
			}
			//拿到响应后，首先要看当前测试用例是否需要提取响应头部的信息
			//如果RespHeader为空，就不需要提取，否则需要提取
			if (!StringUtils.isEmpty(apiCaseDetail.getRespHeader() )) {
				handleRespHeader(response,apiCaseDetail);
			}
			
			//返回相应体
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 现在除了正常的发包，还要做两件事情
	 * 1：在发包前，设置请求头（必须的响应头，提现、充值接口需要设值Cookie的请求头，值为会话id）
	 * 2：在发包后，获取响应头信息(Set-Cookie：会话id)
	 * @param request
	 * @param apiCaseDetail
	 */
	/**
	 * 设值header
	 * @param request
	 * @param apiCaseDetail
	 */
	private static void handleReqHeader(HttpRequest request, ApiCaseDetail apiCaseDetail) {
		//获得要设置请求头的描述信息
		String reqHeader = apiCaseDetail.getReqHeader();
		//封装成java的对象
		List<HeaderOptObject> headerOptObjects = JSONObject.parseArray(reqHeader, HeaderOptObject.class);
		
		for (HeaderOptObject headerOptObject : headerOptObjects) {
			//要设置的请求头的名称
			String headerName = headerOptObject.getHeaderName();
			//全局数据池中的参数名
			String paramName = headerOptObject.getParamName();
			//获取全局数据池中对应参数名的值
			String headerValue = ParameterUtils.getGlobalData(paramName);
			//设置到请求
			request.setHeader(headerName, headerValue);
		}
	}

	/**
	 * 处理响应头部信息的提取
	 * @param response 响应对象
	 * @param apiCaseDetail 当前的接口测试用例信息
	 */
	private static void handleRespHeader(CloseableHttpResponse response, ApiCaseDetail apiCaseDetail) {
		//[{"headerName":"Set-Cookie","paramName":"cookie"}]
		String respHeaderStr = apiCaseDetail.getRespHeader();
		//发射封装对对象
		List<HeaderOptObject> headerOptObjects = JSONObject.parseArray(respHeaderStr, HeaderOptObject.class);
		
		for (HeaderOptObject headerOptObject : headerOptObjects) {
			//要提取的header的name
			String headerName = headerOptObject.getHeaderName(); //Set-Cookie
			//要设值到全局数据池的key
			String paramName = headerOptObject.getParamName();
			
			//得到第一个Header
			Header firstHeader = response.getFirstHeader(headerName);
			//Set-Cookie: JSESSIONID=AB5157AFEE38D9DBE7C83ACF30DBFD9A; Path=/futureloan; HttpOnly
//			JSESSIONID=AB5157AFEE38D9DBE7C83ACF30DBFD9A; Path=/futureloan; HttpOnly
			HeaderElement[] headerElements = firstHeader.getElements();
			HeaderElement firstHeaderElement = headerElements[0];
			//回头要设置到其他接口中间的请求头Cookie对应的时:携带cookie
			String cookieValue = firstHeaderElement.getName() +"=" + firstHeaderElement.getValue();
			//保存到全局数据池
			ParameterUtils.addGlobalData(paramName, cookieValue);
		}
	}

	/**
	 * post请求
	 * @param apiCaseDetail 测试用例详细信息对象
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		//发起请求前，先进行参数的替换
		String replacedRequestData = ParameterUtils.getReplacedStr(apiCaseDetail.getRequestData());
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(replacedRequestData);
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		return post(baseUrl, parametersMap,apiCaseDetail);
	}

	/**
	 * post请求
	 * @param url 接口地址
	 * @param parametersMap 参数的map
	 * @param apiCaseDetail 
	 * @return
	 */
	public static String post(String url, Map<String, String> parametersMap, ApiCaseDetail apiCaseDetail) {
		System.out.println(apiCaseDetail.getCaseId() + "##########################################################");
		HttpPost post = new HttpPost(url);
		try {
			List<NameValuePair> parameters = null;
			if (parametersMap != null) {
				parameters = new ArrayList<NameValuePair>();
				Set<String> keySet = parametersMap.keySet();
				for (String key : keySet) {
					String value = parametersMap.get(key);
					parameters.add(new BasicNameValuePair(key, value));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(parameters));
			
			if (!StringUtils.isEmpty(apiCaseDetail.getReqHeader())) {
				handleReqHeader(post,apiCaseDetail);
			}

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
			
			if (!StringUtils.isEmpty(apiCaseDetail.getRespHeader() )) {
				handleRespHeader(response,apiCaseDetail);
			}
			
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			//打印堆栈信息
//			e.printStackTrace();
			//记录一个日志：error、warn、info、debug
			//报异常
			logger.error("调用post方法出现异常:"+e.getMessage());
			//主观行为
			//应用层面
		}
		return "";
	}

	public static String delete(String baseUrl, Map<String, String> parametersMap) {
		//TODO
		return "";
	}

	public static String put(String baseUrl, Map<String, String> parametersMap) {
		//TODO
		return "";
	}

	/**
	 * 接口中转站--》分发
	 * 转发不同方法的接口
	 * @param apiCaseDetail
	 * @return
	 */
	public static String request(ApiCaseDetail apiCaseDetail) {
		String type = apiCaseDetail.getApiInfo().getType();
		String entityStr = "";
		if ("get".equalsIgnoreCase(type)) {
			//把接口的详细信息记录下来
			logger.info("发起get请求："+apiCaseDetail.getApiInfo().getApiName());
			entityStr = get(apiCaseDetail);
		}else if("post".equalsIgnoreCase(type)){
			logger.info("发起post请求："+apiCaseDetail.getApiInfo().getApiName());
			entityStr = post(apiCaseDetail);
		}
		return entityStr;
	}
	
	/**
	 * 1:一般是需要我们在请求头、参数中间加入鉴权的参数--》（找接口的设计人员）
	 * 		cookie：一段文本--》记录本地
	 * 		token：令牌
	 * 		sign：签名（token + 用户id + 随机数字 + 时间戳）
	 * 			非对称加密
	 * 				一把私钥放到服务器：解密
	 * 				一把公钥发给客户端：加密
	 * @param args
	 */
	public static void main(String[] args) {
		//登录的接口
		String baseUrl = "http://127.0.0.1:8765/futureloan/mvc/api/member/login";
		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("mobilephone", "13999888819");
		parametersMap.put("pwd", "123456");
		String result = get(baseUrl, parametersMap,null);
		System.out.println(result);
		
		//充值的接口
//		String baseUrl = "http://127.0.0.1:8765/futureloan/mvc/api/member/recharge";
//		Map<String, String> parametersMap = new HashMap<String, String>();
//		//{"mobilephone":"13666666665","amount":"100"}
//		parametersMap.put("mobilephone", "13999888819");
//		parametersMap.put("amount", "100");
//		String result = get(baseUrl, parametersMap);
//		System.out.println(result);
		
		/**
		 1:当用户登录之后，会返回一个sessionId（会话的id）
		 2：sessionId设置是在响应头部Set-Cookie
		 3：其他的需要鉴权的接口需要在请求头部信息中设置Cookie
		 
		 HttpResponseProxy{HTTP/1.1 200 OK 
		 [Server: Apache-Coyote/1.1, 
		 Set-Cookie: JSESSIONID=4F641F2D1A8066981BCB0380AC4CAFB0; Path=/futureloan; HttpOnly, 
		 Set-Cookie: rememberMe=deleteMe; Path=/futureloan; Max-Age=0; Expires=Mon, 22-Apr-2019 12:32:56 GMT, 
		 Content-Type: application/json;charset=UTF-8, 
		 Transfer-Encoding: chunked, 
		 Date: Tue, 23 Apr 2019 12:32:56 GMT] 
		 ResponseEntityProxy{[Content-Type: application/json;charset=UTF-8,Chunked: true]}}
{"status":1,"code":"10001","data":null,"msg":"登录成功"}
		 */
		
		//把登录中间的Set-Cookie的响应头的信息保存到全局数据池中间，key为Cookie --》会保存响应头
		//在访问接口时，如提现、充值，先从全局数据池把Cookie的值提取出去，然后设置到接口的请求头部信息中  -》》需要设置请求头
	}
}
