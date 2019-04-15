/**
 * 
 */
package api.retry;

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

/**
 * @Desc:Excel工具类
 * @author:zpp
 * @time:2019年4月14日 下午11:28:17
 */
public class ExcelUtils {
	public static void main(String[] args) {
		String excelPath = "/retry.xlsx";
		int index = 1;
		List<Object> readExcel = readExcel(excelPath, index, ApiCaseDetail.class);
		for (Object object : readExcel) {
			System.out.println(object);
		}
	}

	public static List<Object> readExcel(String excelPath, int index,Class clazz) {
		InputStream is = null;
		Workbook workbook = null;
		List<Object> objectList = null;
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);

			//得到所有字段名
			Row firstRow = sheet.getRow(0);
			int cellNum = firstRow.getLastCellNum();
			String[] fieldName = new String[cellNum];
			for(int i=0;i<cellNum;i++){
				Cell firstRowCell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				firstRowCell.setCellType(CellType.STRING);
				String firstRowCellValue = firstRowCell.getStringCellValue();
				String fieldValue = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				fieldName[i] = fieldValue; //得到字段名
			}
				
			int lastRowNum = sheet.getLastRowNum(); // 最后一行，行数+1
			objectList = new ArrayList<Object>(); //集合

			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);

				Object object = clazz.newInstance(); //一个对象

				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue(); //具体的数据
					
					String currentField = fieldName[j]; //得到当前的字段值
					String currentMethod = "set" + currentField; //得到当前的set方法
					Method method = clazz.getMethod(currentMethod, String.class);
					method.invoke(object, cellValue);
				}

				objectList.add(object); //集合添加对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close(is, workbook);
		}
		return objectList;
	}

	private static void close(InputStream is, Workbook workbook) {
		if(workbook !=null){
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is !=null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
