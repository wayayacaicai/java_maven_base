package web.teachers.day03.section02;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Lemon_Test_Case extends Base_Test_Case {
	//http://test.lemonban.com/lmcanon_web_auto/mng/register.html?t=20181011
	@Test
	public void test_case_01() throws InterruptedException {
		to("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		WebDriver driver = getDriver();
		
		//执行测试用例：输入手机号和密码，点击登录按钮
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		driver.findElement(By.id("menu-product")).click();
		//1:硬性等待、线程等待、线程休眠、强制等待
//		Thread.sleep(20000);
		//2:隐式等待
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		优点：相对灵活，
//		缺点：设置是针对全局的，在WebDriver实例整个生命周期有效，但并不是所有的元素都需要等待
		//并不是所有元素都需要等待
		//只能等待页面上面是否有这个元素
		//复杂场景没有办法完成：我想等这个元素可见的时候，去操作
		//我想等这个元素可被点击的，去操作这个元素
		//页面上面有没有这个元素：
		
		//3:智能等待、显式等待
//		ExpectedConditions. -->工具类
		
		//我的期望条件是：一周排课这个超链接已经出现了
		WebDriverWait wait = new WebDriverWait(driver, 1);
/*		wait.until(new ExpectedCondition() {
			@Override
			public Object apply(Object input) {
				System.out.println("-----------------正在查找一周排课这个元素------------------");
				//轮询11次： 0.5s  500ms去轮询一次
				return driver.findElement(By.linkText("一周排课"));
			}
		});*/
		
		//等待找到这个元素
		WebElement aElement = wait.until(new ExpectedCondition<WebElement>() {
			@Override
			public WebElement apply(WebDriver input) {
				// TODO Auto-generated method stub
				System.out.println("------------------");
				return driver.findElement(By.linkText("一周排课1"));
			}
			
		});
		
		//等待可被操作
/*		Boolean flag = wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver input) {
				return driver.findElement(By.linkText("一周排课")).isEnabled();
			}
		});*/
		
//		ExpectedConditions.elementToBeClickable(By.linkText("一周排课"));
		driver.findElement(By.linkText("一周排课")).click();
		

//		aElement.click();
//		driver.findElement(By.linkText("一周排课")).click();
		
		
		
		//断言
//		String actualText = driver.findElement(By.className("tips")).getText();
//		String expectedText = "账号信息错误";
//		Assert.assertEquals(actualText, expectedText);
	}
	
	
	
	
	
	
	
	
	
	
}
