package web.teachers.day05.section01;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.security.UserAndPassword;


public class AlertTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		
/*		driver.get("file:///E:/svn-lemon/%E6%95%99%E5%AD%A6ppt/happy/java%E8%87%AA%E5%8A%A8%E5%8C%96/04-web%E8%87%AA%E5%8A%A8%E5%8C%96/%E7%89%B9%E6%AE%8A%E5%85%83%E7%B4%A0%E5%AE%9A%E4%BD%8D%E5%92%8C%E6%93%8D%E4%BD%9C%E9%9D%99%E6%80%81%E9%A1%B5%E9%9D%A2/%E6%A8%A1%E6%80%81%E6%A1%86/alert.html");
		driver.findElementById("abtn").click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		
		Thread.sleep(2000);
		driver.findElementById("abtn").click();
		Thread.sleep(2000);
		alert.dismiss();*/
		
		driver.get("file:///E:/svn-lemon/%E6%95%99%E5%AD%A6ppt/happy/java%E8%87%AA%E5%8A%A8%E5%8C%96/04-web%E8%87%AA%E5%8A%A8%E5%8C%96/%E7%89%B9%E6%AE%8A%E5%85%83%E7%B4%A0%E5%AE%9A%E4%BD%8D%E5%92%8C%E6%93%8D%E4%BD%9C%E9%9D%99%E6%80%81%E9%A1%B5%E9%9D%A2/%E6%A8%A1%E6%80%81%E6%A1%86/confirm.html");
		driver.findElementById("abtn").click();
		Thread.sleep(2000);
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		Thread.sleep(2000);
		alert.dismiss();
		
//		alert.sendKeys("");
//		alert.setCredentials(new UserAndPassword("", ""));
		
		
		
		
		Thread.sleep(5000);
		driver.quit();
	}

}
