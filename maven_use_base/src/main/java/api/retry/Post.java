package api.retry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Post {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] allDatas = {
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register?", "13783948731", "123456", "caicai",
						"手机号码已被注册" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register?", "13783948731", "12345", "caicai",
						"密码长度必须为6~18" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register?", "1378394873", "123456", "caicai",
						"手机号码格式不正确" },
				{ "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register?", "13783948731", "", "caicai",
						"密码不能为空" } };
		return allDatas;
	}

	@Test(dataProvider = "getDatas")
	public void post(String url, String mobilephone, String pwd, String regname, String expected) {
		String entityStr = HttpUtils.post(url, mobilephone, pwd, regname);
		Assert.assertTrue(entityStr.contains(expected));
	}
}
