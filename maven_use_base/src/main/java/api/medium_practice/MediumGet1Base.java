package api.medium_practice;

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
 * @Desc:初步封装
 * @author:zpp 
 * @time:2019年4月1日 下午9:07:16
 */
public class MediumGet1Base {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "mobilephone=13783948731&pwd=123456&regname=caicai", "手机号码已被注册" },
				{ "mobilephone=caicai&pwd=123456&regname=caicai", "手机号码格式不正确" },
				{ "mobilephone=13783948731&pwd=12345&regname=caicai", "密码长度必须为6~18" }, };
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public static void get(String params, String expected) throws Exception {
		String baseUrl = "http://120.78.128.25:8080/futureloan/mvc/api/member/register";
		HttpGet get = new HttpGet(baseUrl + "?" + params);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get);
		HttpEntity responseEntity = response.getEntity();
		String responseEntityStr = EntityUtils.toString(responseEntity);
		Assert.assertTrue(responseEntityStr.contains(expected));
	}
}
