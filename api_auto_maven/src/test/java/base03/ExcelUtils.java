package base03;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import base03.pojo.ApiCaseDetail;
import base03.pojo.ApiInfo;

/**
 * @Desc:Excel工具类
 * @author:zpp
 * @time:2019年5月21日 下午5:00:09
 */
public class ExcelUtils {
	/**
	 * @Desc 读取
	 * @return
	 */
	public static Object[][] readExcel(String excelPath,int index){
		InputStream is = null;
		Workbook workbook = null;
		Object[][] allDatas = null; //定义二维数组来存取每行数据
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);
			
			int lastRowNum = sheet.getLastRowNum(); //最后一行
			allDatas = new Object[lastRowNum][]; //创建二维数组来存取每行数据
			for(int i=1;i<=lastRowNum;i++){
				Row row = sheet.getRow(i);
				int cellNum = row.getLastCellNum(); //总列数
				Object[] rowData = new Object[cellNum]; //创建一维数组来存取每列数据
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue();
					rowData[j] = cellValue; //一维数组接收每列数据
				}
				allDatas[i-1] = rowData; //二维数组接收每行数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(is, workbook);
		}
		return allDatas;
	}
	
	/**
	 * @Desc 读取
	 * @return
	 */
	public static List<Object> readExcel(String excelPath,int index,Class clazz){
		InputStream is = null;
		Workbook workbook = null;
		List<Object> aList = null; //定义列表接收所有对象
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);
			
			//------获取第一行表头各个字段------
			Row firstRow = sheet.getRow(0); //获取第一行
			int cellNum = firstRow.getLastCellNum(); //获取总列数
			String[] fieldArray = new String[cellNum]; //定义接收表头字段的数组
			for(int i=0;i<cellNum;i++){
				Cell cell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String cellValue = cell.getStringCellValue();
				String fieldCellValue = cellValue.substring(0, cellValue.indexOf("("));
				fieldArray[i] = fieldCellValue;
			}
			
			int lastRowNum = sheet.getLastRowNum(); //最后一行
			aList = new ArrayList<>(); //创建列表接收所有对象
					
			for(int i=1;i<=lastRowNum;i++){
				Row row = sheet.getRow(i);
				Object object = clazz.newInstance(); //创建一个对象来存取每列数据
				
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue(); //得到单元格的值
					String fieldName = fieldArray[j]; //得到列名
					String setFieldName = "set" + fieldName; //得到set方法
					Method method = clazz.getMethod(setFieldName, String.class);
					method.invoke(object, cellValue);
				}
				aList.add(object); //用列表接收每行数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(is, workbook);
		}
		return aList;
	}

	/**
	 * @Desc 读取excel数据的流关闭
	 * @param is
	 * @param workbook
	 */
	private static void close(InputStream is, Workbook workbook) {
		if(workbook !=null){
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String excelPath = "/base03/testcase_reflect.xlsx";
		List<Object> readExcel = readExcel(excelPath,1,ApiCaseDetail.class);
		for (Object object : readExcel) {
			System.out.println(object);
		}
	}
}
