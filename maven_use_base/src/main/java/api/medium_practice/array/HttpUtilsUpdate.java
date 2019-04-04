package api.medium_practice.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

/**
 * @Desc:请求工具类（最终版）
 * @author:zpp
 * @time:2019年4月1日 下午8:50:26
 */
public class HttpUtilsUpdate {
	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param hm
	 * @return
	 */
	public static String get(String baseUrl, HashMap<String, String> hm) {
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
	public static String post(String baseUrl, HashMap<String, String> hm) {
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
}
