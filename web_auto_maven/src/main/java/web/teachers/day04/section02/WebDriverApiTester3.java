package web.teachers.day04.section02;

import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverApiTester3 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
//		获取navigate对象
//		Navigation navigation = driver.navigate();
		Navigation navigation = driver.navigate();
//		访问指定的url地址
//		navigation.to(url)
		navigation.to("http://www.baidu.com");
		Thread.sleep(2000);
//		刷新当前页面
		navigation.refresh();
		Thread.sleep(2000);
		navigation.to("http://www.lemonban.com");
//		浏览器回退操作
		Thread.sleep(2000);
		navigation.back();
		Thread.sleep(2000);
//		浏览器前进操作
		navigation.forward();

		
		Thread.sleep(10000);
		driver.quit();
	}

}
