package api.retry.testcase;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.retry.pojo.ApiCaseDetail;
import api.retry.utils.DataProviderUtils;
import api.retry.utils.ExcelUtils;
import api.retry.utils.HttpUtils;

public class RequestTestCase {
	private static String sourceExcelPath;
	private static String targetExcelPath;
	private static int sheetIndex1;
	private static int cellNo;
	private static Logger logger = Logger.getLogger(RequestTestCase.class);
	static{
		try {
			Properties properties = new Properties();
			properties.load(DataProviderUtils.class.getResourceAsStream("/config/ApiConfig.properties"));
			sourceExcelPath = properties.getProperty("sourceExcelPath");
			targetExcelPath = properties.getProperty("targetExcelPath");
			sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
			cellNo = Integer.parseInt(properties.getProperty("cellNo"));
		} catch (IOException e) {
//			e.printStackTrace();
			logger.error("配置文件读取出现异常" + e.getMessage());
		}
	}
	
	@DataProvider
	public Object[][] getDatas() {
		return DataProviderUtils.getDatas();
	}

	@Test(dataProvider = "getDatas")
	public void get(ApiCaseDetail apiCaseDetail) {
		String actualResponseData = HttpUtils.get(apiCaseDetail);
		ExcelUtils.writeExcelData(sourceExcelPath, targetExcelPath, sheetIndex1, apiCaseDetail.getCaseId(), cellNo, actualResponseData);
		Assert.assertTrue(actualResponseData.contains(apiCaseDetail.getExpectedResponseData()));
	}

	//@Test(dataProvider = "getDatas")
	public void post(ApiCaseDetail apiCaseDetail) {
		String actualResponseData = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(actualResponseData.contains(apiCaseDetail.getExpectedResponseData()));
	}
}
