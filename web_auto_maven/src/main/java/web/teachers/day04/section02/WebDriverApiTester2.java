package web.teachers.day04.section02;

import java.util.concurrent.TimeUnit;

import org.apache.xalan.templates.ElemWhen;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.jna.platform.win32.BaseTSD.SSIZE_T;


public class WebDriverApiTester2 {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
//		driver.manage()
//		此方法可以获取Options--浏览器菜单操作对象,如完成浏览器的cookie设置
//		Options options = driver.manage();

//		driver.manage().timeouts()
//		此方法可以获取TimeOuts--驱动超时对象，可进行多种场景的等待超时设置
//		Timeouts timeouts = driver.manage().timeouts();
//		timeouts.implicitlyWait(time, unit)
//		timeouts.pageLoadTimeout(2, TimeUnit.SECONDS);
		//需求：页面必须在2s之内打开
	
//		try {
//			driver.get("http://120.78.128.25:8765/");
//		} catch (TimeoutException e) {
//			//页面加载超时
//			System.out.println("页面加载超时，要提bug了");
//		}
//		
//		获取window对象
//		Window window = driver.manage().window();
//		driver.manage().window()
//		此方法可以获取Window--当前窗口对象，可进行窗口大小设置
		driver.get("http://www.baidu.com");
		Window window = driver.manage().window();
//		窗口最大化
//		maximize()
//		window.maximize();
		//窗口最大化
//		driver.manage().window().maximize();
		
//		窗口位置
//		getPosition()
//		Point point = window.getPosition();
//		System.out.println(point.getX());
//		System.out.println(point.getY());
//		窗口大小
//		getSize()
		Dimension size = window.getSize();
		System.out.println(size.getHeight());
		System.out.println(size.getWidth());
//		设置位置
//		setPosition(targetPosition)
		Point targetPosition = new Point(100, 100);
		window.setPosition(targetPosition);
//		设置窗口大小
//		setSize(targetSize)
		Dimension targetSize = new Dimension(300, 300);
		window.setSize(targetSize);

		
		Thread.sleep(10000);
		driver.quit();
	}

}
