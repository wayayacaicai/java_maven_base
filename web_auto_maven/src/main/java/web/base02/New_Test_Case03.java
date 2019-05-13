package web.base02;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Desc:柠檬登录测试----隐式等待和显示等待
 * @author:zpp 
 * @time:2019年5月13日 下午5:19:02
 */
public class New_Test_Case03 extends BaseBrowserAttrs{
	//登陆成功，显示等待
	//@Test
	public void test_case_01(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");;
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btn-success")).click();
		
		WebDriverWait wait = new WebDriverWait(driver,10);
		WebElement aElement = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				
				return driver.findElement(By.linkText("柠檬班题库APP后台管理系统"));
			}
		});
		String actualResult = aElement.getText();
		String expectedResult = "柠檬班题库APP后台管理系统";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//账号输入错误，显示等待
	//@Test
	public void test_case_02(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("13825161922");;
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btn-success")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return "账号信息错误".equals(driver.findElement(By.className("tips")).getText());
			}
		});
		String actualResult = driver.findElement(By.className("tips")).getText();	
		String expectedResult = "账号信息错误";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//密码输入错误，显示等待
	//@Test
	public void test_case_03(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("13825161922");;
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btn-success")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver arg0) {
				return "账号信息错误".equals(driver.findElement(By.className("tips")).getText());
			}
		});
		String actualResult = driver.findElement(By.className("tips")).getText();	
		String expectedResult = "账号信息错误";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//用户名不能为空，隐式等待
	//@Test
	public void test_case_04(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("");;
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btn-success")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualResult = driver.findElement(By.className("tips")).getText();	
		String expectedResult = "用户名不能为空";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//密码名不能为空，隐式等待
	//@Test
	public void test_case_05(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("13825161922");;
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.className("btn-success")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String actualResult = driver.findElement(By.className("tips")).getText();	
		String expectedResult = "用户名不能为空";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//查看一周排课
	@Test
	public void test_case_06(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getWebDriver();
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");;
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.className("btn-success")).click();
		
		
		WebDriverWait wait1 = new WebDriverWait(driver,10);
		WebElement aElement1 = wait1.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				
				return driver.findElement(By.linkText("柠檬班题库APP后台管理系统"));
			}
		});
		String actualResult = aElement1.getText();
		System.out.println(actualResult);
		
		driver.findElement(By.xpath("//*[@id='menu-product']"));
		
		
		WebDriverWait wait3 = new WebDriverWait(driver,10);
		WebElement aElement3= wait3.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				
				return driver.findElement(By.linkText("一周排课"));
			}
		});
		aElement3.click();
		
		String text = driver.findElement(By.linkText(" 我要录课")).getText();
		System.out.println(text);
		
	}
}
