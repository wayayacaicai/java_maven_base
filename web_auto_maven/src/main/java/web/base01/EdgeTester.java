package web.base01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeTester {

	public static void main(String[] args) {
		//设置驱动文件路径的属性
		System.setProperty("webdriver.edge.driver", "src/main/resources/drivers/company/MicrosoftWebDriver.exe");
		
		WebDriver driver = new EdgeDriver();
		
		driver.get("http://www.baidu.com");
		
		driver.close();
	}

}
