package api.retry;

import java.io.IOException;
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

public class HttpUtils {
	public static String get(String url, List<NameValuePair> params) {
		String entityStr = null;
		String paramsStr = URLEncodedUtils.format(params, "utf-8");
		try {
			HttpGet get = new HttpGet(url + "?" + paramsStr);
			
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			
			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static String post(String url, List<NameValuePair> params) {
		String entityStr = null;
		try {
			HttpPost post = new HttpPost(url);
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
