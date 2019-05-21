package base02.section02;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:测试用例执行类
 * @author:zpp 
 * @time:2019年5月21日 下午4:59:10
 */
public class HttpUtilsTest {
	@DataProvider
	public Object[][] getDatas(){
		Object[][] allDatas = {
				{"13699999999","123456","手机号码已被注册"},
				{"1369999999","123456","手机号码格式不正确"},
				{"13699999999","12345","密码长度必须为6~18"},
		};
		return allDatas;
	}
	
	
	@Test(dataProvider="getDatas")
	public void get(String mobilephone,String pwd,String expectedResult){
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		HashMap<String, String> hmParams = new HashMap<>();
		hmParams.put("mobilephone", mobilephone);
		hmParams.put("pwd", pwd);
		String actualResult = HttpUtils.get(baseUrl, hmParams);
		Assert.assertTrue(actualResult.contains(expectedResult));
	}
	
	@Test(dataProvider="getDatas")
	public void post(String mobilephone,String pwd,String expectedResult){
			String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
			HashMap<String, String> hmParams = new HashMap<>();
			hmParams.put("mobilephone", mobilephone);
			hmParams.put("pwd", pwd);
			String actualResult = HttpUtils.post(baseUrl, hmParams);
			Assert.assertTrue(actualResult.contains(expectedResult));
	}
}
