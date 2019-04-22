package api.advanced.data_check.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import api.advanced.data_check.pojo.SqlChecker;
import api.advanced.data_check.pojo.ApiCaseDetail;
import api.advanced.data_check.pojo.ApiInfo;
import api.advanced.data_check.pojo.CellData;
import api.advanced.data_check.testcase.AllTestCase;

/**
 * @Desc:编写测试用例
 * @author:zpp
 * @time:2019年4月6日 下午12:56:32
 */
public class ApiUtils {
	// 收集响应数据的列表
	private static List<CellData> cellDataList = new ArrayList<>();
	private static List<CellData> sqlDataList = new ArrayList<>();
	
	private static String streamSourceExcelPath;
	private static int sheetIndex0;
	private static int sheetIndex1;
	private static int sheetIndex2;
	private static Logger logger = Logger.getLogger(ApiUtils.class);

	public static List<CellData> getcellDataList() {
		return cellDataList;
	}

	public static void addCellData(CellData cellData) {
		cellDataList.add(cellData);
	}
	
	public static List<CellData> getSqlCellDataList() {
		return sqlDataList;
	}

	public static void addSqlData(CellData cellData) {
		sqlDataList.add(cellData);
	}

	static {
		try {
			Properties properties = new Properties();
			InputStream is = new FileInputStream(
					new File("src/main/java/api/advanced/data_check/api_info.properties"));
			properties.load(is);
			streamSourceExcelPath = properties.getProperty("streamSourceExcelPath");
			sheetIndex0 = Integer.parseInt(properties.getProperty("sheetIndex0"));
			sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
			sheetIndex2 = Integer.parseInt(properties.getProperty("sheetIndex2"));
		} catch (Exception e) {
			logger.error("配置文件读取出现异常" + e.getMessage());
		}
	}

	public static Object[][] getDatas() {
		// 接口基本信息
		List<ApiInfo> datasInfoList = (List<ApiInfo>) ExcelUtils.readExcel(streamSourceExcelPath, sheetIndex0,
				ApiInfo.class);
		// 接口详细信息
		List<ApiCaseDetail> datasDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel(streamSourceExcelPath,
				sheetIndex1, ApiCaseDetail.class);
		// 数据验证信息
		List<SqlChecker> sqlCheckerList = (List<SqlChecker>) ExcelUtils.readExcel(streamSourceExcelPath, sheetIndex2,
				SqlChecker.class);
		
		//整理数据验证的信息，不同的case的前置，后置sql放不同的列表去
		Map<String, List<SqlChecker>> sqlCheckerMap = new HashMap<String,List<SqlChecker>>();
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//弄一个特殊的键直接区分放到前置或者后置里面，不然先要判断caseId，然后还要判断前置或者后置
			String key = sqlChecker.getCaseId() + "-" + sqlChecker.getType();
			List<SqlChecker> subSqlCheckerList = sqlCheckerMap.get(key);
			if(subSqlCheckerList==null){
				subSqlCheckerList = new ArrayList<>();
			}
			//把对象添加到列表
			subSqlCheckerList.add(sqlChecker);
			//把列表添加到hashmap去
			sqlCheckerMap.put(key, subSqlCheckerList);
		}
		
		// ApiId的容器转换
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for (ApiInfo apiInfo : datasInfoList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}

		// 二维数据放一维数组，每个一维数组放一个ApiCaseDetail得对象
		Object[][] objects = new Object[datasDetailList.size()][];
		for (int i = 0; i < datasDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = datasDetailList.get(i); // 遍历得到一个对象
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); // 得到apiInfo对象
			
			//设置前置的sql数据验证列表 caseId+type
			String beforeKey = apiCaseDetail.getCaseId() + "-" + "before";
			apiCaseDetail.setBeforeSqlList(sqlCheckerMap.get(beforeKey));
				
			//设置后置的sql数据验证列表  caseId+type
			String afterKey = apiCaseDetail.getCaseId() + "-" + "after";
			apiCaseDetail.setAfterSqlList(sqlCheckerMap.get(afterKey));
					
			Object[] dataArray = new Object[] { apiCaseDetail }; // 把对象放入一个一维数组
			objects[i] = dataArray; // 把一维数组放入二维数组
		}
		return objects;
	}
}
