package web.teachers.day01.section01;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;

public class FirefoxTester {

	/**
	 * 学习时：selenium 2.53.1  + firefox46.0   不需要可执行驱动文件   
	 * 			2.xx:只支持到火狐的47.0
	 * 		selenium 3.5.1+firefox 56 + GeckoDriver 需要可执行驱动文件
	 * 
	 * 		火狐驱动
				https://github.com/mozilla/geckodriver/releases/
			火狐各版本  
				http://ftp.mozilla.org/pub/firefox/releases/

	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//设置火狐的可执行文件的路径
//		System.setProperty("webdriver.firefox.bin", "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		System.setProperty(SystemProperty.BROWSER_BINARY, "D:\\Program Files\\Mozilla Firefox\\firefox.exe");
		
		//如果是3.xx的selenium，需要设置驱动
		//Selenium3.4-Firefox53-geckodriver0.18.0(D)
//		Selenium3.5.3-Firefox56-geckodriver0.19.0
//		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");

		//首先要打开火狐浏览器
		FirefoxDriver driver = new FirefoxDriver();
		//xxxDriver
//		InternetExplorerDriver
//		ChromeDriver
//		SafariDriver
		
	}

}
