/**
 * 
 */
package api.medium;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:初步封装
 * @author:zpp
 * @time:2019年3月30日 下午11:52:46
 */
public class MediumGet1Update2 {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = { { "13783948731", "123456", "caicai", "手机号码已被注册" },
				{ "caicai", "123456", "caicai", "手机号码格式不正确" }, { "13783948731", "12345", "caicai", "密码长度必须为6~18" }, };
		return datas;
	}

	/**
	 * 
	 * api侵入--沒有和第三方相关的api，用jre的api--系统库，基础开发包 保存多个参数，key--value--map
	 */
	@Test(dataProvider = "getDatas")
	public void Get1Update(String mobilephone, String pwd, String regname, String expected)
			throws ClientProtocolException, IOException {
		String baseUrl = "http://localhost:12345/futureloan/mvc/api%20/member/register";
		// List<NameValuePair> params = new ArrayList<NameValuePair>();
		// params.add(new BasicNameValuePair("mobilephone", mobilephone));
		// params.add(new BasicNameValuePair("pwd", pwd));
		// params.add(new BasicNameValuePair("regname", regname));

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("mobilephone", mobilephone);
		params.put("pwd", pwd);
		params.put("regname", regname);

		String entityStr = HttpUtilsUpdate.get(baseUrl, params);
		System.out.println(entityStr);

		Assert.assertTrue(entityStr.contains(expected));
	}

	public static void main(String[] args) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("mobilephone", "13783948731"));
		params.add(new BasicNameValuePair("pwd", "123456"));
		params.add(new BasicNameValuePair("regname", "菜菜"));
		String urlParams = URLEncodedUtils.format(params, "utf-8");
		System.out.println(urlParams);
	}
}
