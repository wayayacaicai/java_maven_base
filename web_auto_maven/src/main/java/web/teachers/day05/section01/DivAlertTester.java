package web.teachers.day05.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class DivAlertTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/company/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://120.78.128.25:8765/");
		driver.findElement(By.className("calculator-btn")).click();
		//注意页面还没有加载完
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("div[data-type='5']")).click();
		
		
		Thread.sleep(5000);
		driver.quit();
	}

}
