package web.base0203;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class New_Test_Case_01 extends BaseBrowserAttrs{
	@Test
	public void test_case_01(){
		toPage("http://www.baidu.com");
	}
	
	@Test
	public void test_case_02(){
		toPage("http://www.qq.com");
	}
	
//	@Test
//	@Parameters(value = {"browserType","seleniumVersion","driverPath"})
//	public void test_case_01(String browserType,String seleniumVersion,String driverPath){
//		WebDriver driver = WebDriverUtils.getWebDriver(browserType, seleniumVersion, driverPath);
//		driver.get("http://www.baidu.com");
//	}
//	
//	@Test
//	@Parameters(value = {"browserType","seleniumVersion","driverPath"})
//	public void test_case_02(String browserType,String seleniumVersion,String driverPath){
//		WebDriver driver = WebDriverUtils.getWebDriver(browserType, seleniumVersion, driverPath);
//		driver.get("http://www.qq.com");
//	}
}
