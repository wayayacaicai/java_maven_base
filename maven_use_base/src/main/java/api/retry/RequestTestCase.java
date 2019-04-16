package api.retry;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RequestTestCase {
	@DataProvider
	public Object[][] getDatas() {
		return DataProviderUtils.getDatas();
	}

	@Test(dataProvider = "getDatas")
	public void get(ApiCaseDetail apiCaseDetail) {
		String actualResponseData = HttpUtils.get(apiCaseDetail);
		ExcelUtils.writeExcelData("src/main/resources/retry1.xlsx", "src/main/resources/retry1.xlsx", 1, apiCaseDetail.getCaseId(), 6, actualResponseData);
		Assert.assertTrue(actualResponseData.contains(apiCaseDetail.getExpectedResponseData()));
	}

	//@Test(dataProvider = "getDatas")
	public void post(ApiCaseDetail apiCaseDetail) {
		String actualResponseData = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(actualResponseData.contains(apiCaseDetail.getExpectedResponseData()));
	}
}
