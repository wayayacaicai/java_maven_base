package base02.section01;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:数据提供者--基本get请求升级版
 * @author:zpp 
 * @time:2019年5月20日 下午5:13:00
 */
public class HttpGetTestUpdate {
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
			List<NameValuePair> url_params = new ArrayList<>();
			url_params.add(new BasicNameValuePair("mobilephone", mobilephone));
			url_params.add(new BasicNameValuePair("pwd", pwd));
			String url_params_str = URLEncodedUtils.format(url_params, "utf-8");
			HttpGet get = new HttpGet(baseUrl + "?" + url_params_str);
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
