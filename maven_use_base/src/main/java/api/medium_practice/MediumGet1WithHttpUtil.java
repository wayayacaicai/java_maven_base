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
 * @time:2019年4月1日 下午9:11:07
 */
public class MediumGet1WithHttpUtil {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "13783948731", "123456", "caicai", "手机号码已被注册" },
				{ "lemon", "123456", "caicai", "手机号码格式不正确" }, { "13783948731", "12345", "caicai", "密码长度必须为6~18" }, };
		return datas;
	}

	// @Test(dataProvider = "getDatas")
	public static void get(String mobilephone, String pwd, String regname, String expected) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("pwd", pwd));
		params.add(new BasicNameValuePair("regname", regname));
		String actualResponseBody = HttpUtils.get(baseUrl, params);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}

	@Test(dataProvider = "getDatas")
	public static void post(String mobilephone, String pwd, String regname, String expected) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("pwd", pwd));
		params.add(new BasicNameValuePair("regname", regname));
		String actualResponseBody = HttpUtils.post(baseUrl, params);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}
}
