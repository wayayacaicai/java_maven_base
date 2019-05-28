package web.base09.util;

import java.util.List;

import web.base09.pojo.ExcelSheetObject;
import web.base09.testcase.regsiter.RegisterInfo;





public class DataProviderUtil {

	/**
	 * 从excel中读取出数据，然后再次封装为数据提供者要求的二维数组对象中
	 * @param excelPath excel的路径(类路径)
	 * @param sheetIndex sheet索引
	 * @param clazz 对应封装的类型的字节码对象
	 * @return
	 */
	public static Object[][] getData(String excelPath, int sheetIndex, Class<? extends ExcelSheetObject> clazz) {
		List<? extends ExcelSheetObject> registerInfoList = ExcelUtils.readExcel(excelPath, sheetIndex, clazz);
		//创建一个二维数组
		Object[][] datas = new Object[registerInfoList.size()][];
		for (int i = 0; i < registerInfoList.size(); i++) {
			ExcelSheetObject registerInfo = registerInfoList.get(i);
			Object[] itemData = { registerInfo };
			datas[i] = itemData;
		}
		return datas;
	}
	
	public static Object[][] getData(String caseName, Class<RegisterInfo> clazz) {
		//register_failure_test_case
		String[] items = caseName.split("_");
		//excel名称
		String excelName = items[0];
		//sheet的名称
		String sheetName = items[1];
		// excel的路径：/testcase/register/register.xlsx
		String excelPath = "/testcase/"+excelName+"/"+excelName+".xlsx";
		
		List<? extends ExcelSheetObject> registerInfoList = ExcelUtils.readExcel(excelPath, sheetName, clazz);
		//创建一个二维数组
		Object[][] datas = new Object[registerInfoList.size()][];
		for (int i = 0; i < registerInfoList.size(); i++) {
			ExcelSheetObject registerInfo = registerInfoList.get(i);
			Object[] itemData = { registerInfo };
			datas[i] = itemData;
		}
		return datas;
		
	}
	
	//Example
	public static void main(String[] args) {
		Object[][] datas = getData("register_success_test_case", RegisterInfo.class);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}
		/*Object[][] datas = getData("/testcase/register/register.xlsx", 1, RegisterInfo.class);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.println(object);
			}
		}*/
	}



}
