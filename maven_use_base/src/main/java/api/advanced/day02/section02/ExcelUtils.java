package api.advanced.day02.section02;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils {

	public static void main(String[] args) throws Exception {
		Object[][] datas = ExcelUtils.readExcel("/testcase.xlsx",0);
		for (Object[] objects : datas) {
			for (Object object : objects) {
				System.out.print(object + "              ");
			}
			System.out.println();
		}
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
