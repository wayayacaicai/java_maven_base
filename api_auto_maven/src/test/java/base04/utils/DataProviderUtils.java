package base04.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import base04.pojo.ApiCaseDetail;
import base04.pojo.ApiInfo;

/**
 * @Desc:数据提供的工具类 
 * @author:zpp 
 * @time:2019年5月28日 下午4:38:55
 */
public class DataProviderUtils {
	/**
	 * @Desc 数据提供方法
	 * @return
	 */
	public static Object[][] getDatas(){
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/base03/testcase_reflect.xlsx", 1, ApiCaseDetail.class);
		List<Object> apiInfoList = ExcelUtils.readExcel("/base03/testcase_reflect.xlsx", 0, ApiInfo.class);
		Map<String, ApiInfo> apiInfoMap = new HashMap<>(); //容器转换，性能优化
		for (Object object : apiInfoList) {
			ApiInfo apiInfo = (ApiInfo)object;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		Object[][] allDatas = new Object[apiCaseDetailList.size()][]; //创建一个二维数组装所有对象
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail)apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); //得到apiInfo对象
			Object[] data = {apiCaseDetail}; //创建一个一维数组装一个对象
			allDatas[i] = data;
		}
		return allDatas;
	}
}
