package api.teachers.day08.section01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.teachers.day08.section01.pojo.ApiCaseDetail;
import api.teachers.day08.section01.pojo.ApiInfo;
import api.teachers.day08.section01.pojo.CellData;
import api.teachers.day08.section01.pojo.SqlChecker;

public class ApiUtils {
	
	//保存要写的数据的容器
	public static List<CellData> cellDataList = new ArrayList<CellData>();
	public static List<CellData> sqlCellDataList = new ArrayList<CellData>();
	
	
	public static List<CellData> getSqlCellDataList() {
		return sqlCellDataList;
	}

	/**
	 * 添加一个要写的数据
	 * 从果树摘了一个水果，放到篮子中去
	 * @param cellData
	 */
	public static void addSqlCellData(CellData cellData) {
		sqlCellDataList.add(cellData);
	}
	
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
		//读取所有的接口测试用例详细信息
		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
		//读取所有的接口基本信息
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 0, ApiInfo.class);
		//读取所有的数据验证的信息
		List<SqlChecker> sqlCheckerList = (List<SqlChecker>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 2, SqlChecker.class);
		
		//整理数据验证的信息，不同的case的前置、后置sql放不同的列表去
		Map<String, List<SqlChecker>> sqlCheckerMap = new HashMap<String, List<SqlChecker>>();
		//循环每一个数据验证信息
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//需要方法哪个对应的篮子
			String key = sqlChecker.getCaseId() + "-" + sqlChecker.getType();
			//拿到1-before这个key对应的容器
			List<SqlChecker> subSqlCheckerList =  sqlCheckerMap.get(key);
			//首先要判断是不是已经有放当前sql的容器了吧
			if (subSqlCheckerList == null) {
				subSqlCheckerList = new ArrayList<SqlChecker>();
			}
			//1:把当前的sql添加到对应容器
			subSqlCheckerList.add(sqlChecker);
			//caseId为1的前置sql --》1-before
			//caseId为2的后置sql --》2-after
			sqlCheckerMap.put(key, subSqlCheckerList);
		}
		
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
			//设置当前测试用例对应的前置sql列表: caseId-Type   -->1-before
			//给我一个caseid，我就能得到对应前置sql列表--》map<1-before,前置sql列表>
			String beforeKey = apiCaseDetail.getCaseId()+"-"+"before";
			apiCaseDetail.setBeforeSqlList(sqlCheckerMap.get(beforeKey));
			
			//给我一个caseid，我就能得到对应后置sql列表--》map<??,后置sql列表>
			//设置当前测试用例对应的后置sql列表  -》key ：  1-after
			String afterKey = apiCaseDetail.getCaseId()+"-"+"after";
			apiCaseDetail.setAfterSqlList(sqlCheckerMap.get(afterKey));
			
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

}
