package api.advanced.data_re_write;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.HttpEntity;
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

/**
 * @Desc:请求工具类
 * @author:zpp
 * @time:2019年4月1日 下午8:50:26
 */
public class HttpUtilsReflect {
	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param hm
	 * @return
	 */
	public static String get(String baseUrl, Map<String, String> hm) {
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 需要的容器类型
		if (hm != null) { // 不能为空
			Set<Entry<String, String>> entrySet = hm.entrySet(); // 通用类型转换为需要的容器类型
			for (Entry<String, String> entry : entrySet) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		String get_params = URLEncodedUtils.format(params, "utf-8"); // 请求参数拼接
		HttpGet get = new HttpGet(baseUrl + "?" + get_params); // get请求拼接
		String responseEntityStr = null; // 响应体信息定义
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建客户端
			CloseableHttpResponse response = httpClient.execute(get); // 获取响应对象

			HttpEntity responseEntity = response.getEntity(); // 获取响应体
			responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntityStr;
	}

	/**
	 * @Desc post请求方法
	 * @param baseUrl
	 * @param hm
	 * @return
	 */
	public static String post(String baseUrl, Map<String, String> hm) {
		HttpPost post = new HttpPost(baseUrl); // post请求
		String responseEntityStr = null; // 响应体信息
		List<NameValuePair> params = new ArrayList<NameValuePair>(); // 需要的容器类型
		if (hm != null) { // 不能为空
			Set<Entry<String, String>> entrySet = hm.entrySet(); // 通用类型转换为需要的容器类型
			for (Entry<String, String> entry : entrySet) {
				params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			post.setEntity(new UrlEncodedFormEntity(params)); // 设置请求体
			CloseableHttpClient httpClient = HttpClients.createDefault(); // 创建客户端
			CloseableHttpResponse response = httpClient.execute(post); // 获取响应对象

			HttpEntity responseEntity = response.getEntity(); // 获取响应体
			responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntityStr;
	}

	/**
	 * @Desc 直接传一个apiCaseDetail对象的get请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String get(ApiCaseDetail apiCaseDetail) {
		// method--1
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		return get(baseUrl, paramsMap);
		// method--2
		// List<NameValuePair> params = new ArrayList<NameValuePair>(); //
		// 需要的容器类型
		// // json字符串转换为对象
		// Map<String, String> paramsMap = (Map<String, String>)
		// JSONObject.parse(apiCaseDetail.getRequestData());
		// if (paramsMap != null) { // 不能为空
		// Set<Entry<String, String>> entrySet = paramsMap.entrySet(); //
		// 通用类型转换为需要的容器类型
		// for (Entry<String, String> entry : entrySet) {
		// params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		// }
		// }
		// String get_params = URLEncodedUtils.format(params, "utf-8"); //
		// 请求参数拼接
		// HttpGet get = new HttpGet(apiCaseDetail.getApiInfo().getUrl() + "?" +
		// get_params); // get请求拼接
		// String responseEntityStr = null; // 响应体信息定义
		// try {
		// CloseableHttpClient httpClient = HttpClients.createDefault(); //
		// 创建客户端
		// CloseableHttpResponse response = httpClient.execute(get); // 获取响应对象
		//
		// HttpEntity responseEntity = response.getEntity(); // 获取响应体
		// responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return responseEntityStr;
	}

	/**
	 * @Desc 直接传一个apiCaseDetail对象的post请求方法
	 * @param apiCaseDetail
	 * @return
	 */
	public static String post(ApiCaseDetail apiCaseDetail) {
		// method--1
		String baseUrl = apiCaseDetail.getApiInfo().getUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		return post(baseUrl, paramsMap);

		// method--2
		// HttpPost post = new HttpPost(apiCaseDetail.getApiInfo().getUrl()); //
		// post请求
		// String responseEntityStr = null; // 响应体信息
		// List<NameValuePair> params = new ArrayList<NameValuePair>(); //
		// 需要的容器类型
		// // json字符串转换为对象
		// Map<String, String> paramsMap = (Map<String, String>)
		// JSONObject.parse(apiCaseDetail.getRequestData());
		// if (paramsMap != null) { // 不能为空
		// Set<Entry<String, String>> entrySet = paramsMap.entrySet(); //
		// 通用类型转换为需要的容器类型
		// for (Entry<String, String> entry : entrySet) {
		// params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		// }
		// }
		// try {
		// post.setEntity(new UrlEncodedFormEntity(params)); // 设置请求体
		// CloseableHttpClient httpClient = HttpClients.createDefault(); //
		// 创建客户端
		// CloseableHttpResponse response = httpClient.execute(post); // 获取响应对象
		//
		// HttpEntity responseEntity = response.getEntity(); // 获取响应体
		// responseEntityStr = EntityUtils.toString(responseEntity); // 响应体字符串形式
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// return responseEntityStr;
	}
}