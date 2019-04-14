/**
 * 
 */
package api.retry;

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
 * @Desc:TODO
 * @author:zpp
 * @time:2019年4月14日 下午11:28:17
 */
public class ExcelUtils {
	public static void main(String[] args) {
		String excelPath = "/retry.xlsx";
		int index = 0;
		Object[][] allDatas = readExcel(excelPath, index);
		for (Object[] objects : allDatas) {
			for (Object object : objects) {
				System.out.print(object + "  ");
			}
			System.out.println();
		}
	}

	public static Object[][] readExcel(String excelPath, int index) {
		Object[][] allDatas = null;
		try {
			InputStream is = ExcelUtils.class.getResourceAsStream(excelPath);
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);

			int lastRowNum = sheet.getLastRowNum(); // 最后一行，行数+1
			allDatas = new Object[lastRowNum][]; // 二维数组

			for (int i = 1; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i);

				int cellNum = row.getLastCellNum(); // 列数
				Object[] rowDatas = new Object[cellNum]; // 一维数组

				for (int j = 0; j < cellNum; j++) {
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue();

					rowDatas[j] = cellValue; // 一维数组装满列元素
				}

				allDatas[i - 1] = rowDatas; // 二维数组装满一维数组
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allDatas;
	}
}
