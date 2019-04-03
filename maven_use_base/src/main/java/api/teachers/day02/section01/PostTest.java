package api.teachers.day02.section01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class PostTest {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//发包
		//1:准备url：接口的地址
		String url = "http://localhost:8765/futureloan/mvc/api/member/register";
		//mobilephone=13888888888&pwd=123456&regname=happy
		
		//2：设置请求方法
		HttpPost post = new HttpPost(url);
		//1）设置请求体
		StringEntity entity = new StringEntity("mobilephone=13888855555&pwd=123456&regname=happy", ContentType.APPLICATION_FORM_URLENCODED);
		post.setEntity(entity);
		
		//3：准备客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//4:发包
		CloseableHttpResponse response = httpClient.execute(post);
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
