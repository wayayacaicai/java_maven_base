package web.teachers.day07.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.teachers.day07.util.WebAutoUtil;

/**
 * 基础测试类（基类）
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
	public void afterSuite() {
		//线程等待5s后
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			//日志
//			e.printStackTrace();
		}
		//关闭浏览器
		driver.quit();
	}

	/**
	 * 打开某一个页面
	 * @param pageUrl 页面的url
	 */
	public void to(String pageUrl) {
		driver.get(pageUrl);
	}
	
	/**
	 * 单击某个元素
	 */
	public void click(By by){
		getElement(by).click();
	}
	
	/**
	 * 往某个元素输入内容
	 */
	public void type(By by,String content){
		getElement(by).sendKeys(content);
	}
	
	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getText(By by){
		return getElement(by).getText();
	}

	/**
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public  WebElement getElement(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				return driver.findElement(by);
			}

		});
		
		return element;
	}
	
	/**
	 * 等待元素可被点击
	 * @param by
	 * @return
	 */
	public WebElement getElementToBeClickable(By by) {
		ExpectedConditions.elementToBeClickable(by);
		return driver.findElement(by);
	}

}
