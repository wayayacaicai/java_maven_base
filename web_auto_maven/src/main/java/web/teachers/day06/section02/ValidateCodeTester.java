package web.teachers.day06.section02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

/**
 * 验证码咋解决：
 * 		1：自动识别验证码--》不建议
 * 		2：去除验证码（测试环境、预生产环境、生产环境）
 * 		3：万能验证码（后门：题库App后台：LM19   ，  前程贷：hapi）
 * @author happy
 * @date 2019年5月18日
 * @desc 
 * @email
 */
public class ValidateCodeTester{
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		
		
		Thread.sleep(5000);
		driver.quit();
	}
}
