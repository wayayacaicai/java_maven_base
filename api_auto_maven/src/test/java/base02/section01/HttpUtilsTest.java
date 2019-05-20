package base02.section01;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:请求工具类测试用例
 * @author:zpp 
 * @time:2019年5月20日 下午5:14:05
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
		try {
			String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
			List<NameValuePair> urlParams = new ArrayList<>();
			urlParams.add(new BasicNameValuePair("mobilephone", mobilephone));
			urlParams.add(new BasicNameValuePair("pwd", pwd));

			String actualResult = HttpUtils.get(baseUrl, urlParams);
			Assert.assertTrue(actualResult.contains(expectedResult));
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="getDatas")
	public void post(String mobilephone,String pwd,String expectedResult){
		try {
			String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
			List<NameValuePair> urlParams = new ArrayList<>();
			urlParams.add(new BasicNameValuePair("mobilephone", mobilephone));
			urlParams.add(new BasicNameValuePair("pwd", pwd));

			String actualResult = HttpUtils.post(baseUrl, urlParams);
			Assert.assertTrue(actualResult.contains(expectedResult));
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
