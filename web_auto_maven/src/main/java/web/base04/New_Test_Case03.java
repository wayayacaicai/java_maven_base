package web.base04;

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
public class New_Test_Case03 extends Base_Test_Case{
	private static Logger logger = Logger.getLogger(New_Test_Case03.class);
	
	//登陆成功，显示等待
	//@Test
	public void test_case_01(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		
		type(By.id("mobilephone"), "13825161923");
		type(By.id("password"), "123456");
		click(By.className("btn-success"));
		
		String actualResult = getTextInfo(By.linkText("柠檬班题库APP后台管理系统"));
		String expectedResult = "柠檬班题库APP后台管理系统";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//账号输入错误，显示等待
	//@Test
	public void test_case_02(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		
		type(By.id("mobilephone"), "13825161922");
		type(By.id("password"), "123456");
		click(By.className("btn-success"));
		
		getElementBoolean(By.className("tips"), "账号信息错误");
		String actualResult = getTextInfo(By.className("tips"));
		String expectedResult = "账号信息错误";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//密码输入错误，显示等待
	//@Test
	public void test_case_03(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		
		type(By.id("mobilephone"), "13825161923");
		type(By.id("password"), "123457");
		click(By.className("btn-success"));
		
		getElementBoolean(By.className("tips"), "账号信息错误");
		String actualResult = getTextInfo(By.className("tips"));
		String expectedResult = "账号信息错误";
		
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	//查看一周排课
	@Test
	public void test_case_06(){
		toPage("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		
		WebDriver driver = getWebDriver();
		
		logger.info("输入账号");
		type(By.id("mobilephone"), "13825161923");
		
		logger.info("输入密码");
		type(By.id("password"), "123456");
		
		logger.info("点击登录按钮");
		click(By.className("btn-success"));
		
		logger.info("得到文本信息-----柠檬班题库APP后台管理系统");
		String actualResult = getTextInfo(By.linkText("柠檬班题库APP后台管理系统"));
		System.out.println(actualResult);
		
		logger.info("点击排课管理");
		click(By.xpath("//*[contains(@class,'bk_2')]/dl[1]/dt"));
		
		logger.info("点击一周排课");
		click(By.linkText("一周排课"));
		
		logger.info("切换到对应的iframe");
		driver.switchTo().frame(getElement(By.cssSelector("iframe[src='class-plan-list.html']")));

		logger.info("找到批量删除按钮");
		String text = getTextInfo(By.xpath("//*[contains(@class,'bk-gray')]/span/a[1]"));
		
		logger.info("切换回默认的iframe");
		driver.switchTo().defaultContent();
		
		logger.info("点击机构课程");
		click(By.xpath("//*[contains(@class,'bk_2')]/dl[2]/dt"));
	}
}
