package base02.section01;

import java.util.ArrayList;
import java.util.List;

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
 * @time:2019年5月20日 下午5:13:52
 */
public class HttpUtils {
	public static String get(String baseUrl, List<NameValuePair> urlParams) {
		String entityStr = null;

		try {
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

	public static String post(String baseUrl, List<NameValuePair> urlParams) {
		String entityStr = null;

		try {
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
	
	public static void main(String[] args) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("mobilephone", "13888888888"));
		parameters.add(new BasicNameValuePair("pwd", "123456"));

		String result = post(baseUrl, parameters);
		System.out.println(result);
	}
}
