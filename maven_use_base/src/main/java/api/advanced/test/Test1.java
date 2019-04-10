/**
 * 
 */
package api.advanced.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.omg.PortableInterceptor.INACTIVE;

import api.advanced.reflectUpdate.ExcelUtilsReflect;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Desc:数据回写 
 * @author:zpp 
 * @time:2019年4月6日 下午1:29:21
 */
public class Test1 {
	public static void main(String[] args) throws Exception{
		 InputStream is = Test1.class.getResourceAsStream("/excel/reflect/excelReflectTest.xlsx");
		 Workbook workbook = WorkbookFactory.create(is);
		 OutputStream os = new FileOutputStream(new File("D:\\1.xlsx"));
		 Sheet sheet = workbook.getSheetAt(1);
		 Row row = sheet.getRow(1);
//		 Cell cell = row.getCell(5);
		 Cell cell = row.getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		 cell.setCellValue("Test1");
		 
		 workbook.write(os);
		 os.close();
		 is.close();	
	}
}
