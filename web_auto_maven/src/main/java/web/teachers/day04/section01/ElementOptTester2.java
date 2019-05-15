package web.teachers.day04.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementOptTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", 
				"src/main/resources/drivers/company/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://120.78.128.25:8765/Index/login.html");
		driver.findElement(By.name("phone")).sendKeys("13825161923");
		driver.findElement(By.name("password")).sendKeys("lemon123");
		//提交表单
//		driver.findElement(By.name("login-form")).submit();
		//只要是表单中的元素即可，会自动找到form进行提交
		driver.findElement(By.name("phone")).submit();	
		Thread.sleep(5000);
		driver.quit();
	}

}
