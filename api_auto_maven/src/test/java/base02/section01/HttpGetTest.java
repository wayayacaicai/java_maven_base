package base02.section01;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:数据提供者--基本get请求
 * @author:zpp 
 * @time:2019年5月20日 下午5:12:33
 */
public class HttpGetTest {
	@DataProvider
	public Object[][] getDatas(){
		Object[][] allDatas = {
				{"mobilephone=13699999999&pwd=123456","手机号码已被注册"},
				{"mobilephone=1369999999&pwd=123456","手机号码格式不正确"},
				{"mobilephone=13699999999&pwd=12345","密码长度必须为6~18"},
		};
		return allDatas;
	}
	
	
	@Test(dataProvider="getDatas")
	public void get(String urlParams,String expectedResult){
		try {
			String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
			HttpGet get = new HttpGet(baseUrl + "?" + urlParams);
			CloseableHttpClient client = HttpClients.createDefault();
			CloseableHttpResponse response = client.execute(get);
			
			HttpEntity entity = response.getEntity();
			String entityStr = EntityUtils.toString(entity);
			System.out.println(entityStr);
			Assert.assertTrue(entityStr.contains(expectedResult));
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
