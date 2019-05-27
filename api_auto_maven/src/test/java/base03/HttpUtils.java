package base03;

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

/**
 * @Desc:请求工具类(防止api的侵入，后期只用调整工具类其中的参数即可)---调整传入参数为Map类型
 * @author:zpp 
 * @time:2019年5月20日 下午5:13:52
 */
public class HttpUtils {
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
	
	public static void main(String[] args) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";

		HashMap<String, String> hMap = new HashMap<>();
		hMap.put("mobilephone", "13888888888");
		hMap.put("pwd", "123456");

		String result = get(baseUrl, hMap);
		System.out.println(result);
	}
}
