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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @Desc:初步封装
 * @author:zpp
 * @time:2019年3月30日 下午11:52:46
 */
public class MediumGet1 {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "mobilephone=13783948731&pwd=123456&regname=caicai", "手机号码已被注册" },
				{ "mobilephone=caicai&pwd=123456&regname=caicai", "手机号码格式不正确" },
				{ "mobilephone=13783948731&pwd=12345&regname=caicai", "密码长度必须为6~18" }, };
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public void Get1(String params, String expected) throws ClientProtocolException, IOException {
		String baseUrl = "http://localhost:12345/futureloan/mvc/api%20/member/register";
		HttpGet get = new HttpGet(baseUrl + "?" + params);
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = httpClient.execute(get);

		HttpEntity entity = response.getEntity();
		String entityStr = EntityUtils.toString(entity);
		System.out.println(entityStr);

		Assert.assertTrue(entityStr.contains(expected));
	}

	public static void main(String[] args) throws Exception {
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("mobilephone", "13783948731"));
		// params.add(new BasicNameValuePair("pwd", "123456"));
		// params.add(new BasicNameValuePair("regname", "菜菜"));
		// String urlParams = URLEncodedUtils.format(params, "utf-8");
		// System.out.println(urlParams);

//		jsonStudy();
	}

	private static void jsonStudy() {
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < 3; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("mobilephone", "1" + i);
			jsonObject.put("pwd", "123456" + i);
			jsonObject.put("regname", "菜菜" + i);
			jsonArray.add(jsonObject);
		}
		for (int j = 0; j < 3; j++) {
			String jsonStr = jsonArray.get(j).toString();
			System.out.println(jsonStr);
		}
	}
}
