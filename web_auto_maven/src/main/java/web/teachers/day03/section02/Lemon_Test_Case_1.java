package web.teachers.day03.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lemon_Test_Case_1 extends Base_Test_Case {
	//http://test.lemonban.com/lmcanon_web_auto/mng/register.html?t=20181011
	@Test
	public void test_case_01() throws InterruptedException {
		to("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getDriver();
		
		//执行测试用例：输入手机号和密码，点击登录按钮
		driver.findElement(By.id("mobilephone")).sendKeys("13555555555");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		//1:硬性等待、线程等待、线程休眠、强制等待
		Thread.sleep(20000);
		
		//断言
		String actualText = driver.findElement(By.className("tips")).getText();
		String expectedText = "账号信息错误";
		Assert.assertEquals(actualText, expectedText);
	}
	
	
	
	
	
	
	
	
	
	
}
