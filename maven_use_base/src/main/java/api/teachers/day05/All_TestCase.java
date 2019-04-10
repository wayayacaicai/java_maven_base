package api.teachers.day05;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		String entityStr = HttpUtils.get(apiCaseDetail);
		//写的单元格在哪？？
		System.out.println(entityStr);
		//怎么拿到行号，什么可以对应找到行号
		//没有行号，什么能确定行号，caseId一定可以确定行号的
//		ExcelUtils.writeExcel("/api_test_case_01.xlsx", "d://api_test_case_2019-4-9.xlsx", 1, apiCaseDetail.getCaseId(), 6,entityStr);
		//在什么时候去一次性把所有的结果全部写回到excel中去
		//在所有测试用例执行完后，所有的单元测试方法执行完后
		//批量回写--》
	}
	
	@AfterSuite
	public void afterSuite(){
		//一次性数据回写（很多数据--》在执行测试用例的时候就要做一个收集）
	}

}
