package api.base_practice;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class GetBase {
	public static void main(String args[]) throws Exception {
		String url = "http://120.78.128.25:8080/futureloan/mvc/api/member/register?"
				+ "mobilephone=13783948731&pwd=123456&regname=caicai";
		HttpGet get = new HttpGet(url);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get);

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
