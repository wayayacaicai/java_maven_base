package api.advanced.day01;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ApiTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//发包
		//1:准备url：接口的地址
		String url = "http://localhost:8765/futureloan/mvc/api/member/register?mobilephone=13888888888&pwd=123456&regname=happy";
		//2：设置请求方法
		HttpGet get = new HttpGet(url);
		//3：准备客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//4:发包
		CloseableHttpResponse response = httpClient.execute(get);
		//4:验证响应结果是否符合预期
		//1)状态行
		StatusLine statusLine = response.getStatusLine();
		System.out.print(statusLine.getProtocolVersion().toString() + " ");
		System.out.print(statusLine.getStatusCode() + " ");
		System.out.println(statusLine.getReasonPhrase());
		
		//2)响应头
		Header[] allHeaders = response.getAllHeaders();
		for (Header header : allHeaders) {
			System.out.println(header.getName()+": "+header.getValue());
		}
		
		//3）空一行
		System.out.println();
		
		//3)响应体
		String entityStr = EntityUtils.toString(response.getEntity());
		System.out.println(entityStr);
		
	}
	

}
