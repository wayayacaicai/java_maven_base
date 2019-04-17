package api.retry.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import api.retry.pojo.ApiCaseDetail;
import api.retry.pojo.ApiInfo;
import api.retry.testcase.RequestTestCase;

public class DataProviderUtils {
	private static String dataProviderSourceExcelPath;
	private static int sheetIndex0;
	private static int sheetIndex1;
	private static Logger logger = Logger.getLogger(DataProviderUtils.class);
	static{
		try {
			Properties properties = new Properties();
			properties.load(DataProviderUtils.class.getResourceAsStream("/config/ApiConfig.properties"));
			dataProviderSourceExcelPath = properties.getProperty("dataProviderSourceExcelPath");
			sheetIndex0 = Integer.parseInt(properties.getProperty("sheetIndex0"));
			sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
		} catch (IOException e) {
//			e.printStackTrace();
			logger.error("配置文件读取出现异常" + e.getMessage());
		}
	}
	public static Object[][] getDatas() {
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel(dataProviderSourceExcelPath, sheetIndex0, ApiInfo.class);
		// 容器转换
		HashMap<String, ApiInfo> apiInfoMap = new HashMap<>();
		for (ApiInfo apiInfo : apiInfoList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}

		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel(dataProviderSourceExcelPath, sheetIndex1, ApiCaseDetail.class);

		Object[][] allDatas = new Object[apiCaseDetailList.size()][];
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); // 得到apiInfo对象

			Object[] object = new Object[] { apiCaseDetail }; // 一维数组
			allDatas[i] = object; // 二维数组
		}
		return allDatas;
	}
}
