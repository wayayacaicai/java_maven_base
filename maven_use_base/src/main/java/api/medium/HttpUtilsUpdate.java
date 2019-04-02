/**
 * 
 */
package api.medium;

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
 * @Desc:请求工具类
 * @author:zpp
 * @time:2019年3月31日 上午12:30:53
 */
public class HttpUtilsUpdate {
	public static String get(String baseUrl, HashMap<String, String> params) {
		// 返回响应体信息
		String entityStr = null;
		// 需要的容器类型
		List<NameValuePair> params_need = new ArrayList<NameValuePair>();
		if (params != null) {
			// 容器转换
			Set<Entry<String, String>> entrySet = params.entrySet();
			for (Entry<String, String> entry : entrySet) {
				params_need.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// 参数编码
		String url_params = URLEncodedUtils.format(params_need, "utf-8");
		HttpGet get = new HttpGet(baseUrl + "?" + url_params);
		try {
			// 创建客户端
			CloseableHttpClient httpClient = HttpClients.createDefault();
			// 得到响应
			CloseableHttpResponse response = httpClient.execute(get);

			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static String post(String baseUrl, HashMap<String, String> params) {
		String entityStr = null; // 返回响应体信息
		HttpPost post = new HttpPost(baseUrl);
		try {
			// 需要的容器类型
			List<NameValuePair> params_need = new ArrayList<NameValuePair>();
			if (params != null) {
				// 容器转换
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					params_need.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(params_need));
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
		HashMap<String, String> params = new HashMap<>();
		params.put("mobilephone", "13783948731");
		params.put("pwd", "123456");
		params.put("regname", "caicai");
		// String entityStr = HttpUtilsUpdate.get(baseUrl, params);
		String entityStr = HttpUtilsUpdate.post(baseUrl, params);
		System.out.println(entityStr);
	}
}
