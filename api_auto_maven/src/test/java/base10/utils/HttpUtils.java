package base10.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

import base10.pojo.ApiCaseDetail;
import base10.pojo.HeaderObject;

/**
 * @Desc:请求工具类(防止api的侵入，后期只用调整工具类其中的参数即可)---调整传入参数为Map类型
 * @author:zpp 
 * @time:2019年5月20日 下午5:13:52
 */
public class HttpUtils {
	//记录日志对象
	private static Logger logger = Logger.getLogger(HttpUtils.class);
	
	/**
	 * @Desc 设置请求头部信息
	 * @param request
	 * @param apiCaseDetail
	 */
	private static void setHeaderInfo(HttpRequest request,ApiCaseDetail apiCaseDetail){
		String reqHeader = apiCaseDetail.getReqHeader();
		List<HeaderObject> headerObjects = JSONObject.parseArray(reqHeader, HeaderObject.class);
		for (HeaderObject headerObject : headerObjects) {
			String headerName = headerObject.getHeaderName(); //需要设置的请求头的键
			String paramName = headerObject.getParamName(); //全局数据池中的键
			String headerValue = ParamsOperUtils.getGlobalValue(paramName); //全局数据池中的值
			request.setHeader(headerName, headerValue); //设置请求头
		}
		
	}
	
	/**
	 * @Desc 提取请求头部信息
	 * @param response
	 * @param apiCaseDetail
	 */
	private static void getHeaderInfo(CloseableHttpResponse response,ApiCaseDetail apiCaseDetail){
		String respHeader = apiCaseDetail.getRespHeader();
		List<HeaderObject> headerObjects = JSONObject.parseArray(respHeader, HeaderObject.class);
		for (HeaderObject headerObject : headerObjects) {
			String headerName = headerObject.getHeaderName(); //需要提取的请求头的键
			String paramName = headerObject.getParamName(); //需要设置全局数据池中的键
			
			Header[] headers = response.getHeaders(headerName);
			for (Header header : headers) {
				if(header.getValue().contains("HttpOnly")){
					HeaderElement[] elements = header.getElements(); //value的子元素
					HeaderElement firstElement = elements[0]; //value的子元素第一个
					//组装出有用的cookie值
					String paramValue = firstElement.getName() + "=" + firstElement.getValue();
					System.out.println(paramValue);
					//放入全局数据池
					ParamsOperUtils.addGlobalData(paramName, paramValue);
				}
			}
		}
	}
	
	
	/**
	 * @Desc get请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		Map<String, String> urlParamsMap = (Map<String, String>)JSONObject.parse(apiCaseDetail.getRequestData());		
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		return get(baseUrl, urlParamsMap,apiCaseDetail);
	}
	
	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param urlParamsMap
	 * @return
	 */
	public static String get(String baseUrl,Map<String, String> urlParamsMap,ApiCaseDetail apiCaseDetail) {
		String entityStr = null;

		try {
			List<NameValuePair> urlParams = null;
			if(urlParamsMap !=null){
				urlParams = new ArrayList<>();
				Set<Entry<String, String>> entrySet = urlParamsMap.entrySet();
				for (Entry<String, String> entry : entrySet) {
					urlParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			
			String urlParamsStr = URLEncodedUtils.format(urlParams, "utf-8");
			HttpGet get = new HttpGet(baseUrl + "?" + urlParamsStr);
			
			//设置请求头
			if(!StringUtils.isEmpty(apiCaseDetail.getReqHeader())){
				setHeaderInfo(get, apiCaseDetail);
			}
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			
			//得到响应头
			if(!StringUtils.isEmpty(apiCaseDetail.getRespHeader())){
				getHeaderInfo(response, apiCaseDetail);
			}
			
			entityStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	
	/**
	 * @Desc post请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		Map<String, String> urlParamsMap = (Map<String, String>)JSONObject.parse(apiCaseDetail.getRequestData());
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		return post(baseUrl, urlParamsMap,apiCaseDetail);
	}
	
	/**
	 * @Desc post请求方法
	 * @param baseUrl
	 * @param urlParamsMap
	 * @param apiCaseDetail 
	 * @return
	 */
	public static String post(String baseUrl, Map<String, String> urlParamsMap, ApiCaseDetail apiCaseDetail) {
		String entityStr = null;

		try {
			List<NameValuePair> urlParams = null;
			if(urlParamsMap !=null){
				urlParams = new ArrayList<>();
				Set<Entry<String, String>> entrySet = urlParamsMap.entrySet();
				for (Entry<String, String> entry : entrySet) {
					urlParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			
			HttpPost post = new HttpPost(baseUrl);
			post.setEntity(new UrlEncodedFormEntity(urlParams));
			
			//设置请求头
			if(!StringUtils.isEmpty(apiCaseDetail.getReqHeader())){
				setHeaderInfo(post, apiCaseDetail);
			}
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(post);
			
			//得到响应头
			if(!StringUtils.isEmpty(apiCaseDetail.getRespHeader())){
				getHeaderInfo(response, apiCaseDetail);
			}
			
			entityStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
	}
	
	/**
	 * @Desc 执行不同请求的方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String request(ApiCaseDetail apiCaseDetail){
		String type = apiCaseDetail.getApiInfo().getType();
		String actualResult = "";
		if("get".equalsIgnoreCase(type)){
			logger.info("发起get请求:" + apiCaseDetail.getRowNo());
			actualResult = get(apiCaseDetail);
		}else if("post".equalsIgnoreCase(type)){
			logger.info("发起post请求:" + apiCaseDetail.getRowNo());
			actualResult = post(apiCaseDetail);
		}else{
			System.out.println("请求异常！");
		}
		return actualResult;
	}
	
	
	/**
	 * @Desc 测试是否可行
	 * @param args
	 */
	public static void main(String[] args) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";

		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("mobilephone", "13888888888");
		hMap.put("pwd", "123456");

//		String result = get(baseUrl, hMap);
//		System.out.println(result);
	}
}
