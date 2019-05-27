package web.teachers.day09.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.teachers.day09.pojo.Locator;
import web.teachers.day09.util.PagesUtils;
import web.teachers.day09.util.WebAutoUtil;


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
	public void click(By by) {
		getElement(by).click();
	}
	
	/**
	 * 单击某个元素
	 */
	public void click(String locatorName) {
		getElement(locatorName).click();
	}
	/**
	 * 单击某个元素
	 */
	public void click(String pageName,String locatorName) {
		getElement(pageName, locatorName).click();
	}

	/**
	 * 往某个元素输入内容
	 */
	public void type(By by, String content) {
		getElement(by).sendKeys(content);
	}
	
	/**
	 * 往某个元素输入内容
	 */
	public void type(String locatorName, String content) {
		getElement(locatorName).sendKeys(content);
	}
	
	/**
	 * 往某个元素输入内容
	 */
	public void type(String pageName,String locatorName, String content) {
		getElement(pageName, locatorName).sendKeys(content);
	}
	



	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getText(By by) {
		return getElement(by).getText();
	}
	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getText(String locatorName) {
		return getElement(locatorName).getText();
	}
	
	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getText(String pageName,String locatorName) {
		return getElement(pageName, locatorName).getText();
	}

	/**
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(By by) {
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
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String pageName, String locatorName) {
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(locatorName);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				By by = null;
				if ("id".equals(byType)) {
					by = By.id(value);
				} else if ("name".equals(byType)) {
					by = By.name(value);
				} else if ("linkText".equals(byType)) {
					by = By.linkText(value);
				} else if ("partialLinkText".equals(byType)) {
					by = By.partialLinkText(value);
				} else if ("tagName".equals(byType)) {
					by = By.tagName(value);
				} else if ("cssSelector".equals(byType)) {
					by = By.cssSelector(value);
				} else if ("className".equals(byType)) {
					by = By.className(value);
				} else if ("xpath".equals(byType)) {
					by = By.xpath(value);
				}
				return driver.findElement(by);
			}

		});

		return element;
	}
	
	/**
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String locatorName) {
		//根据类和pages.xml中间页面命名规则获得页面名称
		String className = this.getClass().getSimpleName();
		String pageName = className.substring(0,className.indexOf("_"));
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(locatorName);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();

		WebDriverWait wait = new WebDriverWait(driver, 5);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				By by = null;
				if ("id".equals(byType)) {
					by = By.id(value);
				} else if ("name".equals(byType)) {
					by = By.name(value);
				} else if ("linkText".equals(byType)) {
					by = By.linkText(value);
				} else if ("partialLinkText".equals(byType)) {
					by = By.partialLinkText(value);
				} else if ("tagName".equals(byType)) {
					by = By.tagName(value);
				} else if ("cssSelector".equals(byType)) {
					by = By.cssSelector(value);
				} else if ("className".equals(byType)) {
					by = By.className(value);
				} else if ("xpath".equals(byType)) {
					by = By.xpath(value);
				}
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
