package web.teachers.day05.section02;

import java.util.Set;

import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DatePickerTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		//登录
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		//点击
		Thread.sleep(1000);
		driver.findElement(By.id("menu-product")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("一周排课")).click();
		Thread.sleep(2000);
		
		//切换到iframe
		WebElement frameElement = driver.findElement(By.cssSelector("iframe[src='class-plan-list.html']"));
		driver.switchTo().frame(frameElement);
		
		driver.findElement(By.id("datemin")).sendKeys("2019-05-16 22:02:57");
		//2019-5-16 22:02:57
		//2019-05-16 22:03:46
		driver.findElement(By.id("searchButton")).click();
		
		Thread.sleep(5000);
		driver.quit();
	}
}
