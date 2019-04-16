package api.retry;

import java.io.IOException;
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

public class HttpUtils {
	public static String get(String url, Map<String, String> paramsMap) {
		String entityStr = null;
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		// 容器转换
		if (paramsMap != null) {
			Set<Entry<String, String>> entrySet = paramsMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				paramsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		// url编码
		String paramsStr = URLEncodedUtils.format(paramsList, "utf-8");
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

	public static String post(String url, Map<String, String> paramsMap) {
		String entityStr = null;
		List<NameValuePair> paramsList = new ArrayList<NameValuePair>();
		// 容器转换
		if (paramsMap != null) {
			Set<Entry<String, String>> entrySet = paramsMap.entrySet();
			for (Entry<String, String> entry : entrySet) {
				paramsList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		try {
			HttpPost post = new HttpPost(url);
			post.setEntity(new UrlEncodedFormEntity(paramsList));

			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(post);

			HttpEntity entity = response.getEntity();
			entityStr = EntityUtils.toString(entity);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entityStr;
	}

	public static String get(ApiCaseDetail apiCaseDetail) {
		String url = apiCaseDetail.getApiInfo().getApiUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		return get(url, paramsMap);
	}

	public static String post(ApiCaseDetail apiCaseDetail) {
		String url = apiCaseDetail.getApiInfo().getApiUrl();
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		return post(url, paramsMap);
	}
}
