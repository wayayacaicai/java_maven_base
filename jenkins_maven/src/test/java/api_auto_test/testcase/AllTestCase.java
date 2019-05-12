package api_auto_test.testcase;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api_auto_test.pojo.ApiCaseDetail;
import api_auto_test.pojo.CellData;
import api_auto_test.utils.ApiHandleUtils;
import api_auto_test.utils.ApiDataProviderUtils;
import api_auto_test.utils.DataCheckUtils;
import api_auto_test.utils.ExcelUtils;
import api_auto_test.utils.HttpUtils;
import api_auto_test.utils.ParameterUtils;



/**
 * 当excel结构变化：增加、删除列、顺序调整 2：大量的冗余，不好维护--》怎么解决冗余，重复的
 * 数据库冗余：一个信息系统所有数据都放到一个表中--》通过多个表、设计关联字段解决的
 * 
 * @Desc:结合json数据和反射进行请求(执行测试用例)
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class AllTestCase {
	private static String streamSourceExcelPath;
	private static String targetExcelPath;
	private static int sheetIndex1;
	private static Logger logger = Logger.getLogger(AllTestCase.class);

	static {
		try {
			Properties properties = new Properties();
			InputStream is = new FileInputStream(
					new File("src/test/java/api_auto_test/api_info.properties"));
			properties.load(is);
			streamSourceExcelPath = properties.getProperty("streamSourceExcelPath");
			targetExcelPath = properties.getProperty("targetExcelPath");
			sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
		} catch (Exception e) {
			logger.error("配置文件读取出现异常" + e.getMessage());
		}
	}

	@BeforeSuite
	public void BeforeSuite(){
		//参数关联明天要去搞定
		//数据清洗(把数据库数据清除掉)
		System.out.println("在执行用例之前");
		ParameterUtils.addGlobalData("mobilephone", "13666666666");
		ParameterUtils.addGlobalData("pwd", "123456");
		ParameterUtils.addGlobalData("nickname", "caicai");
	}
	
	
	@DataProvider
	public Object[][] getDatas() {
		return ApiDataProviderUtils.getDatas();
	}

	@Test(dataProvider = "getDatas")
	public static void request(ApiCaseDetail apiCaseDetail) {
		//1.前置验证
		DataCheckUtils.beforeCheck(apiCaseDetail);
		
		String responseData = HttpUtils.request(apiCaseDetail);
		
		//提取响应数据
		ApiHandleUtils.extractResponseData(responseData,apiCaseDetail);
		
		//2.后置验证
		DataCheckUtils.afterCheck(apiCaseDetail);
		
		//收集数据
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, responseData);
		ApiDataProviderUtils.addCellData(cellData);
		
		//断言响应结果
		ApiHandleUtils.assertResult(responseData,apiCaseDetail);
		
	}

	@AfterSuite
	public void afterSuite() {
		// 一次性数据回写--一次性把数据收集好
//		List<CellData> cellDataList = ApiUtils.getcellDataList();
//		ExcelUtils.writeExcel(sourceExcelPath, "d:/data.xlsx", sheetIndex1, cellDataList);
//		
//		System.out.println("-------");
//		
//		List<CellData> sqlCellData = ApiUtils.getSqlCellDataList();
//		ExcelUtils.writeExcel(sourceExcelPath, "d:/data_check.xlsx", 2, sqlCellData);
		
		List<CellData> cellDataList = ApiDataProviderUtils.getCellDataList();
		List<CellData> sqlCellData = ApiDataProviderUtils.getSqlCellDataList();
		ExcelUtils.writeAllExcel(streamSourceExcelPath, targetExcelPath,  cellDataList, sqlCellData);
		System.out.println("用例写入结束");
	}
}
