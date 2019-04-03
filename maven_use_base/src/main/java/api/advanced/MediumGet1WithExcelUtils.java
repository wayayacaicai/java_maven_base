package api.advanced;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @Desc:最终封装，用http工具类调用
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class MediumGet1WithExcelUtils {
	@DataProvider
	public Object[][] getDatas() {
		String excelPath = "/excel/mediumExcelWork.xlsx";
		Object[][] datas = ExcelUtilsUpdate.readExcel(excelPath, 0);
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public static void get(String baseUrl, String params, String expected) {
		// HashMap<String, String> hm = new HashMap<String, String>();
		// hm.put("mobilephone", mobilephone);
		// hm.put("pwd", pwd);
		// hm.put("regname", regname);
		// String actualResponseBody = HttpUtilsUpdate.get(baseUrl, hm);
		@SuppressWarnings("unchecked")
		Map<String, String> m = (Map<String, String>) JSONObject.parse(params);
		String actualResponseBody = HttpUtilsUpdate.get(baseUrl, m);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}

	@Test(dataProvider = "getDatas")
	public static void post(String baseUrl, String params, String expected) {
		// HashMap<String, String> hm = new HashMap<String, String>();
		// hm.put("mobilephone", mobilephone);
		// hm.put("pwd", pwd);
		// hm.put("regname", regname);
		// String actualResponseBody = HttpUtilsUpdate.post(baseUrl, hm);
		@SuppressWarnings("unchecked")
		Map<String, String> m = (Map<String, String>) JSONObject.parse(params);
		String actualResponseBody = HttpUtilsUpdate.get(baseUrl, m);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}
}
