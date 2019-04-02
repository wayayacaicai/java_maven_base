/**
 * 
 */
package api.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
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
public class MediumGet1Update3 {
	@DataProvider
	public Object[][] getDatas() {
		Object[][] datas = ExcelUtilsUpdate.method_High1("/excel/testExcel.xlsx", 0);
		return datas;
	}

	/**
	 * 
	 * api侵入--沒有和第三方相关的api，用jre的api--系统库，基础开发包 保存多个参数，key--value--map
	 */
	@Test(dataProvider = "getDatas")
	public void Get1Update(String baseUrl, String mobilephone, String pwd, String regname, String expected) {
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
