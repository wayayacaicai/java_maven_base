package web.teachers.day11.base;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.teachers.day11.pojo.Locator;
import web.teachers.day11.util.PagesUtils;
import web.teachers.day11.util.WebAutoUtil;



/**
 * 基础测试类（基类）
 * @author happy
 * @date 2019年5月9日
 * @desc 
 * @email
 */
public abstract class Base_Test_Case {

	private static WebDriver driver;

	private static Logger logger = Logger.getLogger(Base_Test_Case.class);

	@BeforeSuite
	@Parameters(value = { "browserType", "seleniumVersion", "driverExePath" })
	public void beforeSuite(String browserType, String seleniumVersion, String driverExePath) {
		driver = WebAutoUtil.openBrowser(browserType, seleniumVersion, driverExePath);
		//浏览器最大化
		windowMaximize();
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
	 * @param pageUrl 页面的名称
	 */
	public void to(String pageName) {
		logger.info("打开页面[" + pageName + "]");
		driver.get(WebAutoUtil.getTotalUrlByKey(pageName));
	}

	/**
	 * 窗口最大化
	 */
	public void windowMaximize() {
		logger.info("窗口最大化");
		driver.manage().window().maximize();
	}

	/**
	 * 刷新
	 */
	public void refresh() {
		logger.info("刷新页面[" + driver.getCurrentUrl() + "]");
		driver.navigate().refresh();
	}

	/**
	 * 前进
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 回退
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 切换到frame
	 * @param keyword
	 */
	public void switchToFrame(String keyword) {
		driver.switchTo().frame(getElement(keyword));
	}

	/**
	 * 切换会默认的内容
	 */
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
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
	public void click(String keyword) {
		getElement(keyword).click();
	}

	/**
	 * 单击某个元素
	 */
	public void click(String pageName, String keyword) {
		getElement(pageName, keyword).click();
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
	public void type(String keyword, String content) {
		getElement(keyword).sendKeys(content);
	}

	/**
	 * 往某个元素输入内容
	 */
	public void type(String pageName, String keyword, String content) {
		getElement(pageName, keyword).sendKeys(content);
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
	public String getText(String keyword) {
		return getElement(keyword).getText();
	}

	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getText(String pageName, String keyword) {
		return getElement(pageName, keyword).getText();
	}

	/**
	 * 智能等待获取元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	//失效了  过期了
	@Deprecated
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
	 * 元素的名称  元素的定位方式  元素的定位信息  元素的操作类型  
	 * 关键字驱动
	 * 
	 * 智能等待获取指定页面元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String pageName, String keyword) {
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(keyword);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();
		//是否要等待可被点击
		String isClickable = locator.getClickable();
		//是否要等待显示
		String isDisplayed = locator.getDisplayed();
		//等待时间
		String delay = locator.getDelay();

		logger.info("查找[" + keyword + "]:以[" + byType + "]方式，值为[" + value + "]进行查找");

		//long类型的等待时间
		long waitTime = (delay == null ? 5L : Long.parseLong(delay));
		//通过反射获取到by对象
		Class clazz = By.class;

		WebDriverWait wait = new WebDriverWait(driver, waitTime);
		try {
			Method byMethod = clazz.getMethod(byType, String.class);
			By by = (By) byMethod.invoke(null, value);
			if ("true".equals(isClickable)) {
				return wait.until(ExpectedConditions.elementToBeClickable(by));
			} else {
				return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 智能等待获取指定页面元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(Class<? extends Base_Test_Case> clazz, String keyword) {
		String pageName = clazz.getName();
		return getElement(pageName, keyword);
	}

	/**
	 * 智能等待获取当前页面元素的方法
	 * @param by 定位方式、定位器（locator）
	 */
	public WebElement getElement(String keyword) {
		String pageName = this.getClass().getName();
		return getElement(pageName, keyword);
	}

	//	assertTextPresent：断言页面元素文本值为某文本 -->页面  元素  期望的文本
	/**
	 * 断言指定页面元素文本值为某文本
	 * @param clazz
	 * @param keyword
	 * @param expectedContent
	 */
	public void assertTextPresentInPage(Class<? extends Base_Test_Case> clazz, String keyword, String expectedContent) {
		String actualContent = getElement(clazz, keyword).getText();
		Assert.assertEquals(actualContent, expectedContent);
	}

	/**
	 * 断言当前页面某元素文本值为某文本
	 * @param keyword
	 * @param expectedContent
	 */
	public void assertTextPresentCurrentPage(String keyword, String expectedContent) {
		assertTextPresentInPage(this.getClass(), keyword, expectedContent);
	}

	/**
	 * 断言指定页面元素文本值包含某文本
	 * @param clazz 页面测试类的字节码对象
	 * @param keyword 元素的关键字
	 * @param expectedPartialText  期望的部分文本
	 */
	public void assertPartialTextPresentInPage(Class<? extends Base_Test_Case> clazz, String keyword,
			String expectedPartialText) {
		String totalText = getElement(clazz, keyword).getText();
		Assert.assertTrue(totalText.contains(expectedPartialText));
	}

	public void assertPartialTextPresentInCurrentPage(String keyword, String expectedPartialText) {
		assertPartialTextPresentInPage(this.getClass(), keyword, expectedPartialText);
	}

	/**
	 * 断言指定页面某元素是生效状态
	 * @param clazz 页面测试类的字节码对象
	 * @param keyword 元素关键字
	 */
	public void assertElementEditableInPage(Class<? extends Base_Test_Case> clazz, String keyword) {
		Assert.assertTrue(getElement(clazz, keyword).isEnabled());
	}

	/**
	 * 断言当前页面某元素是生效状态
	 * @param clazz 页面测试类的字节码对象
	 * @param keyword 元素关键字
	 */
	public void assertElementEditableInCurrentPage(String keyword) {
		assertElementEditableInPage(this.getClass(), keyword);
	}

	/**
	 * 断言指定页面某元素不为空
	 * @param clazz
	 * @param keyword
	 */
	public void assertElementPresentInPage(Class<? extends Base_Test_Case> clazz, String keyword) {
		WebElement element = getElement(clazz, keyword);
		Assert.assertNotNull(element);
	}
	//	assertElementNotEditable：断言某元素不可编辑
	//	assertURLContains：断言当前URL包含
	//	assertTextNotPresent
	//	assertTextNotPresentPrecesion
	//	assertElementAttributeValueEquals
	//	assertElementAttributeValueNotEquals
	//	assertAlertTextContains

}
