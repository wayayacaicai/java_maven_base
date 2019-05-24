package web.base08.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import web.base08.util.WebDriverUtils;


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
	 * @Desc 点击某个元素
	 * @param by
	 */
	public void click(By by){
		getElement(by).click();
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
	 * @Desc 得到某个元素的文本值
	 * @param by
	 * @return
	 */
	public String getTextInfo(By by){
		return getElement(by).getText();
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
}
