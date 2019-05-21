package base02.section02;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;

/**
 * @Desc:Excel工具类
 * @author:zpp 
 * @time:2019年5月21日 下午5:00:09
 */
public class ExcelUtils {
	public static void main(String[] args) {
		try {
			InputStream is = ExcelUtils.class.getResourceAsStream("/base02/testcase.xlsx");
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);
			
			int lastRowNum = sheet.getLastRowNum();
			System.out.println("行数" + lastRowNum);
			Row row = sheet.getRow(1);
			
			int lastCellNum = row.getLastCellNum();
			System.out.println("列数" + lastCellNum);
			
			Cell cell = row.getCell(4, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String stringCellValue = cell.getStringCellValue();
			System.out.println(stringCellValue);
			workbook.close();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
