package api.advanced.json;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 当excel结构变化：增加、删除列、顺序调整 2：大量的冗余，不好维护--》怎么解决冗余，重复的
 * 数据库冗余：一个信息系统所有数据都放到一个表中--》通过多个表、设计关联字段解决的
 * 
 * @Desc:结合json数据进行请求
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class ApiTestWithExcelAndJson {
	@DataProvider
	public Object[][] getDatas() {
		String excelPath = "/excel/mediumExcelWork.xlsx";
		Object[][] datas = ExcelUtilsJson.readExcel(excelPath, 0);
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public static void get(String baseUrl, String params, String expected) {
		// HashMap<String, String> hm = new HashMap<String, String>();
		// hm.put("mobilephone", mobilephone);
		// hm.put("pwd", pwd);
		// hm.put("regname", regname);
		// String actualResponseBody = HttpUtilsUpdate.get(baseUrl, hm);

		// 通过注入进来的apiId，到第1个sheet去找
		// 数据是excel中，然后我们读取出来时保存到了二维数组对象--》遍历这个数组中的每个元素，一一匹配
		// 给我一个apiId，我就能知道对应的api的基本信息
		@SuppressWarnings("unchecked") // json字符串转换为对象
		Map<String, String> m = (Map<String, String>) JSONObject.parse(params);
		String actualResponseBody = HttpUtilsJson.get(baseUrl, m);
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
		String actualResponseBody = HttpUtilsJson.get(baseUrl, m);
		Assert.assertTrue(actualResponseBody.contains(expected));
	}
}
