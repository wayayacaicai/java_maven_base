package web.teachers.day02.section01;
//
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
//静态导入

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Baidu_Test_Case extends Base_Test_Case{
	
	//1：参数只要注入一遍
	//2：浏览器只要打开一遍
	@Test
//	@Parameters(value={"browserType","seleniumVersion","driverExePath"})
	public void test_case_01(){
//		WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
		to("http://www.baidu.com");
	}

	
	@Test
//	@Parameters(value={"browserType","seleniumVersion","driverExePath"})
	public void test_case_02(){
//		WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
		to("http://www.lemonban.com");
	}
	
	@Test
//	@Parameters(value={"browserType","seleniumVersion","driverExePath"})
	public void test_case_03(){
//		WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
		to("http://www.lemfix.com");
	}


}
