/**
 * 
 */
package api.medium;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:TODO
 * @author:zpp
 * @time:2019年3月31日 上午12:09:13
 */
public class MediumGet2 {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "mobilephone=13783948731&pwd=123456", "登录成功" },
				{ "mobilephone=caicai&pwd=123456", "用户名或密码错误" }, { "mobilephone=13783948731&pwd=12345", "用户名或密码错误" }, };
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public void Get2(String params, String expected) throws ClientProtocolException, IOException {
		String baseUrl = "http://localhost:12345/futureloan/mvc/api%20/member/login";
		HttpGet get = new HttpGet(baseUrl + "?" + params);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get);

		HttpEntity entity = response.getEntity();
		String entityStr = EntityUtils.toString(entity);
		System.out.println(entityStr);

		Assert.assertTrue(entityStr.contains(expected));
	}
}
