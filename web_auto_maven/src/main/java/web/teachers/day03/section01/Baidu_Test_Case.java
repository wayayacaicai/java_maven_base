package web.teachers.day03.section01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Baidu_Test_Case extends Base_Test_Case {

	/**
	 * 相对用的比较少一点的：
	 * 根据tagName
			By.cssSelector("input")
		根据ID
			By.cssSelector("input#id"); 使用html标签拼上#id
			By.cssSelector("#id");仅使用#id
		根据className(样式名)，.class形式
			By.cssSelector(".className");
			By.cssSelector("input.className"); 标签拼上样式
	用的多一点的：（必须要掌握的）
		根据元素属性,属性名=属性值,id,class,等都可写成这种形式
			By.cssSelector("标签名[属性名='属性值']");
		如：By.cssSelector("input[name='xxx']")
		是否存在属性
			By.cssSelector("标签名[属性名='属性值']");
		多属性
			By.cssSelector("标签名[属性1='属性值'][属性2='属性值'][属性3='属性值']");
		...
	 */
	@Test
	public void test_case_01() {
//		to("http://www.baidu.com");
//		WebDriver driver = getDriver();
		//7:cssSelector
//		driver.findElement(By.cssSelector("input.s_ipt")).sendKeys("柠檬班");;
		//<input id="kw" name="wd" class="s_ipt" value="" maxlength="255" autocomplete="off">
		//maxlength="255"
//		driver.findElement(By.cssSelector("input[maxlength=\"255\"]")).sendKeys("柠檬班软件测试");
//		driver.findElement(By.cssSelector("input[maxlength='255']")).sendKeys("柠檬班软件测试");
//		driver.findElement(By.cssSelector("input[maxlength='255'][autocomplete='off']")).sendKeys("柠檬班软件测试");
		
		//input标签的路径：   html/body/div[1]/div[1]/div[1]/div[1]/div[1]/form/span/input
		//1）xpath绝对路径，从html开始一级一级的找
//		driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[1]/div[1]/form/span/input")).sendKeys("腾讯课堂柠檬班");
		//2)  //*[@id="kw"]
//		driver.findElement(By.xpath("//*[@id=\"kw\"]")).sendKeys("柠檬班&腾讯课堂");
		
		//前程贷首页
		to("http://120.78.128.25:8765/Index/login.html");
		WebDriver driver = getDriver();
		/**
		 通过元素名定位
		//input 获取页面所有input元素
		
		通过元素名+索引定位
		//form/div[1]/input 获取手机号输入框
		
		使用元素名+属性
		//*[@name="phone"]  获取手机号输入框
		
		使用元素(html元素-->标签)名+包含部分属性值
		//*[contains(@name,'one')] 获取手机号输入框
		
		使用元素名+元素的文本内容  <xxx>text</xxx>
		//*[text()='免费注册']  获取免费注册超链接，注意空格
		
		使用元素名+包含元素的部分文本内容
		//*[contains(text(),'免费')] 获取免费注册超链接
		 
		 */
		//<input type="text" name="phone" placeholder="手机号" datatype="m" nullmsg="请输入手机号" 
		//errormsg="请输入正确的手机号" class="form-control username" value="">
		
//		driver.findElement(By.xpath("/html/body/div[2]/div/form/div[1]/input")).sendKeys("13888888888");
//		driver.findElement(By.xpath("//form/div/input")).sendKeys("13888886666");
//		driver.findElement(By.xpath("//*[@placeholder='手机号']")).sendKeys("13888887777");
//		driver.findElement(By.xpath("//*[contains(@name,'hone')]")).sendKeys("13888889999");
//		driver.findElement(By.xpath("//*[contains(text(),'费注')]")).click();
//		driver.findElement(By.xpath("//*[@placeholder='手机号' and @datatype='m']")).sendKeys("13888881111");
//		driver.findElement(By.xpath("//*[@placeholder='手机号111' or @datatype='mobile']")).sendKeys("13888882222");
		
		//and  or
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
