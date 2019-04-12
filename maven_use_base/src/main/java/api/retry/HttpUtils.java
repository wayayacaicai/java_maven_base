package api.retry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static String get(String url, String params) {
		String entityStr = null;
		try {
			HttpGet get = new HttpGet(url + "?" + params);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static String post(String url, String mobilephone, String pwd, String regname) {
		String entityStr = null;
		try {
			HttpPost post = new HttpPost(url);
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("mobilephone", mobilephone));
			params.add(new BasicNameValuePair("pwd", pwd));
			params.add(new BasicNameValuePair("regname", regname));
			post.setEntity(new UrlEncodedFormEntity(params));

			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(post);

			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityStr;
	}
}
