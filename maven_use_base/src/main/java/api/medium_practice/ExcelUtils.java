package api.medium_practice;

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
 * @time:2019年4月1日 下午8:29:42
 */
public class ExcelUtils {
	public static void main(String[] args) throws Exception {
		// getAllDatas();

		// 打印excel所有数据
		String excelPath = "/excel/testExcel.xlsx";
		Object[][] allDatas = readExcel(excelPath, 0);
		for (Object[] objects : allDatas) {
			for (Object object : objects) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @Desc 读取excel的方法
	 * @param excelPath
	 * @param index
	 * @return
	 */
	public static Object[][] readExcel(String excelPath, int index) {
		InputStream is = null; // 定义流
		Workbook workbook = null; // 定义工作薄
		Object[][] allDatasArray = null; // 定义二维数组
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath); // Excel文件流
			workbook = WorkbookFactory.create(is); // 获得工作薄
			Sheet sheet = workbook.getSheetAt(index); // 获得sheet表

			int lastRowNum = sheet.getLastRowNum(); // 最后一行
			allDatasArray = new Object[lastRowNum + 1][]; // 二维数组大小
			for (int i = 0; i <= lastRowNum; i++) {
				Row row = sheet.getRow(i); // 遍历每一行
				int lastCellNum = row.getLastCellNum(); // 总列数
				Object[] rowDatasArray = new Object[lastCellNum]; // 一维数组大小
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j); // 遍历每一列
					cell.setCellType(CellType.STRING); // 设置所有的cell为string
					String cellValue = cell.getStringCellValue(); // 获取当前cell值
					rowDatasArray[j] = cellValue; // 保存cell的值
				}
				allDatasArray[i] = rowDatasArray; // 把一维数组放到二维数组
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, workbook);
		}
		return allDatasArray;
	}

	/**
	 * @Desc 关闭资源
	 * @param is
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

	/**
	 * @Desc 遍历出excel所有数据的方法
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	private static void getAllDatas() throws IOException, InvalidFormatException {
		InputStream is = ExcelUtils.class.getResourceAsStream("/excel/testExcel.xlsx");
		Workbook workbook = WorkbookFactory.create(is);
		Sheet sheet = workbook.getSheetAt(0);

		int lastRowNum = sheet.getLastRowNum(); // 最后一行
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			int lastCellNum = row.getLastCellNum(); // 总列数
			for (int j = 0; j < lastCellNum; j++) {
				Cell cell = row.getCell(j);
				cell.setCellType(CellType.STRING);
				String cellValue = cell.getStringCellValue();
				System.out.print(cellValue + ",");
			}
			System.out.println();
		}
	}
}
