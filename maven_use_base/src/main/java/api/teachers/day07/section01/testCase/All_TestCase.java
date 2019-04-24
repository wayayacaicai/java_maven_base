package api.teachers.day07.section01.testCase;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.teachers.day07.section01.pojo.ApiCaseDetail;
import api.teachers.day07.section01.pojo.CellData;
import api.teachers.day07.section01.utils.ApiUtils;
import api.teachers.day07.section01.utils.DataCheckUtils;
import api.teachers.day07.section01.utils.ExcelUtils;
import api.teachers.day07.section01.utils.HttpUtils;



/**
 * 执行测试用例
 * @author happy
 * @date 2019年4月2日
 * @desc 
 * @email
 */
public class All_TestCase {
	@DataProvider
	public Object[][] getDatas() {
		return ApiUtils.getCaseDetailList();
	}

	@Test(dataProvider = "getDatas")
	public void execute_test_case(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		//1：前置验证
		DataCheckUtils.beforeCheck(apiCaseDetail);
		
		String entityStr = HttpUtils.request(apiCaseDetail);
		
		//2:后置验证
		DataCheckUtils.afterCheck(apiCaseDetail);
		
//		System.out.println(entityStr);
		//收集要写的数据：把接口的响应写回到对应行的第6列
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, entityStr);
		//添加到容器中
		ApiUtils.addCellData(cellData);
//		System.out.println("--------------------------------------------");
	}
	
	@AfterSuite
	public void afterSuite(){
		//批量回写响应结果
//		ExcelUtils.batchWriteExcel("/api_test_case_01.xlsx", "target/classes/api_test_case_01_w.xlsx", 1);
		//批量回写sql验证的结果
//		ExcelUtils.batchWriteExcel2("/api_test_case_01.xlsx", "target/classes/api_test_case_01_sql.xlsx", 2);
		
		ExcelUtils.batchWriteExcel("/api_test_case_01.xlsx", "target/classes/api_test_case_01_all.xlsx");
	}

}
