package api.retry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class DataProviderUtils {
	private static String dataProviderSourceExcelPath;
	private static int sheetIndex0;
	private static int sheetIndex1;
	static{
		try {
			Properties properties = new Properties();
			properties.load(DataProviderUtils.class.getResourceAsStream(dataProviderSourceExcelPath));
			dataProviderSourceExcelPath = properties.getProperty("dataProviderSourceExcelPath");
			int  sheetIndex0 = Integer.parseInt(properties.getProperty("sheetIndex0"));
			int  sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Object[][] getDatas() {
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel(dataProviderSourceExcelPath, 0, ApiInfo.class);
		// 容器转换
		HashMap<String, ApiInfo> apiInfoMap = new HashMap<>();
		for (ApiInfo apiInfo : apiInfoList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}

		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel(dataProviderSourceExcelPath, 1, ApiCaseDetail.class);

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
