package web.base04;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

/**
 * @Desc:浏览器驱动获取工具类 
 * @author:zpp 
 * @time:2019年5月10日 上午11:21:29
 */
public class WebDriverUtils {
	/**
	 * 浏览器名称
	 * */
	private static final String CHROME_BROWSER_NAME = "chrome";
	private static final String FIREFOX_BROWSER_NAME = "firefox";
	private static final String IE_BROWSER_NAME = "ie";
	private static final String EDGE_BROWSER_NAME = "edge";
	private static final String SAFARI_BROWSER_NAME = "safari";
	private static final String OPERA_BROWSER_NAME = "opera";
	/**
	 * selenium版本
	 * */
	private static final String SELENIUM_VERSION_2_X = "2.x";
	private static final String SELENIUM_VERSION_3_X = "3.x";
	private static final String SELENIUM_VERSION_4_X = "4.x";
	/**
	 * 浏览器驱动的路径,改为在testng.xml中当做参数传递
	 * */
//	private static final String CHROME_DRIVER_PATH = "src/main/resources/drivers/company/chromedriver.exe";
//	private static final String IE_DRIVER_PATH = "src/main/resources/drivers/company/IEDriverServer.exe";
//	private static final String EDGE_DRIVER_PATH = "src/main/resources/drivers/company/MicrosoftWebDriver.exe";
//	private static final String FIREFOX_DRIVER_PATH = "src/main/resources/drivers/company/geckodriver.exe";
	/**
	 * 其他
	 * */
	private static final String FIREFOX_BROWSER_PATH = "D:\\normal_soft\\Browser\\FireFox\\fire46.0.1\\firefox.exe";
//	private static final String FIREFOX_BROWSER_PATH = "D:\\normal_soft\\browser\\firefox46.0\\firefox.exe";
	private static final String INIT_URL = "http://www.baidu.com";
	/**
	 * @Desc 获取浏览器的驱动
	 * @param browserType
	 * @param seleniumVersion
	 * @param driverPath
	 * @return
	 */
	public static WebDriver getWebDriver(String browserType,String seleniumVersion,String driverPath){
		if(CHROME_BROWSER_NAME.equalsIgnoreCase(browserType)){
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverPath);
			return new ChromeDriver();
		}else if(FIREFOX_BROWSER_NAME.equalsIgnoreCase(browserType)){
			System.setProperty(SystemProperty.BROWSER_BINARY, FIREFOX_BROWSER_PATH);
			if(!(SELENIUM_VERSION_2_X.equalsIgnoreCase(seleniumVersion))){
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverPath);
			}
			return new FirefoxDriver();
		}else if(IE_BROWSER_NAME.equalsIgnoreCase(browserType)){
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, driverPath);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, INIT_URL);
			return new InternetExplorerDriver(capabilities);
		}else if(EDGE_BROWSER_NAME.equalsIgnoreCase(browserType)){
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, driverPath);
			return new EdgeDriver();
		}
		else if(SAFARI_BROWSER_NAME.equalsIgnoreCase(browserType)){
			//后期用到再做扩展
			return new SafariDriver();
		}else if(OPERA_BROWSER_NAME.equalsIgnoreCase(browserType)){
			//后期用到再做扩展
			return new OperaDriver();
		}else{
			//浏览器输入不合法，抛出空指针异常
			return null;
		}
	}
	
	
	public static void main(String[] args) {
		
//		private static final String CHROME_DRIVER_PATH = "src/main/resources/drivers/company/chromedriver.exe";
//		private static final String IE_DRIVER_PATH = "src/main/resources/drivers/company/IEDriverServer.exe";
//		private static final String EDGE_DRIVER_PATH = "src/main/resources/drivers/company/MicrosoftWebDriver.exe";
//		private static final String FIREFOX_DRIVER_PATH = "src/main/resources/drivers/company/geckodriver.exe";
		String driverPath = "src/main/resources/drivers/company/chromedriver.exe";
		WebDriver driver = getWebDriver("chrome", "2.x", driverPath);
		driver.get("http://www.hao123.com");
		driver.quit();
	}
}
