/**
 * 
 */
package api.medium;

import java.util.ArrayList;
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
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @Desc:请求工具类
 * @author:zpp
 * @time:2019年3月31日 上午12:30:53
 */
public class HttpUtils {
	public static String get(String baseUrl, List<NameValuePair> params) {
		String entityStr = null; // 返回响应体信息
		String url_params = URLEncodedUtils.format(params, "utf-8");
		HttpGet get = new HttpGet(baseUrl + "?" + url_params);
		try {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(get);

			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static String post(String baseUrl, List<NameValuePair> params) {
		String entityStr = null; // 返回响应体信息
		HttpPost post = new HttpPost(baseUrl);
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			CloseableHttpClient httpClient = HttpClients.createDefault();
			CloseableHttpResponse response = httpClient.execute(post);

			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static void main(String[] args) {
		String baseUrl = "http://localhost:12345/futureloan/mvc/api%20/member/register";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobilephone", "13783948731"));
		params.add(new BasicNameValuePair("pwd", "123456"));
		params.add(new BasicNameValuePair("regname", "caicai"));
		// String entityStr = HttpUtils.get(baseUrl, params);
		String entityStr = HttpUtils.post(baseUrl, params);
		System.out.println(entityStr);
	}
}
