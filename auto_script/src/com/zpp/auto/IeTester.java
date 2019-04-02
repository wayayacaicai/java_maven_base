package com.zpp.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class IeTester {

	public static void main(String[] args) {
		System.setProperty("webdriver.ie.driver", "src/IEDriverServer.exe");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

		WebDriver driver = new InternetExplorerDriver(capabilities);

		driver.get("http://www.baidu.com");
	}

}
