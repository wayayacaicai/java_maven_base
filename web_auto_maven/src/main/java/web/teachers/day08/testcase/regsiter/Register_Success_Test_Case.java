package web.teachers.day08.testcase.regsiter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import web.teachers.day08.base.Base_Test_Case;

/**
 * @author happy
 * @date 2019年5月21日
 * @desc 注册功能正向测试用例
 * @email
 */
public class Register_Success_Test_Case extends Base_Test_Case {
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
	public void register_success_test_case(RegisterInfo registerInfo)
			throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), registerInfo.getMobilePhone());
		//输入密码
		type(By.id("password"), registerInfo.getPwd());
		//输入重复密码
		type(By.id("pwdconfirm"), registerInfo.getConfirmPwd());
		//输入验证码
		type(By.id("verifycode"), registerInfo.getVerifycode());
		//单击注册按钮
		click(By.id("signup-button"));
		//断言:页面上面出现登录元素
		WebElement loginBtn = getElement(By.id("login"));
		Assert.assertNotNull(loginBtn);
	}

}
