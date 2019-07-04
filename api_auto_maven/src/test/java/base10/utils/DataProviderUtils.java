package base10.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import base10.pojo.ApiCaseDetail;
import base10.pojo.ApiInfo;
import base10.pojo.BaseExcelSheet;
import base10.pojo.CellData;
import base10.pojo.SqlChecker;

/**
 * @Desc:数据提供的工具类 
 * @author:zpp 
 * @time:2019年5月28日 下午4:38:55
 */
public class DataProviderUtils {
	//一次性回写实际响应数据的容器--列表
	private static List<CellData> cellDataList = new ArrayList<>();
	//一次性回写数据验证的容器--列表
	private static List<CellData> sqlCheckerList = new ArrayList<>();
	
	public static List<CellData> getCellDataList(){
		return cellDataList;
	}
	
	public static void addCellData(CellData cellData){
		cellDataList.add(cellData);
	}
	
	public static List<CellData> getSqlCheckerList(){
		return sqlCheckerList;
	}
	
	public static void addSqlChecker(CellData cellData){
		sqlCheckerList.add(cellData);
	}
	
	/**
	 * @Desc 数据提供方法,通过设置dataprovider的名称和字节码自动调用
	 * @Desc 因为是类名调用所有注意要是静态方法
	 * @Desc 字节码对象定义泛型，限制约束条件
	 * @return
	 */
	@DataProvider(name="apitest")
	public static Object[][] getDatas(){
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel("/base10/testcase.xlsx", 0, ApiInfo.class);
		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel("/base10/testcase.xlsx", 1, ApiCaseDetail.class);
		List<SqlChecker> sqlCheckerList = (List<SqlChecker>) ExcelUtils.readExcel("/base10/testcase.xlsx", 2, SqlChecker.class);
		
		Map<String, ApiInfo> apiInfoMap = new HashMap<>(); //容器转换，性能优化
		for (ApiInfo apiInfo : apiInfoList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		Map<String, List<SqlChecker>> sqlCheckerMap = new HashMap<>(); //容器转换，性能优化
		for (SqlChecker sqlChecker : sqlCheckerList) {
			String key = sqlChecker.getCaseId() + "-" + sqlChecker.getType();
			List<SqlChecker> subSqlCheckerList = sqlCheckerMap.get(key);
			if(subSqlCheckerList == null){
				subSqlCheckerList = new ArrayList<>();
			}
			subSqlCheckerList.add(sqlChecker);
			sqlCheckerMap.put(key, subSqlCheckerList);
		}
	
		Object[][] allDatas = new Object[apiCaseDetailList.size()][]; //创建一个二维数组装所有对象
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); //得到apiInfo对象
			
			String beforeKey = apiCaseDetail.getCaseId() + "-before";
			apiCaseDetail.setBeforeSqlCheckerList(sqlCheckerMap.get(beforeKey)); //得到前置验证列表
			String afterKey = apiCaseDetail.getCaseId() + "-after";
			apiCaseDetail.setAfterSqlCheckerList(sqlCheckerMap.get(afterKey)); //得到后置验证列表
			
			Object[] data = {apiCaseDetail}; //创建一个一维数组装一个对象
			allDatas[i] = data;
		}
		return allDatas;
	}
}
	
