package web.teachers.day04.section02.proj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
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

	@AfterSuite
	public void afterSuite() throws InterruptedException {
		//线程等待5s后
		Thread.sleep(5000);
		//关闭浏览器
		driver.quit();
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
	
	/**
	 * 单击某个元素
	 */
	public static void click(By by){
		getElement(by).click();
	}
	
	/**
	 * 往某个元素输入内容
	 */
	public static void type(By by,String content){
		getElement(by).sendKeys(content);
	}

	/**
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public static WebElement getElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return driver.findElement(by);
			}

		});
		
		return element;
	}

}
