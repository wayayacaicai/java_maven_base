package base06.section01.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import base06.section01.pojo.ApiCaseDetail;
import base06.section01.pojo.CellData;
import base06.section01.utils.DataProviderUtils;
import base06.section01.utils.ExcelUtils;
import base06.section01.utils.HttpUtils;

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
		
		//一直在重新生成新的excel，并且覆盖原来的excel，所以最后只能生成最后一个用例的结果
//		ExcelUtils.writeExcel("src/test/resources/base05/testcase.xlsx", "target/testcase_write.xlsx", 1, apiCaseDetail.getCaseId(), 6, actualResult);
		
		//要用同一个excel来重复接收，因为这个方法写入一次要进行一次io操作（对这个excel）
//		ExcelUtils.writeExcel("target/base05/testcase.xlsx", "target/base05/testcase.xlsx", 1, apiCaseDetail.getCaseId(), 6, actualResult);
	
		DataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 6, actualResult));
	}
	
	//@Test(dataProvider="apitest",dataProviderClass=DataProviderUtils.class)
	public void post(ApiCaseDetail apiCaseDetail){
		String actualResult = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
	
	@AfterSuite
	public void afterSuite(){
		//一次性回写数据，在执行测试用例之前做一个收集
		List<CellData> cellDataList = DataProviderUtils.getCellDataList();
		ExcelUtils.writeExcelAll("src/test/resources/base06/testcase.xlsx", "target/base06/testcase.xlsx", 1, cellDataList);
	}
}
