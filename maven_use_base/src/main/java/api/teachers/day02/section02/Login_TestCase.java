package api.teachers.day02.section02;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 不同的接口的请求的参数名不同--》调和 注册：3个 mobilephone、pwd、regname
 * {"mobilephone":"13888888888","pwd":"123456","regname":"happy"}
 * 登录：2个mobilephone、pwd 充值：2个 id，amount 新建项目：title、loanrate。。。。。10个 调和，通用的处理 格式
 * 
 * @author happy
 * @date 2019年4月2日
 * @desc
 * @email
 */
public class Login_TestCase {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] data = { { "13888888888", "123456", "登录成功" }, { "lemon", "123456", "用户名或密码错误" },
				{ "13888888888", "12345", "用户名或密码错误" }, };

		// 20个参数--》容器中--》
		return data;
	}

	@Test(dataProvider = "getDatas")
	public void test_case_1(String mobilephone, String pwd, String expectedResult)
			throws ClientProtocolException, IOException {
		String baseUrl = "http://localhost:8765/futureloan/mvc/api/member/login";
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("mobilephone", mobilephone);
		parameters.put("pwd", pwd);
		String entityStr = HttpUtils.get(baseUrl, parameters);
		Assert.assertTrue(entityStr.contains(expectedResult));
	}
}
