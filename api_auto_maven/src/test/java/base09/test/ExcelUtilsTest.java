package base09.test;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import base09.pojo.ApiCaseDetail;
import base09.pojo.CellData;
import base09.utils.DataCheckerUtils;
import base09.utils.DataProviderUtils;
import base09.utils.ExcelUtils;
import base09.utils.HttpUtils;
import base09.utils.ParamsOperUtils;

/**
 * @Desc:使用Excel来执行测试用例---使用面向对象---完全抽离
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	@BeforeSuite
	public void beforeSuite(){
		//数据清洗
		
		
		//参数替换准备的数据
		ParamsOperUtils.addGlobalData("mobilephone", "13999888819");
		ParamsOperUtils.addGlobalData("pwd", "123456");
		ParamsOperUtils.addGlobalData("nickname", "test");
	}
	
	@Test(dataProvider="apitest",dataProviderClass=DataProviderUtils.class)
	public void get(ApiCaseDetail apiCaseDetail){
		//先置为无验证的数据
		DataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 7, "无验证"));
		
		//前向数据验证
		DataCheckerUtils.check(apiCaseDetail,"before");
		//执行用例
		String actualResult = HttpUtils.request(apiCaseDetail);
		//后向数据验证
		DataCheckerUtils.check(apiCaseDetail,"after");
		//添加用例实际结果
		DataProviderUtils.addCellData(new CellData(apiCaseDetail.getRowNo(), 6, actualResult));
		//断言
		System.out.println(actualResult);
//		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedResponseData()));
		
	}

	@AfterSuite
	public void afterSuite(){
		//一次性回写数据，在执行测试用例之后做一个收集，回写测试用例和数据验证的结果
		List<CellData> cellDataList = DataProviderUtils.getCellDataList();
		List<CellData> sqlCheckerList = DataProviderUtils.getSqlCheckerList();
		ExcelUtils.writeExcelAll("src/test/resources/base09/testcase.xlsx",
				"target/base09/testcase.xlsx",
				1, cellDataList,2,sqlCheckerList);
	}
}
