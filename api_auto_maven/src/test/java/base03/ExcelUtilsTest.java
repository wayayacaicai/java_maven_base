package base03;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * @Desc:使用Excel来执行测试用例
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	@DataProvider(name="apitest")
	public Object[][] getDatas(){
		Object[][] allDatas = ExcelUtils.readExcel("/base03/testcase.xlsx", 0);
		return allDatas;
	}
	
	@Test(dataProvider="apitest")
	public void get(String baseUrl,String urlParamsJson,String expectedResult){
		Map<String, String> urlParams = (Map<String, String>)JSONObject.parse(urlParamsJson);
		String actualResult = HttpUtils.get(baseUrl, urlParams);
		Assert.assertTrue(actualResult.contains(expectedResult));
	}
	
	@Test(dataProvider="apitest")
	public void post(String baseUrl,String urlParamsJson,String expectedResult){
		Map<String, String> urlParams = (Map<String, String>)JSONObject.parse(urlParamsJson);
		String actualResult = HttpUtils.post(baseUrl, urlParams);
			Assert.assertTrue(actualResult.contains(expectedResult));
	}
}
