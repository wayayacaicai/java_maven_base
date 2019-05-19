package web.teachers.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePickerTester2{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.12306.cn/index/");
		
		//执行js移除元素属性
		String jsStr = "document.getElementById('train_date').removeAttribute('readonly');";
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript(jsStr);
		
		Thread.sleep(2000);
		driver.findElement(By.id("train_date")).clear();
		Thread.sleep(2000);
		driver.findElement(By.id("train_date")).sendKeys("2019-05-19");
		
		Thread.sleep(5000);
		driver.quit();
	}
}
