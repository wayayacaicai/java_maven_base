package web.base08.testcase.register;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import web.base08.base.Base_Test_Case;



/**
 * 单元测试方法的命名规则：
 * 		功能名称_success|failure_test_case_001
 * register_failure_test_case
 * 
 * 通过约束的方法命名，数据提供者可以根据方法命名确定数据从哪儿：能够确定excel的路径、和索引（名称）
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
	 */
	@Test(dataProvider="failure_data",dataProviderClass=RegisterDPClass.class)
	public void register_failure_test_case(RegisterInfo registerInfo) throws InterruptedException {
		//到登录页面
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/register.html");
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
		//获取到页面的提示:实际的提示内容
		String actualText = getTextInfo(By.className("tips"));
		//断言
		Assert.assertEquals(actualText, registerInfo.getExpectedResult());
	}
	
	

}
