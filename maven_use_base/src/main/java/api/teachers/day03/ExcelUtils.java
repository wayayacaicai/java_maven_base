package api.teachers.day03;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alibaba.fastjson.JSONObject;

public class ExcelUtils {

	public static void main(String[] args) throws Exception {
//		Object[][] datas = ExcelUtils.readExcel("/api_test_case_01.xlsx",1);
//		for (Object[] objects : datas) {
//			for (Object object : objects) {
//				System.out.print(object + "              ");
//			}
//			System.out.println();
//		}
//		Object[][] datas = ExcelUtils.readExcel("/api_test_case_01.xlsx",1,ApiCaseDetail.class);
//		Object[][] datas = ExcelUtils.readExcel("/api_test_case_01.xlsx",0,ApiInfo.class);
		List<Object> allDataList = ExcelUtils.readExcel("/api_test_case_01.xlsx",1,ApiCaseDetail.class);
		for (Object object : allDataList) {
			System.out.println(object);
		}
		
		
//		json测试
//		String jsonStr = "{\"mobilephone\":\"13888888888\",\"pwd\":\"123456\",\"regname\":\"happy\"}";
//		Map<String, String> map = (Map<String, String>) JSONObject.parse(jsonStr);
//		System.out.println(map);
	}
	
	public static List<Object> readExcel(String excelPath, int sheetIndex,Class clazz) {
		//准备一个容器，来存放所有的行对应的对象
		List<Object> allDataList = new ArrayList<Object>();
		InputStream is = null;
		Workbook workbook = null;
		try {
			//1：Excel文件输入流
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			//2：获得工作簿
			workbook = WorkbookFactory.create(is);
			//3：获得第一个sheet（工作表）
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			//4：获得行数
			int lastRowNum = sheet.getLastRowNum();
			/**-------------------读第一行数据 start---------------------------------- */
			
			//得到所有的字段（得到对象的属性）-->第一行--》遍历第一行的所有列
			Row firstRow = sheet.getRow(0);
			//得到第一行的列数
			int firstRowCellNum = firstRow.getLastCellNum();
			//创建一个容器，保存所有的字段
			String[] fieldArray = new String[firstRowCellNum];
			//遍历第一行的每一列
			for(int i=0;i < firstRowCellNum;i++){
				//获得对应索引的列
				Cell cell = firstRow.getCell(i);
				//所有的cell当成字符串处理
				cell.setCellType(CellType.STRING);
				//获得当前cell的值
				String firstRowCellValue = cell.getStringCellValue();
				//截取出字段名，忽略掉备注信息
				String fieldName = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				//把字段名保存到字段容器中
				fieldArray[i] = fieldName;
//				System.out.print(fieldName + "   ");
			}
			/**-------------------读第一行数据 end---------------------------------- */
			

			//遍历所有的行
			for (int i = 1; i <= lastRowNum; i++) {
				//获得对应的行
				Row row = sheet.getRow(i);
				//获得的最大的列数
				int lastCellNum = row.getLastCellNum();
				//创建一个数组，保存当前行的数据
				Object[] rowDataArray = new Object[lastCellNum];
				//如果读的是第一个sheet--》ApiInfo对象，第二个sheet--》ApiCaseDetail对象
				//生成一个对应的对象来：ApiInfo?ApiCaseDetail?ApiXXX,ApiNNN,ApiMMM.....
//				Object obj = clazz.newInstance();
				//要生成对象：1：new   2：构造方法反射  3：clazz.newInstance
				Object obj = clazz.newInstance();//clazz是ApiInfo，用Object类型接收，本质还是ApiInfo
				
				//获得每行的列
				for (int j = 0; j < lastCellNum; j++) {
					//获得对应索引的列
					Cell cell = row.getCell(j);
					//所有的cell当成字符串处理
					cell.setCellType(CellType.STRING);
					//获得当前cell的值
					String cellValue = cell.getStringCellValue();
					//把值保存到数组
					rowDataArray[j] = cellValue;
					/**-------------------反射设值 start---------------------------------- */
					//拿到当前列对应的字段名
					String currentFieldName = fieldArray[j];
					//拼接处对应的setter方法：CaseId --》setCaseId,IsExcute-->setIsExcute
					String setterName = "set" + currentFieldName;
					//获得对应的方法
					Method setterMethod = clazz.getMethod(setterName, String.class);
					//反射调用方法，完成对象的属性设值
					setterMethod.invoke(obj, cellValue);
					/**-------------------反射设值 end---------------------------------- */
				}
				
//				System.out.println(obj);
				allDataList.add(obj);
			}
		} catch (Exception e) {
			//日志信息
		} finally {
			close(is, workbook);
		}
		return allDataList;
	}

	public static Object[][] readExcel(String excelPath, int sheetIndex) {
		Object[][] allDatasArray = null;
		InputStream is = null;
		Workbook workbook = null;
		try {
			//1：Excel文件输入流
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			//2：获得工作簿
			workbook = WorkbookFactory.create(is);
			//3：获得第一个sheet（工作表）
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			//4：获得行数
			int lastRowNum = sheet.getLastRowNum();
			//确定了行数--》数组的长度
			allDatasArray = new Object[lastRowNum][];

			//遍历所有的行
			for (int i = 1; i <= lastRowNum; i++) {
				//获得对应的行
				Row row = sheet.getRow(i);
				//获得的最大的列数
				int lastCellNum = row.getLastCellNum();
				//创建一个数组，保存当前行的数据
				Object[] rowDataArray = new Object[lastCellNum];
				//如果读的是第一个sheet--》ApiInfo对象，第二个sheet--》ApiCaseDetail对象
				//生成一个对应的对象来：ApiInfo?ApiCaseDetail?
//				Object obj = clazz.newInstance();
				
				//获得每行的列
				for (int j = 0; j < lastCellNum; j++) {
					//获得对应索引的列
					Cell cell = row.getCell(j);
					//所有的cell当成字符串处理
					cell.setCellType(CellType.STRING);
					//获得当前cell的值
					String cellValue = cell.getStringCellValue();
					//把值保存到数组
					rowDataArray[j] = cellValue;
				}

				//把一行的数据（一维数组）--》二维数组
				allDatasArray[i-1] = rowDataArray;
			}
			return allDatasArray;
		} catch (Exception e) {
			//日志信息
		} finally {
			close(is, workbook);
		}

		return null;
	}

	/**
	 * close相关的资源
	 * @param is 输入流
	 * @param workbook 
	 */
	private static void close(InputStream is, Workbook workbook) {
		if (workbook != null) {

			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	private static void example() throws IOException, InvalidFormatException {
		//		1：Excel文件输入流
		//		InputStream is = ExcelReader.class.getResourceAsStream(path);
		InputStream is = ExcelUtils.class.getResourceAsStream("/testcase.xlsx");
		//		2：获得工作簿
		//		Workbook workbook = WorkbookFactory.create(is);
		Workbook workbook = WorkbookFactory.create(is);
		//		3：获得第一个sheet（工作表）
		//		Sheet sheet = workbook.getSheetAt(0);
		Sheet sheet = workbook.getSheetAt(0);
		//		4：获得第i行
		//		Row row = sheet.getRow(i);
		Row row = sheet.getRow(1);
		//		5：获得第j列
		//		Cell cell = row.getCell(j);
		Cell cell = row.getCell(3);
		//		6：设置cell的内容类型
		//		cell.setCellType(CellType.STRING);

		//得到cell的值
		System.out.println(cell.getStringCellValue());
	}

}
