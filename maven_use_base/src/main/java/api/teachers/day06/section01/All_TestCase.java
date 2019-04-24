package api.teachers.day06.section01;

import java.io.IOException;
import java.util.List;

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
		System.out.println(entityStr);
		
		//收集要写的数据：把接口的响应写回到对应行的第6列
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, entityStr);
		//添加到容器中
		ApiUtils.addCellData(cellData);
	}
	
	@AfterSuite
	public void afterSuite(){
		//一次性数据回写（很多数据--》在执行测试用例的时候就要做一个收集）
		/**
		 *  往第1行第1列写入aaa--》1条要写的信息
		 *  往第1行第5列写入bbb
		 *  往第2行第6列写入ccc
		 *  往第3行第4列写入dddd
		 *  
		 *  收集这些数据放哪去--》容器--》List<CellData>
		 */
		
		/*List<CellData> cellDatas = ApiUtils.getCellDataList();
		for (CellData cellData : cellDatas) {
			System.out.println(cellData);
		}*/
		
		//批量回写
		ExcelUtils.batchWriteExcel("/api_test_case_01.xlsx", "target/classes/api_test_case_01_w.xlsx", 1);
		
	}

}
