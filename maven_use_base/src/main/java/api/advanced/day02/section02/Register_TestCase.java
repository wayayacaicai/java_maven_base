package api.advanced.day02.section02;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Register_TestCase {
//	硬编码--》写死的--》代码--》抽取出去--》不要放代码--》文件（properties、xml、excel）、数据库
	@DataProvider
	public Object[][] getDatas(){
//		Object[][] data = {
//				{"13888888888","123456","happy","手机号码已被注册"},
//				{"lemon","123456","happy","手机号码格式不正确"},
//				{"13888888888","12345","happy","密码长度必须为6~18"},
//		};
		
		Object[][] datas = ExcelUtils.readExcel("/testcase.xlsx",0);
		for (Object[] objects : datas) {
			for (Object object : objects) {
//				System.out.print(object + "              ");
			}
//			System.out.println();
		}
		return datas;
	}
	
	//api侵入--》没有和第三方相关的api--》用自己的api、用jre的api--》系统库、基础开发包
	//保存多个参数：key-value--》map
	@Test(dataProvider="getDatas")
	public void test_case_1(String baseUrl,String mobilephone,String pwd,String regName,String expectedResult) throws ClientProtocolException, IOException {
		HashMap<String, String> parameters = new HashMap<String, String>();
		parameters.put("mobilephone", mobilephone);
		parameters.put("pwd", pwd);
		parameters.put("regname", regName);
		
		String entityStr = HttpUtils.get(baseUrl, parameters);
		Assert.assertTrue(entityStr.contains(expectedResult));
	}
}
