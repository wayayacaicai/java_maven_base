package web.base09.testcase.regsiter;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import web.base09.base.Base_Test_Case;









/**
 * @author happy
 * @date 2019年5月21日
 * @desc 注册功能正向测试用例
 * @email
 *  确定好这个页面，然后我能够拿到这个页面的所有元素
 *  然后告诉我某个元素名称，我能拿到这个元素的所有信息
 *  List
 *  Map:A树  苹果A
 */
public class Register_Success_Test_Case extends Base_Test_Case {
	
	private static final String REGISTER_PAGE_NAME = "注册页面";
	private static final String LOGIN_PAGE_NAME = "登录页面";
	/**
	 * 注册成功后，跳转到登录页面
	 * 	断言：
	 * 		1：url
	 * 		2: title
	 * 		3：页面表现
	 * @param mobilePhone
	 * @param pwd
	 * @param confirmPwd
	 * @param validateCode
	 * @param expectedText
	 * @throws InterruptedException
	 */
	@Test(dataProvider = "success_data", dataProviderClass = RegisterDPClass.class)
	public void register_success_test_case(RegisterInfo registerInfo) throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type("手机号码输入框", registerInfo.getMobilePhone());
		//输入密码
		type("密码输入框", registerInfo.getPwd());
		//输入重复密码
		type("重复密码输入框", registerInfo.getConfirmPwd());
		//输入验证码
		type("验证码输入框", registerInfo.getVerifycode());
		//单击注册按钮
		click("注册按钮");
		//断言:页面上面出现登录元素
		WebElement loginBtn = getElement("Login","登录按钮");
		Assert.assertNotNull(loginBtn);
	}

}
