package api.teachers.day04.section02;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
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
	public void test_case_1(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		String entityStr = HttpUtils.get(apiCaseDetail);
		System.out.println(entityStr);
	}

}
