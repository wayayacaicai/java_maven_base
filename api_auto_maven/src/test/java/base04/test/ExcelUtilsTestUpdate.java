package base04.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base04.pojo.ApiCaseDetail;
import base04.utils.DataProviderUtils;
import base04.utils.HttpUtils;

/**
 * @Desc:使用Excel来执行测试用例---使用面向对象---完全抽离
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTestUpdate {
	
	@DataProvider(name="apitest")
	public Object[][] getDatas(){
		return DataProviderUtils.getDatas();
	}
	
	@Test(dataProvider="apitest")
	public void get(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.get(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
	
	@Test(dataProvider="apitest")
	public void post(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
}
