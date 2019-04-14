package api.retry;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Get {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] allDatas = {
				{"13783948731","123456","caicai", "手机号码已被注册" },
				{"13783948731","12345","caicai", "密码长度必须为6~18" },
				{"1378394873","123456","caicai", "手机号码格式不正确" },
				{"13783948731","","caicai", "密码不能为空" } };
		return allDatas;
	}

	@Test(dataProvider = "getDatas")
	public void get(String mobilephone,String pwd,String regname, String expected) {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api%20/member/register";
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("mobilephone", mobilephone));
		params.add(new BasicNameValuePair("pwd", pwd));
		params.add(new BasicNameValuePair("regname", regname));
		String entityStr = HttpUtils.get(baseUrl,params);
		System.out.println(entityStr);
		Assert.assertTrue(entityStr.contains(expected));
	}

	public static void main(String args[]) {
		
	}
}
