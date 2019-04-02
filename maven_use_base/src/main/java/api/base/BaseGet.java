/**
 * 
 */
package api.base;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Desc:get请求
 * @author:zpp 
 * @time:2019年3月29日 下午9:32:18
 */
public class BaseGet {

	/**
	 * @Desc
	 * @param args
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	@Test
	public void testGet1() throws ClientProtocolException, IOException {
		//1.接口地址
		String uri = "http://localhost:12345/futureloan/mvc/api%20/member/register?"
				+ "mobilephone=13783948731&pwd=123456&regname=caicai";
		
		//2.设置请求方法
		HttpGet get = new HttpGet(uri);
		
		//3.准备客户端
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		//4.发请求
		CloseableHttpResponse response = httpClient.execute(get);
		
		//5.验证响应结果是否符合预期
		//状态行
		StatusLine statusLine = response.getStatusLine();
		System.out.print(statusLine.getProtocolVersion().toString() + " ");
		System.out.print(statusLine.getStatusCode() + " ");
		System.out.println(statusLine.getReasonPhrase());
		
		//响应头
		Header[] allHeaders = response.getAllHeaders();
		for (Header header : allHeaders) {
			System.out.println(header.getName() + ":" + header.getValue());
		}
		
		//空行
		System.out.println();
		
		//响应体
		HttpEntity entity = response.getEntity();
		String string = EntityUtils.toString(entity);
		System.out.println(string);
		
		//验证
		Assert.assertTrue(string.contains("手机号码已被注册"));
		
//		//关闭资源
//		EntityUtils.consume(entity);
//		
//		response.close();
//		httpClient.close();
	}

}
