package web.teachers.day04.section02;

import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverApiTester4 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		TargetLocator targetLocator = driver.switchTo();
//		targetLocator.alert()//alert
//		targetLocator.frame(index)//iframe
//		targetLocator.window(nameOrHandle)
		
		Thread.sleep(10000);
		driver.quit();
	}

}
