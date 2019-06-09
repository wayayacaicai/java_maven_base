package base06.section02.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import base06.section02.pojo.ApiCaseDetail;
import base06.section02.pojo.CellData;
import base06.section02.utils.DataProviderUtils;
import base06.section02.utils.ExcelUtils;
import base06.section02.utils.HttpUtils;

/**
 * @Desc:使用Excel来执行测试用例---使用面向对象---完全抽离
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	
	@Test(dataProvider="apitest",dataProviderClass=DataProviderUtils.class)
	public void get(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.request(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	
		DataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 6, actualResult));
	}

	@AfterSuite
	public void afterSuite(){
		//一次性回写数据，在执行测试用例之前做一个收集
		List<CellData> cellDataList = DataProviderUtils.getCellDataList();
		ExcelUtils.writeExcelAll("src/test/resources/base06/testcase2.xlsx", "target/base06/testcase2.xlsx", 1, cellDataList);
	}
}
