package web.base0203;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * @Desc:基础用例模板
 * @author:zpp 
 * @time:2019年5月10日 下午4:47:46
 */
public abstract class BaseBrowserAttrs {
	private static WebDriver driver;
	
	@BeforeSuite
	@Parameters(value = {"browserType","seleniumVersion","driverPath"})
	public static void beforeTestSuite(String browserType,String seleniumVersion,String driverPath){
		driver = WebDriverUtils.getWebDriver(browserType, seleniumVersion, driverPath);
	}
	
	@AfterSuite
	public static void afterTestSuite(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	/**
	 * 返回一个浏览器驱动对象
	 * */
	public static WebDriver getWebDriver(){
		return driver;
	}
	
	/**
	 * 访问一个页面
	 * */
	public static void toPage(String pageUrl){
		driver.get(pageUrl);
	}
}
