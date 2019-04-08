/**
 * 
 */
package api.medium;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Desc:excel解析
 * @author:zpp
 * @time:2019年3月31日 下午11:24:34
 */
public class ExcelUtils {
	public static void main(String[] args) throws Exception {
		// methodBase();

		// methodMedium();

		String excelPath = "/excel/baseExcelWork.xlsx";
		Object[][] method_High1 = method_High1(excelPath, 0);
		for (Object[] objects : method_High1) {
			for (Object object : objects) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
	}

	public static Object[][] method_High1(String excelPath, int index) {
		// 返回的数组
		Object[][] datasArray = null;
		InputStream is = null;
		Workbook workbook = null;
		try {
			// 1.Excel文件流
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			// 2.获得工作薄
			workbook = WorkbookFactory.create(is);
			// 3.获得第一个sheet表
			Sheet sheet = workbook.getSheetAt(index);
			// 4.获得最后一行,然后遍历出所有行
			int rowNum = sheet.getLastRowNum();
			// 确定了行数----数组的长度
			datasArray = new Object[rowNum + 1][];

			for (int i = 0; i <= rowNum; i++) {
				// 获得对应行
				Row row = sheet.getRow(i);
				// 获得最大的列数
				int cellNum = row.getLastCellNum();
				// 创建一个数组，保存当前行的数据
				Object[] rowDataArray = new Object[cellNum];
				for (int j = 0; j < cellNum; j++) {
					// 遍历到每一列
					Cell cell = row.getCell(j);
					// 设置所有的cell为string
					cell.setCellType(CellType.STRING);
					// 获取当前cell值
					String cellValue = cell.getStringCellValue();
					// 保存cell的值
					rowDataArray[j] = cellValue;
				}
				// 把一维数组放到二维数组
				datasArray[i] = rowDataArray;
			}
		} catch (Exception e) {
			// 日志信息
			e.printStackTrace();
		} finally {
			close(is, workbook);
		}
		return datasArray;
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

	public static void methodMedium() throws IOException, InvalidFormatException {
		// 1.Excel文件流
		InputStream is = ExcelUtils.class.getResourceAsStream("/excel/testExcel.xlsx");
		// 2.获得工作薄
		Workbook workbook = WorkbookFactory.create(is);
		// 3.获得第一个sheet表
		Sheet sheet = workbook.getSheetAt(0);
		// 4.获得最后一行,然后遍历出所有行
		int rowNum = sheet.getLastRowNum();
		for (int i = 0; i <= rowNum; i++) {
			// 获得对应行
			Row row = sheet.getRow(i);
			// 获得最大的列数
			int cellNum = row.getLastCellNum();
			// System.out.println(cellNum);
			for (int j = 0; j < cellNum; j++) {
				// 遍历到每一列
				Cell cell = row.getCell(j);
				// 设置所有的cell为string
				cell.setCellType(CellType.STRING);
				System.out.print(cell.getStringCellValue() + " ");
			}
			System.out.println();
		}
	}

	public static void methodBase() throws Exception {
		// 1.Excel文件流
		InputStream is = ExcelUtils.class.getResourceAsStream("/excel/baseExcelWork.xlsx");
		// 2.获得工作薄
		Workbook workbook = WorkbookFactory.create(is);
		// 3.获得第一个sheet表
		Sheet sheet = workbook.getSheetAt(0);
		// 4.获得i行
		Row row = sheet.getRow(3);
		// 5.获得j列
		Cell cell = row.getCell(4);
		System.out.println(cell.getStringCellValue());
	}
}
