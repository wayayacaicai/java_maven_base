package api.teachers.day06.section01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiUtils {
	
	//保存要写的数据的容器
	public static List<CellData> cellDataList = new ArrayList<CellData>();
	
	
	public static List<CellData> getCellDataList() {
		return cellDataList;
	}

	/**
	 * 添加一个要写的数据
	 * 从果树摘了一个水果，放到篮子中去
	 * @param cellData
	 */
	public static void addCellData(CellData cellData) {
		cellDataList.add(cellData);
	}
	



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
			//apiInfoMap.get(apiCaseDetail.getApiId())
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

}
