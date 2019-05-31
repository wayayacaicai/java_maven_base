package web.teachers.day11.testcase.regsiter;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import web.teachers.day11.base.Base_Test_Case;
import web.teachers.day11.testcase.login.LoginPage;



/**
 * @author happy
 * @date 2019年5月21日
 * @desc 
 * @email
 */
public class RegisterPage extends Base_Test_Case {
	
	private static Logger logger = Logger.getLogger(RegisterInfo.class);
	/**
	 *  //元素的定位信息是有结构化
	 *  一个项目有很多的模块，一个模块有很多功能、一个功能有很多页面，一个页面有很多元素、元素又与很多定位信息 --》数据载体
	 */
	@Test(dataProvider = "failure_data", dataProviderClass = RegisterDPClass.class)
	public void register_failure_test_case(RegisterInfo registerInfo) throws InterruptedException {
		//到登录页面
		to("register_page"); //TODO 1：页面的url可以独立出去--》解耦--》properties文件
		//输入手机号
		//往登录页面的的手机号输入框输入内容
		type("手机号码输入框", registerInfo.getMobilePhone());
		//输入密码
		type("密码输入框", registerInfo.getPwd());
		//输入重复密码
		type("重复密码输入框", registerInfo.getConfirmPwd());
		//输入验证码
		type("验证码输入框", registerInfo.getVerifycode());
		//单击注册按钮
		click("注册按钮");
		//断言提示信息元素为期望的文本
		assertTextPresentCurrentPage("提示信息元素", registerInfo.getExpectedResult());
	}

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
		to("register_page");
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
		assertElementPresentInPage(LoginPage.class, "登录按钮");
	}
}
