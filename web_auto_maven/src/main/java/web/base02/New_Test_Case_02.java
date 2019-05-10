package web.base02;

import org.testng.annotations.Test;

public class New_Test_Case_02 extends BaseBrowserAttrs{
	@Test
	public void test_case_01(){
		toPage("http://www.hao123.com");
	}
	
	@Test
	public void test_case_02(){
		toPage("http://lib.culr.edu.cn/");
	}
}
