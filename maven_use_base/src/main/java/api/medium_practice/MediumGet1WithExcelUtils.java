package api.medium_practice;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:最终封装，用http工具类调用
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class MediumGet1WithExcelUtils {
	@DataProvider
	public Object[][] getDatas() {
		String excelPath = "/excel/baseExcelWork.xlsx";
		Object[][] datas = ExcelUtilsUpdate.readExcel(excelPath, 0);
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public static void get(String baseUrl, String mobilephone, String pwd, String regname, String expected) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("mobilephone", mobilephone);
		hm.put("pwd", pwd);
		hm.put("regname", regname);
		String actualResponseBody = HttpUtilsUpdate.get(baseUrl, hm);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}

	@Test(dataProvider = "getDatas")
	public static void post(String baseUrl, String mobilephone, String pwd, String regname, String expected) {
		HashMap<String, String> hm = new HashMap<String, String>();
		hm.put("mobilephone", mobilephone);
		hm.put("pwd", pwd);
		hm.put("regname", regname);
		String actualResponseBody = HttpUtilsUpdate.post(baseUrl, hm);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}
}
