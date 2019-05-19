package web.teachers.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.baidu.com/");
		
		WebElement inputEle = driver.findElement(By.id("kw"));
		WebElement btnEle = driver.findElement(By.id("su"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(inputEle).click().sendKeys("柠檬班").perform();
		actions.moveToElement(btnEle).click().perform();
		
		Thread.sleep(5000);
		driver.quit();
	}
}
