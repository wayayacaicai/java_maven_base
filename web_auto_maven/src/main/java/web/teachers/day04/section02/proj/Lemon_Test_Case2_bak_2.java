package web.teachers.day04.section02.proj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Lemon_Test_Case2_bak_2 extends Base_Test_Case {
	@Test
	public void test_case_01() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		//执行测试用例：输入手机号和密码，点击登录按钮
		getElement(By.id("mobilephone")).sendKeys("13825161923");
		getElement(By.id("password")).sendKeys("123456");
		getElement(By.id("login")).click();
		//点击排课管理
		getElement(By.id("menu-product")).click();
		//点击一周排课
		getElement(By.linkText("一周排课")).click();
		//点击老师信息管理
		getElement(By.id("menu-teacher")).click();
		//点击老师信息
		getElement(By.linkText("老师信息")).click();
		
	}
	
}
