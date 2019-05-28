package web.base09.util;

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

public class WebAutoUtil {

	/**
	 * ie浏览器的名称
	 */
	private static final String IE_BROWSER_NAME = "ie";
	/**
	 * 
	 */
	private static final String CHROME_BROWSER_NAME = "chrome";
	private static final String FIREFOX_BROWSER_NAME = "firefox";
	private static final String EDGE_BROWSER_NAME = "edge";
	private static final String SAFARI_BROWSER_NAME = "safari";
	private static final String OPERA_BROWSER_NAME = "opera";

	private static final String SELENIUM_VERSION_2_X = "2.x";
	private static final String SELENIUM_VERSION_3_X = "3.x";
	private static final String SELENIUM_VERSION_4_X = "4.x";

	/**
	 * 打开一个浏览器-->创建一个浏览器的驱动
	 * 需要知道浏览器的类型：ie、chrome、firefox、edge、safari
	 * 
	 */
	public static WebDriver openBrowser(String browserType, String seleniumVersion,String driverExePath) {
		if (IE_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY,
					driverExePath);
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");
			return new InternetExplorerDriver(capabilities);
		} else if (CHROME_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, driverExePath);
			return new ChromeDriver();
		} else if (FIREFOX_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			System.setProperty(SystemProperty.BROWSER_BINARY, "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
			if (SELENIUM_VERSION_3_X.equalsIgnoreCase(seleniumVersion)
					|| SELENIUM_VERSION_4_X.equalsIgnoreCase(seleniumVersion)) {
				System.setProperty(GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY, driverExePath);
			}
			return new FirefoxDriver();
		} else if (EDGE_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			System.setProperty(EdgeDriverService.EDGE_DRIVER_EXE_PROPERTY, driverExePath);
			return new EdgeDriver();
		} else if (SAFARI_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			return new SafariDriver();
		} else if (OPERA_BROWSER_NAME.equalsIgnoreCase(browserType)) {
			return new OperaDriver();
		} else {
			//抛异常：浏览器类型非法
			return null;
		}

	}

	public static void main(String[] args) {
		/**
		 * src/main/resources/IEDriverServer.exe
		 * src/main/resources/chromedriver.exe
		 * src/main/resources/geckodriver.exe
		 * src/main/resources/MicrosoftWebDriver.exe
		 */
		openBrowser("ie", "2.x","src/main/resources/IEDriverServer.exe");
	}
}
