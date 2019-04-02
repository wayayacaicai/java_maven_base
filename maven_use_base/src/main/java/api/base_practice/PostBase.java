package api.base_practice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostBase {
	public static void main(String args[]) throws Exception {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		HttpPost post = new HttpPost(baseUrl);
		// 1.请求体
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobilephone", "13332124555"));
		params.add(new BasicNameValuePair("pwd", "12345"));
		params.add(new BasicNameValuePair("regname", "caicai"));
		UrlEncodedFormEntity postEntity1 = new UrlEncodedFormEntity(params);

		// 2.请求体
		StringEntity postEntity2 = new StringEntity("mobilephone=13332124557&pwd=123456&regname=caicai",
				ContentType.APPLICATION_FORM_URLENCODED);

		post.setEntity(postEntity2);

		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(post);

		ProtocolVersion protocolVersion = response.getStatusLine().getProtocolVersion();
		int statusCode = response.getStatusLine().getStatusCode();
		String reasonPhrase = response.getStatusLine().getReasonPhrase();
		System.out.print(protocolVersion + " " + statusCode + " " + reasonPhrase);
		System.out.println();

		Header[] allHeaders = response.getAllHeaders();
		for (Header header : allHeaders) {
			System.out.println(header.getName() + ":" + header.getValue());
		}

		System.out.println();

		HttpEntity responseEntity = response.getEntity();
		String responseEntityStr = EntityUtils.toString(responseEntity);
		System.out.println(responseEntityStr);
	}
}
