/**
 * 
 */
package api.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
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
import org.testng.annotations.Test;

/**
 * @Desc:post请求
 * @author:zpp
 * @time:2019年3月29日 下午10:09:18
 */
public class BasePost {
	/**
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @Desc
	 */
	@Test
	public void testPost1() throws ClientProtocolException, IOException {
		// 1.接口地址
		String uri = "http://localhost:12345/futureloan/mvc/api%20/member/register";

		// 2.设置请求方法
		HttpPost post = new HttpPost(uri);

		// 设置请求体1
		
//		 List<NameValuePair> aList = new ArrayList<NameValuePair>();
//		 aList.add(new BasicNameValuePair("mobilephone", "13332124555"));
//		 aList.add(new BasicNameValuePair("pwd", "123456")); 
//		 aList.add(new BasicNameValuePair("regname", "caicai2")); 
//		 UrlEncodedFormEntity entity_post = new UrlEncodedFormEntity(aList);
//		 post.setEntity(entity_post);
		 

		// 设置请求体2
		StringEntity stringEntity = new StringEntity("mobilephone=18352738717&pwd=123456&regname=caicai3",
				ContentType.APPLICATION_FORM_URLENCODED);
		post.setEntity(stringEntity);

		// 3.设置客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();

		// 4.发请求
		CloseableHttpResponse response = httpClient.execute(post);

		// 5.验证响应结果是否符合预期
		// 状态行
		StatusLine statusLine = response.getStatusLine();
		System.out.print(statusLine.getProtocolVersion().toString() + " ");
		System.out.print(statusLine.getStatusCode() + " ");
		System.out.println(statusLine.getReasonPhrase());

		// 响应头
		Header[] allHeaders = response.getAllHeaders();
		for (Header header : allHeaders) {
			System.out.println(header.getName() + ":" + header.getValue());
		}

		// 空行
		System.out.println();

		// 响应体
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity);
		System.out.println(string);
	}
}
