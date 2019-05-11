package web.teachers.day02.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Baidu_Test_Case extends Base_Test_Case {

	/**
	 * 为什么要定位元素，查找元素
	 * 功能测试
	 */
	@Test
	public void test_case_01() {
//		to("http://www.baidu.com");
//		WebDriver driver = getDriver();
		//查找单一元素
		//		driver.findElement(by)
		//查找元素列表
		//		driver.findElements(by)
		
		//找到百度的输入框
		//1:id -->唯一标识 在一个页面中，id应该是唯一的
//		WebElement input = driver.findElement(By.id("kw"));
		//2:name-->名称
//		WebElement input = driver.findElement(By.name("wd"));
//		input.sendKeys("柠檬班软件测试");
//		System.out.println(input.getAttribute("autocomplete"));
		//3：linkText -->完整的超链接文本
//		driver.findElement(By.linkText("新闻")).click();
		//4:partialLinkText -->部分的超链接文本
//		driver.findElement(By.partialLinkText("术")).click();
		//linkText、 partialLinkText
		
//		to("http://120.78.128.25:8765/Index/login.html");
//		WebDriver driver = getDriver();
////		driver.findElement(By.linkText("免费注册")).click();
//		driver.findElement(By.partialLinkText("免费注册")).click();
		
		//5:tagName-->标签名
//		driver.findElement(By.tagName("input")).sendKeys("13825161923");
		to("http://www.baidu.com");
		WebDriver driver = getDriver();
//		driver.findElement(By.tagName("input")).sendKeys("柠檬班软件测试");
		/*List<WebElement> elements = driver.findElements(By.tagName("a"));
		System.out.println(elements.size());
		for (WebElement webElement : elements) {
			System.out.println(webElement.getAttribute("href"));
		}*/
		
		//6:
		driver.findElement(By.className("s_ipt")).sendKeys("柠檬班");
		//不能用复合类名
//		driver.findElement(By.className("bg s_btn")).click();
		WebElement btn = driver.findElement(By.className("s_btn"));
		System.out.println(btn.getAttribute("type"));
		btn.click();
		
	}
}
