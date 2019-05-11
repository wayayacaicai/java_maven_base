package web.base01;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTester {
	
	public static void main(String[] args){
		//设置驱动文件路径的属性
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/company/chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.baidu.com");
		
		driver.close();
	}
}
