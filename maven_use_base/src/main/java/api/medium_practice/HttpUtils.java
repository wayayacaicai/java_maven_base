package api.medium_practice;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * @Desc:请求工具类
 * @author:zpp
 * @time:2019年4月1日 下午8:42:03
 */
public class HttpUtils {
	/**
	 * @Desc get请求方法
	 * @param baseUrl
	 * @param params
	 * @return
	 */
	public static String get(String baseUrl, List<NameValuePair> params) {
		String get_params = URLEncodedUtils.format(params, "utf-8"); // 请求参数拼接
		HttpGet get = new HttpGet(baseUrl + "?" + get_params); // get请求
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
	 * @param params
	 * @return
	 */
	public static String post(String baseUrl, List<NameValuePair> params) {
		HttpPost post = new HttpPost(baseUrl); // post请求
		String responseEntityStr = null; // 响应体信息
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
