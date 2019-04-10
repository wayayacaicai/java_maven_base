package api.teachers.day05;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
	
	/**
	 * 封装数据提供者需要的二维数组对象
	 * @return
	 */
	public static Object[][] getCaseDetailList(){
		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
		//读取所有的接口基本信息
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 0, ApiInfo.class);
		
		//key为apiId，值为apiinfo类型的map
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for(ApiInfo apiInfo : apiInfoList){
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		//封装为二维数组中
		Object[][] datas = new Object[apiCaseDetailList.size()][];
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

}
