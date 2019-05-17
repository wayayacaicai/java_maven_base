package web.teachers.day05.section02;

import java.util.Set;

import org.apache.commons.lang3.math.Fraction;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://mail.qq.com/");
		
		//获取第一个窗口的句柄
		String firstHandle = driver.getWindowHandle();
		System.out.println(firstHandle);
		driver.findElement(By.linkText("手机版")).click();
		//得到所有的窗口的句柄
		System.out.println("-----------------------------------------");
		Set<String> allHandles = driver.getWindowHandles();
		//第一个句柄
		String secondHandle = null;
		//这里有两个句柄，只要不是第一个的，那么就是第二个的
		for (String handle : allHandles) {
			System.out.println(handle);
			if (!handle.equals(firstHandle)) {
				secondHandle = handle;
			}
		}
		//切换到第二个窗口
		driver.switchTo().window(secondHandle);
		
		driver.findElement(By.className("download_ios")).click();
		
		//切换会第一个窗口
		driver.switchTo().window(firstHandle);
		driver.findElement(By.linkText("企业邮箱")).click();
		
		//第三个窗口，第四个窗口怎么切换
		
		Thread.sleep(5000);
		driver.quit();
	}
}
