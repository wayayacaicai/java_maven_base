package web.teachers.day01.section02;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTester {

	/**
	 * 推荐学习期间：selenium2.53.1 + Chrome版本 60.0.3112.11
	 * 
	 * Chrome浏览器是一定要设置Chrome驱动文件的路径！！！！
	 * 
	 * 1：https://docs.seleniumhq.org/download/
	 * 2：找到Third Party Drivers, Bindings, and Plugins
	 * 3：找到Google Chrome Driver
	 * 4：看change log：https://sites.google.com/a/chromium.org/chromedriver/downloads
	 * 5：下载对应的驱动文件：https://sites.google.com/a/chromium.org/chromedriver/downloads
	 * 					或者：https://chromedriver.storage.googleapis.com/index.html
	 * 6：驱动文件放置到资源目录
	 * 7：System.setProperty("webdriver.chrome.driver", "xxx");
	 *chrome driver版本	chrome浏览器
				v2.31	v58-60
				v2.30	v58-60
				v2.29	v56-58
				v2.28	v55-57
				v2.27	v54-56
				v2.26	v53-55
				v2.25	v53-55
				v2.24	v52-54
				v2.23	v51-53
				v2.22	v49-52
				v2.21	v46-50
				v2.20	v43-48
				v2.19	v43-47
				v2.18	v43-46
				
	chrome浏览器各版本
		http://www.chromedownloads.net/chrome64win/
		驱动下载
		进入selenium网站：http://www.seleniumhq.org/download/
	禁止谷歌浏览器更新
		https://jingyan.baidu.com/article/76a7e409f2137afc3b6e15be.html
	镜像网站：
		https://npm.taobao.org/
		ChromeDriver 镜像:http://npm.taobao.org/mirrors/chromedriver
		Selenium 镜像: http://npm.taobao.org/mirrors/selenium		
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Exception in thread "main" java.lang.IllegalStateException: 
		//The path to the driver executable must be set by the webdriver.chrome.driver system property; for more information, see https://github.com/SeleniumHQ/selenium/wiki/ChromeDriver. The latest version can be downloaded from http://chromedriver.storage.googleapis.com/index.html
		//设置驱动文件路径的属性
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		//打开chrome浏览器
		ChromeDriver driver = new ChromeDriver();
	}

}
