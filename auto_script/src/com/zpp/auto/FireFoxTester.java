package com.zpp.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxTester {

	public static void main(String[] args) {
		System.setProperty("webdriver.firefox.bin", "D:\\normal_soft\\Browser\\FireFox\\fire46.0.1\\firefox.exe");

		WebDriver driver = new FirefoxDriver();

		driver.get("http://www.baidu.com");

		driver.close();
	}

}
