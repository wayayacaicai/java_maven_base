package web.base0203;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.eclipse.jetty.util.statistic.SampleStatistic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Desc:柠檬登录测试----隐式等待和显示等待
 * @author:zpp 
 * @time:2019年5月13日 下午5:19:02
 */
public class New_Test_Case03 extends BaseBrowserAttrs{
	private static Logger logger = Logger.getLogger(New_Test_Case03.class);
	
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
		
		logger.info("输入账号");
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");;
		
		logger.info("输入密码");
		driver.findElement(By.id("password")).sendKeys("123456");
		
		logger.info("点击登录按钮");
		driver.findElement(By.className("btn-success")).click();
		
		logger.info("得到文本信息-----柠檬班题库APP后台管理系统");
		WebDriverWait wait1 = new WebDriverWait(driver,10);
		WebElement aElement1 = wait1.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				return driver.findElement(By.linkText("柠檬班题库APP后台管理系统"));
			}
		});
		String actualResult = aElement1.getText();
		
		logger.info("点击排课管理");
		driver.findElement(By.xpath("//*[contains(@class,'bk_2')]/dl[1]/dt")).click();
		
		logger.info("点击一周排课");
		WebDriverWait wait2 = new WebDriverWait(driver,10);
		WebElement aElement2= wait2.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				
				return driver.findElement(By.linkText("一周排课"));
			}
		});
		aElement2.click();
		
		logger.info("切换到对应的iframe");
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[src='class-plan-list.html']")));
		
		logger.info("找到批量删除按钮");
		String text = driver.findElement(By.xpath("//*[contains(@class,'bk-gray')]/span/a[1]")).getText();
		
		logger.info("切换回默认的iframe");
		driver.switchTo().defaultContent();
		
		logger.info("点击机构课程");
		driver.findElement(By.xpath("//*[contains(@class,'bk_2')]/dl[2]/dt")).click();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
}
