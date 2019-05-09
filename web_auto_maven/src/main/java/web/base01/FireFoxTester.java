package web.base01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriver.SystemProperty;

public class FireFoxTester {
	
	public static void main(String[] args){
		//设置火狐的可执行文件的路径
//		System.setProperty("webdriver.firefox.bin", "D:\\normal_soft\\browser\\firefox49.0\\firefox.exe");
		System.setProperty(SystemProperty.BROWSER_BINARY, "D:\\normal_soft\\browser\\firefox46.0\\firefox.exe");
	
		//如果是3.xx的selenium，需要设置驱动
		//Selenium3.4-Firefox53-geckodriver0.18.0(D)
//		Selenium3.5.3-Firefox56-geckodriver0.19.0
//		System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
			
		WebDriver driver = new FirefoxDriver();
		
		driver.get("http://www.baidu.com");
		
		driver.close();
	}
}
