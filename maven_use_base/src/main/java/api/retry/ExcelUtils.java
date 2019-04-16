/**
 * 
 */
package api.retry;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	public static List<? extends ExcelSheetObject> readExcel(String excelPath, int index, Class<? extends ExcelSheetObject> clazz) {
		InputStream is = null;
		Workbook workbook = null;
		List<ExcelSheetObject> excelSheetList = null;
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);

			// 得到所有字段名
			Row firstRow = sheet.getRow(0);
			int cellNum = firstRow.getLastCellNum();
			String[] fieldName = new String[cellNum];
			for (int i = 0; i < cellNum; i++) {
				Cell firstRowCell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				firstRowCell.setCellType(CellType.STRING);
				String firstRowCellValue = firstRowCell.getStringCellValue();
				String fieldValue = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				fieldName[i] = fieldValue; // 得到字段名
			}

			int lastRowNum = sheet.getLastRowNum(); // 最后一行，行数+1
			excelSheetList = new ArrayList<ExcelSheetObject>(); // 集合

			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);

				ExcelSheetObject excelSheetObject = clazz.newInstance(); // 一个对象
				excelSheetObject.setRowNo(i+1); //设置行号
		
				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue(); // 具体的数据

					String currentField = fieldName[j]; // 得到当前的字段值
					String currentMethod = "set" + currentField; // 得到当前的set方法
					Method method = clazz.getMethod(currentMethod, String.class);
					method.invoke(excelSheetObject, cellValue);
				}
				excelSheetList.add(excelSheetObject); // 集合添加对象
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, workbook);
		}
		return excelSheetList;
	}

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
	
	public static void writeExcelData(String sourceExcelPath,String targetExcelPath,
			int sheetIndex,String caseId,int cellNo,String actualResponseData) {
		InputStream is = null;
		Workbook workbook = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(new File(sourceExcelPath));
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			
			int lastRowNum = sheet.getLastRowNum();
			for(int i=1;i<=lastRowNum;i++){
				Row row = sheet.getRow(i);
				Cell firstCell = row.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); //第一列
				firstCell.setCellType(CellType.STRING);
				String firstCellValue = firstCell.getStringCellValue(); //第一列值
				//第一列的值和传入的caseid相同，就把对应的实际响应结果写入到这一行
				if(firstCellValue.equals(caseId)){
					Cell targetCell = row.getCell(cellNo-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					targetCell.setCellType(CellType.STRING);
					targetCell.setCellValue(actualResponseData);
					break;
				}
			}
			os = new FileOutputStream(new File(targetExcelPath));
			workbook.write(os);
		} catch (Exception e) {		
			e.printStackTrace();
		}finally {
			close(is, workbook, os);
		}
	}

	private static void close(InputStream is, Workbook workbook, OutputStream os) {
		if(os !=null){
			try {
				os.close();
			} catch (IOException e) {	
				e.printStackTrace();
			}
		}
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

