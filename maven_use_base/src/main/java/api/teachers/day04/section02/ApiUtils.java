package api.teachers.day04.section02;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
	
	/**
	 * 封装数据提供者需要的二维数组对象
	 * @return
	 */
	public static Object[][] getCaseDetailList(){
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
		//读取所有的接口基本信息
		List<Object> apiInfoList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 0, ApiInfo.class);
		
		//key为apiId，值为apiinfo类型的map
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for(Object obj : apiInfoList){
			ApiInfo apiInfo = (ApiInfo) obj;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		//封装为二维数组中
		Object[][] datas = new Object[apiCaseDetailList.size()][];
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail) apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

}
