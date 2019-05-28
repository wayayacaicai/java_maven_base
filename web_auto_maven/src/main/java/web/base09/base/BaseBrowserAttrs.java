package web.base09.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.base08.util.WebDriverUtils;
import web.base09.pojo.Locator;
import web.base09.util.PagesUtils;



/**
 * @Desc:基础用例模板
 * @author:zpp 
 * @time:2019年5月10日 下午4:47:46
 */
public abstract class BaseBrowserAttrs {
	private static WebDriver driver; //保持全局只有一个驱动类对象
	
	@BeforeSuite
	@Parameters(value = {"browserType","seleniumVersion","driverPath"})
	public void beforeTestSuite(String browserType,String seleniumVersion,String driverPath){
		driver = WebDriverUtils.getWebDriver(browserType, seleniumVersion, driverPath);
	}
	
	@AfterSuite
	public void afterTestSuite(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	/**
	 * 访问一个页面
	 * */
	public void toPage(String pageUrl){
		driver.get(pageUrl);
	}
	
	/**
	 * @Desc 单击某个元素
	 * @param by
	 */
	public void click(By by){
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
	 * @Desc 往某个元素输入内容
	 * @param by
	 * @param content
	 */
	public void type(By by, String content){
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
	 * @Desc 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getTextInfo(By by){
		return getElement(by).getText();
	}
	
	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getTextInfo(String locatorName) {
		return getElement(locatorName).getText();
	}
	
	/**
	 * 获取某个元素的文本内容
	 * @param by
	 * @return
	 */
	public String getTextInfo(String pageName,String locatorName) {
		return getElement(pageName, locatorName).getText();
	}
	
	/**
	 * @Desc 智能等待元素的获取元素(设置默认的等待时间)
	 * @param by
	 * @return
	 */
	public WebElement getElement(By by){
		int times = 5;
		WebElement element = getElement(by,times);
		return element;
	}
	
	
	/**
	 * @Desc 智能等待元素的获取元素(设置默认的等待时间)
	 * @param pageName
	 * @param locatorName
	 * @return
	 */
	public WebElement getElement(String pageName, String locatorName){
		int times = 5;
		WebElement element = getElement(pageName,locatorName,times);
		return element;
	}
	
	
	/**
	 * @Desc 智能等待元素的获取元素(设置默认的等待时间)
	 * @param locatorName
	 * @return
	 */
	public WebElement getElement(String locatorName){
		int times = 5;
		WebElement element = getElement(locatorName,times);
		return element;
	}
	
	/**
	 * @Desc 智能等待元素的获取元素(自己设置等待时间)
	 * @param by
	 * @param times
	 * @return
	 */
	public WebElement getElement(By by,int times){
		WebDriverWait wait = new WebDriverWait(driver, times);
		WebElement element = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {	
				return driver.findElement(by);
			}
		});
		return element;
	}
	
	
	/**
	 * @Desc 智能等待元素的获取元素(自己设置等待时间)
	 * @param pageName
	 * @param locatorName
	 * @param times
	 * @return
	 */
	public WebElement getElement(String pageName, String locatorName,int times) {
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(locatorName);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();

		WebDriverWait wait = new WebDriverWait(driver, times);
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
	 * @Desc 智能等待元素的获取元素(自己设置等待时间)
	 * @param locatorName
	 * @param times
	 * @return
	 */
	public WebElement getElement(String locatorName,int times) {
		//根据类和pages.xml中间页面命名规则获得页面名称
		String className = this.getClass().getSimpleName();
		String pageName = className.substring(0,className.indexOf("_"));
		//获得该页面该元素的定位信息
		Locator locator = PagesUtils.getAllLocatorsByPageName(pageName).get(locatorName);
		//定位的方式
		String byType = locator.getBy();
		//定位的值
		String value = locator.getValue();

		WebDriverWait wait = new WebDriverWait(driver, times);
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
	 * @Desc 智能等待元素的获取布尔值(传入文本值比较)
	 * @param by
	 * @param text
	 * @return
	 */
	public boolean getElementBoolean(By by,String text){
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Boolean flag = wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {	
				return text.equals(driver.findElement(by).getText());
			}
		});
		return flag;
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
