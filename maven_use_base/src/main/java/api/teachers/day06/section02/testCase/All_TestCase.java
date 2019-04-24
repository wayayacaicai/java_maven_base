package api.teachers.day06.section02.testCase;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.teachers.day06.section02.pojo.ApiCaseDetail;
import api.teachers.day06.section02.pojo.CellData;
import api.teachers.day06.section02.utils.ApiUtils;
import api.teachers.day06.section02.utils.ExcelUtils;
import api.teachers.day06.section02.utils.HttpUtils;

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
		String entityStr = HttpUtils.request(apiCaseDetail);
		System.out.println(entityStr);
		//收集要写的数据：把接口的响应写回到对应行的第6列
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, entityStr);
		System.out.println(apiCaseDetail.getAfterSql());
		System.err.println(apiCaseDetail.getBeforeSql());
		//添加到容器中
		ApiUtils.addCellData(cellData);
	}
	
	@AfterSuite
	public void afterSuite(){
		//批量回写
		ExcelUtils.batchWriteExcel("/api_test_case_01.xlsx", "target/classes/api_test_case_01_w.xlsx", 1);
	}

}
