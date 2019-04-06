package api.medium_practice.array;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Desc:excel读取最终版
 * @author:zpp
 * @time:2019年4月1日 下午8:18:53
 */
public class ExcelUtilsUpdate {
	public static void main(String[] args) throws Exception {
		// 打印excel所有数据
		String excelPath = "/excel/baseExcelWork.xlsx";
		Object[][] allDatas = readExcel(excelPath, 0);
		for (Object[] objects : allDatas) {
			for (Object object : objects) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
	}

	/**
	 * @Desc 不打印第一行，需要注意少一行，并且索引应该从0开始
	 * @param excelPath
	 * @param index
	 * @return
	 */
	public static Object[][] readExcel(String excelPath, int index) {
		InputStream is = null; // 定义流
		Workbook workbook = null; // 定义工作薄
		Object[][] allDatasArray = null; // 定义二维数组
		try {
			is = ExcelUtilsUpdate.class.getResourceAsStream(excelPath); // Excel文件流
			workbook = WorkbookFactory.create(is); // 获得工作薄
			Sheet sheet = workbook.getSheetAt(index); // 获得sheet表

			int lastRowNum = sheet.getLastRowNum(); // 最后一行
			allDatasArray = new Object[lastRowNum][]; // 需要减少一行，二维数组大小
			for (int i = 1; i <= lastRowNum; i++) { // 第一行不要，从第二行开始
				Row row = sheet.getRow(i); // 遍历每一行
				int lastCellNum = row.getLastCellNum(); // 总列数
				Object[] rowDatasArray = new Object[lastCellNum]; // 一维数组大小
				for (int j = 0; j < lastCellNum; j++) {
					// Cell cell = row.getCell(j); // 遍历每一列
					//可以解决空指针问题
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING); // 设置所有的cell为string
					String cellValue = cell.getStringCellValue(); // 获取当前cell值
					rowDatasArray[j] = cellValue; // 保存cell的值
				}
				allDatasArray[i - 1] = rowDatasArray; // i=1，所以i需要从零开始，把一维数组放到二维数组
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
}
