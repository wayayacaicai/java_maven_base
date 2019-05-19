package web.teachers.day05.section01;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class IframeTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/company/chromedriver.exe");
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
//		driver.switchTo().frame(index)
//		driver.switchTo().frame(nameOrId)
		WebElement frameElement = driver.findElement(By.cssSelector("iframe[src='class-plan-list.html']"));
		driver.switchTo().frame(frameElement);
		
		driver.findElement(By.className("btn-primary")).click();
//		driver.findElement(By.name("courseTitle")).sendKeys("Java自动化");
		
		Thread.sleep(2000);
		driver.switchTo().frame("layui-layer-iframe1");
		driver.findElement(By.id("articletitle")).sendKeys("Python自动化");
		
		
		//切换会默认的内容
		driver.switchTo().defaultContent();
		driver.findElement(By.id("menu-teacher")).click();
		
		Thread.sleep(5000);
		driver.quit();
	}
}
