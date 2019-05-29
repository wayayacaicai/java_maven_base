package web.teachers.day10.testcase.regsiter;

import org.testng.Assert;
import org.testng.annotations.Test;

import web.teachers.day10.base.Base_Test_Case;



/**
 * @author happy
 * @date 2019年5月21日
 * @desc 
 * @email
 */
public class Register_Failure_Test_Case extends Base_Test_Case {
	/**
	 * @param mobilePhone
	 * @param pwd
	 * @param confirmPwd
	 * @param validateCode
	 * @param expectedText
	 * @throws InterruptedException
	 * 
	 *  //元素的定位信息是有结构化
	 *  一个项目有很多的模块，一个模块有很多功能、一个功能有很多页面，一个页面有很多元素、元素又与很多定位信息 --》数据载体
	 */
	@Test(dataProvider="failure_data",dataProviderClass=RegisterDPClass.class)
	public void register_failure_test_case(RegisterInfo registerInfo) throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/register.html"); //TODO 1：页面的url可以独立出去--》解耦--》properties文件
		//输入手机号
		//往登录页面的的手机号输入框输入内容
		type("手机号码输入框",registerInfo.getMobilePhone());
		//输入密码
		type("密码输入框",registerInfo.getPwd());
		//输入重复密码
		type("重复密码输入框",registerInfo.getConfirmPwd());
		//输入验证码
		type("验证码输入框",registerInfo.getVerifycode());
		//单击注册按钮
		click("注册按钮");
		//获取到页面的提示:实际的提示内容
		String actualText = getText("提示信息元素");
		//断言
		Assert.assertEquals(actualText, registerInfo.getExpectedResult());
	}
	
	

}
