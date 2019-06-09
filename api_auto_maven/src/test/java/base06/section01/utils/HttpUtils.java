package base06.section01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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

import com.alibaba.fastjson.JSONObject;

import base06.section01.pojo.ApiCaseDetail;

/**
 * @Desc:请求工具类(防止api的侵入，后期只用调整工具类其中的参数即可)---调整传入参数为Map类型
 * @author:zpp 
 * @time:2019年5月20日 下午5:13:52
 */
public class HttpUtils {
	
	/**
	 * @Desc get请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		Map<String, String> urlParamsMap = (Map<String, String>)JSONObject.parse(apiCaseDetail.getRequestData());		
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		return get(baseUrl, urlParamsMap);
	}
	
	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param urlParamsMap
	 * @return
	 */
	public static String get(String baseUrl,Map<String, String> urlParamsMap) {
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
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			
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
		return post(baseUrl, urlParamsMap);
	}
	
	/**
	 * @Desc post请求方法
	 * @param baseUrl
	 * @param urlParamsMap
	 * @return
	 */
	public static String post(String baseUrl, Map<String, String> urlParamsMap) {
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
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(post);
			
			entityStr = EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
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

		String result = get(baseUrl, hMap);
		System.out.println(result);
	}
}
