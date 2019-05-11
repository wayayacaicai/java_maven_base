package web.teachers.day02.section01;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * 基础测试类
 * @author happy
 * @date 2019年5月9日
 * @desc 
 * @email
 */
public abstract class Base_Test_Case {
	private static WebDriver driver;

	@BeforeSuite
	@Parameters(value = { "browserType", "seleniumVersion", "driverExePath" })
	public void beforeSuite(String browserType, String seleniumVersion, String driverExePath) {
		driver = WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
	}

	public static WebDriver getDriver() {
		return driver;
	}

	/**
	 * 打开某一个页面
	 * @param pageUrl 页面的url
	 */
	public static void to(String pageUrl) {
		driver.get(pageUrl);
	}

}
