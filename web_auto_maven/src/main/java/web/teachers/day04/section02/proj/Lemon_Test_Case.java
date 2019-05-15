package web.teachers.day04.section02.proj;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Lemon_Test_Case extends Base_Test_Case {
	@Test
	public void test_case_01() throws InterruptedException {
		//到登录页面
		to("http://test.lemonban.com/lmcanon_web_auto/mng/login.html");
		//输入手机号
		type(By.id("mobilephone"), "13825161923");
		//输入密码
		type(By.id("password"), "123456");
		//单击登录按钮
		click(By.id("login"));
		//点击排课管理
		click(By.id("menu-product"));
		//点击一周排课
		click(By.linkText("一周排课"));
		//点击老师信息管理
		click(By.id("menu-teacher"));
		//点击老师信息
		click(By.linkText("老师信息"));
	}

}
