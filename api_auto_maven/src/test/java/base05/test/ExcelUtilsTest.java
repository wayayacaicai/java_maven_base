package base05.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base05.pojo.ApiCaseDetail;
import base05.utils.DataProviderUtils;
import base05.utils.HttpUtils;

/**
 * @Desc:使用Excel来执行测试用例---使用面向对象---完全抽离
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	@Test(dataProvider="apitest",dataProviderClass=DataProviderUtils.class)
	public void get(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.get(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
	
	@Test(dataProvider="apitest",dataProviderClass=DataProviderUtils.class)
	public void post(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
}
