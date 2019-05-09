package web.base01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IETester {
	public static void main(String[] args) {
		// 异常1：The path to the driver executable must be set by the
		// webdriver.ie.driver system property;
		// System.setProperty("webdriver.ie.driver",
		// "src/main/resources/IEDriverServer.exe");
		// 异常2： Protected Mode settings are not the same for all zones. Enable
		// Protected Mode must be set to the same value (enabled or disabled)
		// for all zones. (WARNING: The server did not provide any stacktrace
		// information)
		// 解决方案1：打开IE浏览器->工具->安全->全部勾选启用保护模式（不通用）
		// 异常3：Browser zoom level was set to 125%. It should be set to 100%
		// (WARNING: The server did not provide any stacktrace information)
		// 解决方案：把浏览器的缩放调整成100%，浏览器缩放级别设置不对导致的(点工具栏页面->缩放设置)

		// 设置IE的可执行文件的路径
		System.setProperty("webdriver.ie.driver", "src/main/resources/drivers/company/IEDriverServer.exe");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		// 添加一个能力:忽略安全域设置
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		// 忽略浏览器缩放
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		// 初始化打开页面
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "http://www.baidu.com");

		WebDriver driver = new InternetExplorerDriver(capabilities);

		driver.get("http://www.baidu.com");

		driver.close();

	}
}
