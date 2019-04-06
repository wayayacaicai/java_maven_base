/**
 * 
 */
package api.advanced.reflectUpdate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Desc:编写测试用例
 * @author:zpp
 * @time:2019年4月6日 下午12:56:32
 */
public class DataProviderUtils {
	public static Object[][] getDatas() {
		// 接口基本信息
		List<Object> datasInfoList = ExcelUtilsReflect.readExcel("/excel/reflect/excelReflect.xlsx", 0, ApiInfo.class);
		// 接口详细信息
		List<Object> datasDetailList = ExcelUtilsReflect.readExcel("/excel/reflect/excelReflect.xlsx", 1,
				ApiCaseDetail.class);
		// ApiId的容器转换
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for (Object object : datasInfoList) {
			ApiInfo apiInfo = (ApiInfo) object;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}

		// 二维数据放一维数组，每个一维数组放一个ApiCaseDetail得对象
		Object[][] objects = new Object[datasDetailList.size()][];
		for (int i = 0; i < datasDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail) datasDetailList.get(i); // 遍历得到一个对象
			// 告诉apiId进行外键匹配,每一次匹配apiId，都会全部重新匹配一边，性能太差
			// for (Object object : datasInfoList) {
			// ApiInfo apiInfo = (ApiInfo) object;
			// if (apiInfo.getApiId().equals(apiCaseDetail.getApiId())) {
			// apiCaseDetail.setApiInfo(apiInfo); // 设置对应的apiInfo对象
			// }
			// }
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));

			Object[] dataArray = new Object[] { apiCaseDetail }; // 把对象放入一个一维数组
			objects[i] = dataArray; // 把一维数组放入二维数组
		}
		return objects;
	}
}
