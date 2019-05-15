package web.teachers.day04.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementOptTester {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		/**
		 *  click()
		 *  触发当前元素的点击事件
			clear()
			清空内容
			sendKeys(...)
			往文本框一类元素中写入内容
			element.sendKeys("柠檬班");//输入内容”柠檬班”
			按键操作
			element.sendKeys(Keys.CONTROL,"a");//ctrl+a 全选
			element.sendKeys(Keys.CONTROL,"x");//ctrl+x 剪切
			element.sendKeys(Keys.CONTROL,"c");//ctrl+c 复制
			element.sendKeys(Keys.CONTROL,"v");//ctrl+v 粘贴
			element.sendKeys(Keys.ENTER);//回车
			element.sendKeys(Keys.BACK_SPACE);//删除
			element.sendKeys(Keys.SPACE);//空格键

		 */
		driver.get("http://www.baidu.com");
//		driver.findElement(By.linkText("学术")).click();
//		driver.findElement(By.id("kw")).sendKeys("柠檬班");
//		Thread.sleep(2000);
//		driver.findElement(By.id("kw")).clear();

		
		WebElement input = driver.findElement(By.id("kw"));
//		input.sendKeys("柠檬班");
//		input.sendKeys(Keys.CONTROL,"a");
//		Thread.sleep(2000);
//		input.sendKeys(Keys.CONTROL,"c");
//		Thread.sleep(2000);
//		input.sendKeys(Keys.CONTROL,"v");
//		Thread.sleep(2000);
//		input.sendKeys(Keys.CONTROL,"v");
//		Thread.sleep(2000);
//		input.sendKeys(Keys.CONTROL,"v");
//		getTagName()
//		获取元素的的标签名
//		getAttribute(属性名)
//		根据属性名获取元素属性值
//		getText()
//		获取当前元素的文本值
//		System.out.println(input.getTagName());
//		System.out.println(input.getAttribute("maxlength"));
//		System.out.println(input.getText());
		
//		isDisplayed()
//		查看元素是否显示
//		isEnabled()
//		查看元素是否启用(可编辑)
//		isSelected()
//		查看元素是否被选中
//		System.out.println(input.isDisplayed());
//		System.out.println(input.isEnabled());
//		System.out.println(input.isSelected());
		
		Thread.sleep(5000);
		driver.quit();
	}

}
