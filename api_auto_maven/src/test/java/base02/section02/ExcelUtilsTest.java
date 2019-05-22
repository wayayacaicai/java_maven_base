package base02.section02;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Desc:使用Excel来执行测试用例
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	@DataProvider(name="apitest")
	public Object[][] getDatas(){
		Object[][] allDatas = ExcelUtils.readExcel("/base02/testcase.xlsx", 0);
		return allDatas;
	}
	
	@Test(dataProvider="apitest")
	public void get(String baseUrl,String mobilephone,String pwd,String regname,String expectedResult){
		HashMap<String, String> hmParams = new HashMap<>();
		hmParams.put("mobilephone", mobilephone);
		hmParams.put("pwd", pwd);
		hmParams.put("regname", regname);
		String actualResult = HttpUtils.get(baseUrl, hmParams);
		Assert.assertTrue(actualResult.contains(expectedResult));
	}
	
	@Test(dataProvider="apitest")
	public void post(String baseUrl,String mobilephone,String pwd,String regname,String expectedResult){
			HashMap<String, String> hmParams = new HashMap<>();
			hmParams.put("mobilephone", mobilephone);
			hmParams.put("pwd", pwd);
			hmParams.put("regname", regname);
			String actualResult = HttpUtils.post(baseUrl, hmParams);
			Assert.assertTrue(actualResult.contains(expectedResult));
	}
}
