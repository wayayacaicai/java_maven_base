package web.teachers.day07.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import web.teachers.day07.base.Base_Test_Case;


/**
 * @author happy
 * @date 2019年5月21日
 * @desc 
 * @email
 */
public class Lemon_Test_Case3 extends Base_Test_Case {
	/**
	 * 	 
	 手机号	         密码		重复密码	验证码		期望值
											用户名不能为空
	lemon									非法的手机号
	13888888888					LM19		密码不能为空
	13888888888	12345			LM19		密码长度至少为6位
	13888888888	123456			LM19		重复密码不能为空
	13888888888	123456	1234567	LM19    	密码不一致
	 * @return
	 */
	@DataProvider(name="data")
	public Object[][] registerData(){
		return new Object[][]{
			{"","","","","用户名不能为空"},
			{"lemon","","","","非法的手机号"},
			{"柠檬班","","","","非法的手机号"},
			{"13888888888","","","LM19","密码不能为空"},
			{"13888888888","12345","","LM19","密码长度至少为6位"},
			{"13888888888","123456","","LM19","重复密码不能为空"},
			{"13888888888","123456","1234567","LM19","密码不一致"},
			{"13888888888","123456","123456","","验证码不能为空"},
		};
	}
	
	@Test(dataProvider="data")
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
