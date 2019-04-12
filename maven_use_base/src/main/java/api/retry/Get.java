package api.retry;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] allDatas = {
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register",
						"mobilephone=13783948731&pwd=123456&regname=caicai", "手机号码已被注册" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register",
						"mobilephone=13783948731&pwd=12345&regname=caicai", "密码长度必须为6~18" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register",
						"mobilephone=1378394873&pwd=123456&regname=caicai", "手机号码格式不正确" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register",
						"mobilephone=1378394873&pwd=&regname=caicai", "密码不能为空" } };
		return allDatas;
	}

	@Test(dataProvider = "getDatas")
	public void get(String url, String params, String expected) {
		String entityStr = HttpUtils.get(url, params);
		Assert.assertTrue(entityStr.contains(expected));
	}

	public static void main(String args[]) {
		String url = "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register?"
				+ "mobilephone=13783948731&pwd=&regname=caicai";
		String expected = "密码长度必须为6~18";
		try {
			HttpGet get = new HttpGet(url);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();
			String entityStr = EntityUtils.toString(entity);
			System.out.println(entityStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
