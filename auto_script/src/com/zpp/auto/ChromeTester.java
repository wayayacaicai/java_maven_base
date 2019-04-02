package com.zpp.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import org.openqa.selenium.By;

public class ChromeTester {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://www.baidu.com");

		// WebElement element = driver.findElement(By.id("kw"));

		// WebElement element = driver.findElement(By.className("s_ipt"));

		List<WebElement> elements = driver.findElements(By.tagName("input"));
		System.out.println(elements.size());
		WebElement element = elements.get(7);

		// WebElement element = driver.findElement(By.xpath("//*[@id='kw']"));

		// WebElement element =
		// driver.findElement(By.cssSelector("input[autocomplete='off']"));

		element.sendKeys("Java");

		// WebElement element2 = driver.findElement(By.linkText("新闻"));
		// element2.click();

	}
}
