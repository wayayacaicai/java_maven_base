package web.teachers.day04.section01;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverApiTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
//		driver.findElementById("")
//		driver.findElement(By.id(id))
		
//		driver.get(String url)：访问指定url页面
//		driver.get("http://www.baidu.com");
//		driver.getCurrentUrl():获取当前页面的url地址
//		System.out.println(driver.getCurrentUrl());
//		driver.getTitle():获取当前页面的标题
//		System.out.println(driver.getTitle());
//		driver.getPageSource()：获取当前页面源代码
//		System.out.println(driver.getPageSource());//做爬虫--》解析html文档--》html解析库
		
		
//		driver.quit()：关闭驱动对象以及所有相关的窗口
//		driver.close()：关闭当前窗口
//		driver.findElement(By.id("jgwab")).click();
//		Thread.sleep(5000);
//		driver.close();
//		driver.findElement(by)：根据by对象获取单个元素
//		driver.findElements(by)：根据by对象获取元素集合(爬虫)
		
//		句柄:窗口的标识符
//		driver.getWindowHandle()：返回当前页面句柄（当前窗口）
		driver.get("http://www.baidu.com");
		System.out.println(driver.getWindowHandle());
		//打开另外一个窗口
		driver.findElement(By.id("jgwab")).click();
		System.out.println(driver.getWindowHandle());
//		driver.getWindowHandles()：返回所有由驱动对象打开页面的句柄，页面不同，句柄不一样
		System.out.println("-----------------------------------------------");
		Set<String> windowHandlesSet = driver.getWindowHandles();
		for (String handle : windowHandlesSet) {
			System.out.println(handle);
		}
		
		
		Thread.sleep(5000);
		driver.quit();
	}

}
