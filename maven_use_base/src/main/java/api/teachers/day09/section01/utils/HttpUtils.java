package api.teachers.day09.section01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
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
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
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
			
			handleReqHeader(get,apiCaseDetail);
			
//			get.setHeader("Cookie", "JSESSIONID=4F641F2D1A8066981BCB0380AC4CAFB0;");
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
//			if (apiCaseDetail.getRespHeader() !=null && apiCaseDetail.getRespHeader().length()>0) {
//				handleRespHeader(response,apiCaseDetail);
//			}
			
			//返回相应体
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	//设值header
	private static void handleReqHeader(HttpRequest request, ApiCaseDetail apiCaseDetail) {
//		get.setHeader(name, value);
		/*String reqHeader = apiCaseDetail.getReqHeader();
		List<HeaderOptObject> headerOptObjects = JSONObject.parseArray(reqHeader, HeaderOptObject.class);
		for (HeaderOptObject headerOptObject : headerOptObjects) {
			String headerName = headerOptObject.getHeaderName();
			String paramName = headerOptObject.getParamName();
			String headerValue = ParameterUtils.getGlobalData(paramName);
			//设置到请求
			request.setHeader(headerName, headerValue);
		}*/
	}

	//处理响应头部信息的提取
	private static void handleRespHeader(CloseableHttpResponse response, ApiCaseDetail apiCaseDetail) {
		/*String respHeaderStr = apiCaseDetail.getRespHeader();
		List<HeaderOptObject> headerOptObjects = JSONObject.parseArray(respHeaderStr, HeaderOptObject.class);
		for (HeaderOptObject headerOptObject : headerOptObjects) {
			//要提取的header的name
			String headerName = headerOptObject.getHeaderName();
			//要设值到全局数据池的key
			String paramName = headerOptObject.getParamName();
			
			//从response对象的响应头信息中提取出对应的header
			Header[] headers = response.getHeaders(headerName);
			for (Header header : headers) {
				System.out.println(header.getName() + "--->" + header.getValue());
				if (header.getName().equals(headerName)) {
					//设值到全局的数据池
					ParameterUtils.addGlobalData(paramName, header.getValue());
					break;
				}
			}
			
		}*/
	}

	/**
	 * post请求
	 * @param apiCaseDetail 测试用例详细信息对象
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		Map<String, String> parametersMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
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
			
			//设置header
//			if (apiCaseDetail.getReqHeader() 不为空  不为空白 不为空格) {
			handleReqHeader(post, apiCaseDetail);
//			}

			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);
//			if (apiCaseDetail.getRespHeader() !=null && apiCaseDetail.getRespHeader().length()>0) {
//				handleRespHeader(response,apiCaseDetail);
//			}
			Header[] headers = response.getAllHeaders();
			for (Header header : headers) {
				System.out.println(header.getName() + "--->" + header.getValue());
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
