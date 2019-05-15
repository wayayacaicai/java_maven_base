package web.teachers.day04.section02.proj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Lemon_Test_Case_bak extends Base_Test_Case {
	@Test
	public void test_case_01() throws InterruptedException {
		to("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getDriver();
		
		//执行测试用例：输入手机号和密码，点击登录按钮
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		driver.findElement(By.id("menu-product")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);
		
		//等待找到这个元素
		WebElement aElement = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				System.out.println("------------------");
				return driver.findElement(By.linkText("一周排课"));
			}
			
		});
		
		aElement.click();
		
		//点击
		driver.findElement(By.id("menu-teacher")).click();
		WebElement teacherInfo = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				System.out.println("------------------");
				return driver.findElement(By.linkText("老师信息"));
			}
			
		});
		teacherInfo.click();
		
	}
	
}
