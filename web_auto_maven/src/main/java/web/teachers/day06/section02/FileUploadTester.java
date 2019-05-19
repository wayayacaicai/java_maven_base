package web.teachers.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class FileUploadTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.treejs.cn/v3/demo/cn/exedit/drag.html");
		Thread.sleep(3000);
		//如果加载不出来，提示找不到元素
		driver.navigate().refresh();
		
		WebElement ele121 = driver.findElement(By.id("treeDemo_4_span"));
		WebElement ele131 = driver.findElement(By.id("treeDemo_8_span"));
		
		Actions actions = new Actions(driver);
		actions.clickAndHold(ele121).moveToElement(ele131).release().perform();
		
		Thread.sleep(5000);
		driver.quit();
	}
}
