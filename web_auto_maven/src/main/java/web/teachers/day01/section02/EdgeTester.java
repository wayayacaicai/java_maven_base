package web.teachers.day01.section02;

import org.openqa.selenium.edge.EdgeDriver;

public class EdgeTester {

	/**
	 * 下载驱动：https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
	 * @param args
	 */
	public static void main(String[] args) {
		//The path to the driver executable must be set by the webdriver.edge.driver system property; for more information, see https://github.com/SeleniumHQ/selenium/wiki/MicrosoftWebDriver. The lates
		System.setProperty("webdriver.edge.driver", "src/main/resources/MicrosoftWebDriver.exe");
		EdgeDriver driver = new EdgeDriver();
	}

}
