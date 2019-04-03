package api.advanced.day02.section01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class GetTest {
	@DataProvider
	public Object[][] getDatas(){
		Object[][] data = {
				{"mobilephone=13888888888&pwd=123456&regname=happy","手机号码已被注册"},
				{"mobilephone=lemon&pwd=123456&regname=happy","手机号码格式不正确"},
				{"mobilephone=13888888888&pwd=12345&regname=happy","密码长度必须为6~18"},
		};
		
		//20个参数--》容器中--》
		return data;
	}
	
	@Test(dataProvider="getDatas")
	public void test_case_1(String paramsStr,String expectedResult) throws ClientProtocolException, IOException {
		String baseUrl = "http://localhost:8765/futureloan/mvc/api/member/register";
		HttpGet get = new HttpGet(baseUrl + "?" + paramsStr);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get);
		String entityStr = EntityUtils.toString(response.getEntity());
		System.out.println(entityStr);
		Assert.assertTrue(entityStr.contains(expectedResult));

	}
	
	
	public static void main(String[] args) {
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("mobilephone", "13888888888"));
		parameters.add(new BasicNameValuePair("pwd", "123456"));
		parameters.add(new BasicNameValuePair("regname", "长江二号"));
		String encodeURL = URLEncodedUtils.format(parameters, "utf-8");
		System.out.println(encodeURL);
	}

}
