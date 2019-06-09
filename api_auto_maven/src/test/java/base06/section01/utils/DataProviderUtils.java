package base06.section01.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import base06.section01.pojo.ApiCaseDetail;
import base06.section01.pojo.ApiInfo;
import base06.section01.pojo.CellData;

/**
 * @Desc:数据提供的工具类 
 * @author:zpp 
 * @time:2019年5月28日 下午4:38:55
 */
public class DataProviderUtils {
	//一次性回写数据的容器--列表
	private static List<CellData> cellDataList = new ArrayList<>();
	
	public static List<CellData> getCellDataList(){
		return cellDataList;
	}
	
	public static void addCellData(CellData cellData){
		cellDataList.add(cellData);
	}
	
	/**
	 * @Desc 数据提供方法,通过设置dataprovider的名称和字节码自动调用
	 * @Desc 因为是类名调用所有注意要是静态方法
	 * @Desc 字节码对象定义泛型，限制约束条件
	 * @return
	 */
	@DataProvider(name="apitest")
	public static Object[][] getDatas(){
		List<ApiCaseDetail> apiCaseDetailList = (List<ApiCaseDetail>) ExcelUtils.readExcel("/base06/testcase.xlsx", 1, ApiCaseDetail.class);
		List<ApiInfo> apiInfoList = (List<ApiInfo>) ExcelUtils.readExcel("/base06/testcase.xlsx", 0, ApiInfo.class);
		Map<String, ApiInfo> apiInfoMap = new HashMap<>(); //容器转换，性能优化
		for (ApiInfo apiInfo : apiInfoList) {
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
	
		Object[][] allDatas = new Object[apiCaseDetailList.size()][]; //创建一个二维数组装所有对象
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); //得到apiInfo对象
			Object[] data = {apiCaseDetail}; //创建一个一维数组装一个对象
			allDatas[i] = data;
		}
		return allDatas;
	}
}
	
