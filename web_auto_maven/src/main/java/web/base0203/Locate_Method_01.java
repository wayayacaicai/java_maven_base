package web.base0203;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Locate_Method_01 extends Base_Test_Case{
//	@Test
	public void test_case_01(){
		toPage("http://www.baidu.com");
		
		//1:id -->唯一标识 在一个页面中，id应该是唯一的
		WebDriver driver = getWebDriver();
		WebElement element_01 = driver.findElement(By.id("kw"));
		element_01.sendKeys("数学");
		
		//6.不能用复合类名
		WebElement element_02 = driver.findElement(By.className("s_btn"));
		System.out.println(element_02.getAttribute("type"));
		element_02.click();
	}
	
	@Test
	public void test_case_02(){
		toPage("http://www.baidu.com");
		
		//2:name-->名称
		WebDriver driver = getWebDriver();
		WebElement element_03 = driver.findElement(By.name("wd"));
		System.out.println(element_03.getAttribute("autocomplete"));
		
		//3：linkText -->完整的超链接文本
		WebElement element_04 = driver.findElement(By.linkText("新闻"));
		System.out.println(element_04.getAttribute("class"));
		
		//4:partialLinkText -->部分的超链接文本
		WebElement element_05 = driver.findElement(By.partialLinkText("闻"));
		System.out.println(element_05.getAttribute("class"));
		
		//5:tagName-->标签名
		List<WebElement> Elements_06 = driver.findElements(By.tagName("input"));
		System.out.println(Elements_06.size());
		for (WebElement webElement : Elements_06) {
			System.out.println(webElement);
		}
	}
}
