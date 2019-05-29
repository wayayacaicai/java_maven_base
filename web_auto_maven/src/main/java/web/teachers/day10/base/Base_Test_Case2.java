package web.teachers.day10.base;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.teachers.day10.pojo.Locator;
import web.teachers.day10.util.PagesUtils;
import web.teachers.day10.util.WebAutoUtil;



/**
 * 基础测试类（基类）
 * @author happy
 * @date 2019年5月9日
 * @desc 
 * @email
 */
public abstract class Base_Test_Case2 {
	private static WebDriver driver;

	@BeforeSuite
	@Parameters(value = { "browserType", "seleniumVersion", "driverExePath" })
	public void beforeSuite(String browserType, String seleniumVersion, String driverExePath) {
		driver = WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
		//浏览器最大化
		driver.manage().window().maximize();
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
	public void click(String pageName, String locatorName) {
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
	public void type(String pageName, String locatorName, String content) {
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
	public String getText(String pageName, String locatorName) {
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
	 * 智能等待获取指定页面元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String pageName, String locatorName) {
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(locatorName);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();
		//是否要等待可被点击
		String isClickable = locator.getClickable();
		//是否要等待显示
		String isDisplayed = locator.getDisplayed();
		//通过反射获取到by对象
		Class clazz = By.class;
		try {
			Method byMethod = clazz.getMethod(byType, String.class);
			By by = (By) byMethod.invoke(null, value);
			if ("true".equals(isClickable)) {
				ExpectedConditions.elementToBeClickable( by);
				return driver.findElement(by);
			}else {
				ExpectedConditions.visibilityOfElementLocated(by);
				return driver.findElement(by);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		

		/*WebDriverWait wait = new WebDriverWait(driver, 5);

		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				By by = null;
				Class clazz = By.class;
				try {
					Method byMethod = clazz.getMethod(byType, String.class);
					by = (By) byMethod.invoke(null, value);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return driver.findElement(by);
			}
		});*/

//		return element;

	}

	/**
	 * 智能等待获取当前页面元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String locatorName) {
		//根据类和pages.xml中间页面命名规则获得页面名称
		String className = this.getClass().getSimpleName();
		String pageName = className.substring(0, className.indexOf("_"));
		return getElement(pageName, locatorName);
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
