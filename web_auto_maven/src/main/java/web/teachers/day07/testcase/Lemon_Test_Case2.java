package web.teachers.day07.testcase;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import web.teachers.day07.base.Base_Test_Case;



/**
	 手机号	         密码		重复密码	验证码		期望值
											用户名不能为空
	lemon									非法的手机号
	13888888888					LM19		密码不能为空
	13888888888	12345			LM19		密码长度至少为6位
	13888888888	123456			LM19		重复密码不能为空
	13888888888	123456	1234567	LM19    	密码不一致
 * @author happy
 * @date 2019年5月21日
 * @desc 
 * @email
 */
public class Lemon_Test_Case2 extends Base_Test_Case {
	/**
	 * 6个方法代码中可以优化的地方
	 * 1：代码冗余
	 * @throws InterruptedException
	 */
	@Test
	public void test_case_01() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "用户名不能为空";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void test_case_02() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), "lemon");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "非法的手机号";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void test_case_03() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), "13888888888");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "密码不能为空";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void test_case_04() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), "13888888888");
		//输入密码
		type(By.id("password"), "12345");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "密码长度至少为6位";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void test_case_05() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), "13888888888");
		//输入密码
		type(By.id("password"), "123456");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "重复密码不能为空";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}
	
	@Test
	public void test_case_06() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
		//输入手机号
		type(By.id("mobilephone"), "13888888888");
		//输入密码
		type(By.id("password"), "123456");
		//输入重复密码
		type(By.id("pwdconfirm"), "1234567");
		//单击注册按钮
		click(By.id("signup-button"));
		//获取到页面的提示:实际的提示内容
		String actualText = getText(By.className("tips"));
		//期望的提示内容
		String expectedText = "密码不一致";
		//断言
		Assert.assertEquals(actualText, expectedText);
	}

}
