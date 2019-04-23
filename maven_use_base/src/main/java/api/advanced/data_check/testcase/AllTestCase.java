package api.advanced.data_check.testcase;

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

import api.advanced.data_check.pojo.ApiCaseDetail;
import api.advanced.data_check.pojo.CellData;
import api.advanced.data_check.utils.ApiUtils;
import api.advanced.data_check.utils.DataCheckUtils;
import api.advanced.data_check.utils.ExcelUtils;
import api.advanced.data_check.utils.HttpUtils;
import api.advanced.data_check.utils.ParameterUtils;



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
					new File("src/main/java/api/advanced/data_check/api_info.properties"));
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
		return ApiUtils.getDatas();
	}

	@Test(dataProvider = "getDatas")
	public static void request(ApiCaseDetail apiCaseDetail) {
		//1.前置验证
		DataCheckUtils.beforeCheck(apiCaseDetail);
		
		String responseData = HttpUtils.request(apiCaseDetail);
		
		//2.后置验证
		DataCheckUtils.afterCheck(apiCaseDetail);
		
		//收集数据
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, responseData);
		ApiUtils.addCellData(cellData);
		
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
		
		List<CellData> cellDataList = ApiUtils.getCellDataList();
		List<CellData> sqlCellData = ApiUtils.getSqlCellDataList();
		ExcelUtils.writeAllExcel(streamSourceExcelPath, targetExcelPath,  cellDataList, sqlCellData);
	}
}
