/**
 * 
 */
package api.advanced.data_re_write.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import api.advanced.data_re_write.pojo.ApiCaseDetail;
import api.advanced.data_re_write.pojo.ApiInfo;
import api.advanced.data_re_write.testcase.AllTestCase;

/**
 * @Desc:编写测试用例
 * @author:zpp
 * @time:2019年4月6日 下午12:56:32
 */
public class DataProviderUtils {
	private static String streamSourceExcelPath;
	private static int sheetIndex0;
	private static int sheetIndex1;
	private static Logger logger = Logger.getLogger(AllTestCase.class);

	static {
		try {
			Properties properties = new Properties();
			InputStream is = new FileInputStream(
					new File("src/main/java/api/advanced/data_re_write/api_info.properties"));
			properties.load(is);
			streamSourceExcelPath = properties.getProperty("streamSourceExcelPath");
			sheetIndex0 = Integer.parseInt(properties.getProperty("sheetIndex0"));
			sheetIndex1 = Integer.parseInt(properties.getProperty("sheetIndex1"));
			System.out.println(streamSourceExcelPath);
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
			Object[] dataArray = new Object[] { apiCaseDetail }; // 把对象放入一个一维数组
			objects[i] = dataArray; // 把一维数组放入二维数组
		}
		return objects;
	}
}
