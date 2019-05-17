package web.teachers.day05.section02;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		//登录
		driver.findElement(By.id("mobilephone")).sendKeys("13825161923");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("login")).click();
		
		//点击
		Thread.sleep(1000);
		driver.findElement(By.id("menu-teacher")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("老师信息")).click();
		Thread.sleep(2000);
		
		//切换到iframe
		WebElement frameElement = driver.findElement(By.cssSelector("iframe[src='teacher-list.html']"));
		driver.switchTo().frame(frameElement);
		
		WebElement selectElement = driver.findElement(By.name("type"));
		//包装成select对象
		Select select = new Select(selectElement);
		
		//api的操作
//		select.selectByIndex(index);根据索引选中对应的元素
		//索引是从0开始的
//		select.selectByIndex(1);//全职
		
//		Thread.sleep(2000);
//		select.selectByValue(value);选择指定value值对应的选项
//		select.selectByValue("6"); //兼职面试
		
//		Thread.sleep(2000);
//		select.selectByVisibleText(text);选中可见文本值对应的选项
		select.selectByVisibleText("销售人员");//销售
		
//		select.getOptions()：获取所有选项
		/*List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			System.out.println(webElement.isSelected());
		}*/
		
//		select.isMultiple();判断是不是多选的选择框
//		select.deselectAll();取消所有选中的选项(只能用在multi-select中，否则报错)
//		...

		
		
		
		Thread.sleep(5000);
		driver.quit();
	}
}
