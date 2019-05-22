package web.teachers.day07.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import web.teachers.day07.base.Base_Test_Case;



/**
 * @author happy
 * @date 2019年5月21日
 * @desc 
 * @email
 */
public class Lemon_Test_Case extends Base_Test_Case {
	/**
	 * @param mobilePhone
	 * @param pwd
	 * @param confirmPwd
	 * @param validateCode
	 * @param expectedText
	 * @throws InterruptedException
	 */
	@Test(dataProvider="data",dataProviderClass=DataProviderClass.class)
	public void register_failure_test_case(String mobilePhone,String pwd,String confirmPwd,String validateCode,String expectedText) throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), mobilePhone);
		//输入密码
		type(By.id("password"), pwd);
		//输入重复密码
		type(By.id("pwdconfirm"), confirmPwd);
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//断言
		Assert.assertEquals(actualText, expectedText);
	}

}
