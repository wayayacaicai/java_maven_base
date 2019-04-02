package api.medium_practice;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:初步封装，用http工具类调用
 * @author:zpp
 * @time:2019年4月1日 下午9:05:17
 */
public class MediumGet11 {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "13783948731", "123456", "登录成功" }, { "lemon", "123456", "用户名或密码错误" },
				{ "13783948731", "12345", "用户名或密码错误" }, };
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public static void get(String mobilephone, String pwd, String expected) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/login";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("pwd", pwd));
		String actualResponseBody = HttpUtils.get(baseUrl, params);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}

	@Test(dataProvider = "getDatas")
	public static void post(String mobilephone, String pwd, String expected) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/login";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("pwd", pwd));
		String actualResponseBody = HttpUtils.post(baseUrl, params);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}
}
