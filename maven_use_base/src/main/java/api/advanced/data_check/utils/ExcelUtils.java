package api.advanced.data_check.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import api.advanced.data_check.pojo.CellData;
import api.advanced.data_check.pojo.ExcelSheetObject;

/**
 * @Desc:excel工具类(反射型) 注意回写的时候增加了一列（返回回写数据），所以要注意在对应对象增加相关的属性
 * @author:zpp
 * @time:2019年4月1日 下午8:18:53
 */
public class ExcelUtils {
	// 创建日志对象
	private static Logger logger = Logger.getLogger(ExcelUtils.class);

	/**
	 * @Desc 不打印第一行，需要注意少一行，并且索引应该从0开始
	 * @Desc 用泛型约束对象，只能是继承了ExcelSheetObject抽象类的子类
	 * @param excelPath
	 * @param index
	 * @param clazz
	 * @return
	 */
	public static List<? extends ExcelSheetObject> readExcel(String excelPath, int index,
			Class<? extends ExcelSheetObject> clazz) {
		InputStream is = null; // 定义流
		Workbook workbook = null; // 定义工作薄
		List<ExcelSheetObject> excelSheetObjectsList = new ArrayList<ExcelSheetObject>(); // 反射型的
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath); // Excel文件流
			workbook = WorkbookFactory.create(is); // 获得工作薄
			Sheet sheet = workbook.getSheetAt(index); // 获得sheet表
			int lastRowNum = sheet.getLastRowNum(); // 最后一行

			// -------------读第一行数据start--------------------
			Row firstRow = sheet.getRow(0); // 获取第一行的数据
			int colNum = firstRow.getLastCellNum(); // 得到第一行的列数
			String[] fieldArray = new String[colNum]; // 列名的数组
			for (int m = 0; m < colNum; m++) {
				Cell firstRowCell = firstRow.getCell(m, MissingCellPolicy.CREATE_NULL_AS_BLANK); // 得到列
				firstRowCell.setCellType(CellType.STRING); // 设置cell为String类型
				String firstRowCellValue = firstRowCell.getStringCellValue(); // 得到列值
				// 拿到真正的列名
				String fieldName = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				fieldArray[m] = fieldName; // 字段名保存到字段数组中
			}
			// -------------读第一行数据end--------------------

			for (int i = 1; i <= lastRowNum; i++) { // 第一行不要，从第二行开始
				Row row = sheet.getRow(i); // 得到行数据
				// 反射型的
				ExcelSheetObject excelSheetObject = clazz.newInstance(); // 创建一个字节码对象
				// 设置属性 行号比索引大1
				excelSheetObject.setRowNo(i + 1);

				for (int j = 0; j < colNum; j++) {
					// 可以解决空指针问题
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING); // 设置cell为String
					String cellValue = cell.getStringCellValue(); // 获取当前cell值

					// 反射型的
					String currentFieldName = fieldArray[j]; // 拿到当前的列名
					String setMethod = "set" + currentFieldName; // 拿到set方法
					Method method = clazz.getMethod(setMethod, String.class); // 获得对应的方法
					//单元格的值是有参数的，先进行正则匹配和参数替换
					String replacedStr = ParameterUtils.getReplacedStr(cellValue); 
					method.invoke(excelSheetObject, replacedStr); // 反射调用方法，完成对象的属性设置
				}
				// 反射型的
				excelSheetObjectsList.add(excelSheetObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("操作excel反射返回List出现异常：" + e.getMessage());
		} finally {
			close(is, workbook);
		}
		return excelSheetObjectsList;
	}

	/**
	 * @Desc 写回excel的进阶版
	 * @param sourceExcelPath
	 * @param targetExcelPath
	 * @param sheetIndex
	 * @param allDatas
	 */
	public static void writeExcel(String sourceExcelPath, String targetExcelPath, int sheetIndex,
			List<CellData> allDatas) {
		InputStream is = null;
		Workbook workbook = null;
		OutputStream os = null;
		try {
			is = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			// is = new FileInputStream(new File(sourceExcelPath));
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetIndex);

			for (CellData cellData : allDatas) {
				int rowNo = cellData.getRowNo();
				int colNo = cellData.getColNo();
				String cellValue = cellData.getCellValue();
				Row row = sheet.getRow(rowNo - 1);
				Cell cell = row.getCell(colNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(cellValue);
			}
			os = new FileOutputStream(new File(targetExcelPath));
			workbook.write(os);
		} catch (Exception e) {
			logger.error("数据回写出现异常:" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(is, workbook, os);
		}
	}

	/**
	 * @Desc 一次性把多个sheet的数据都进行写回
	 * @param sourceExcelPath
	 * @param targetExcelPath
	 * @param cellDatas
	 * @param sqlDatas
	 */
	public static void writeAllExcel(String sourceExcelPath, String targetExcelPath, List<CellData> cellDatas,
			List<CellData> sqlDatas) {
		InputStream is = null;
		Workbook workbook = null;
		OutputStream os = null;
		try {
			is = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			// is = new FileInputStream(new File(sourceExcelPath));
			workbook = WorkbookFactory.create(is);

			Sheet sheet1 = workbook.getSheetAt(1);
			for (CellData cellData : cellDatas) {
				int rowNo = cellData.getRowNo();
				int colNo = cellData.getColNo();
				String cellValue = cellData.getCellValue();
				Row row = sheet1.getRow(rowNo - 1);
				Cell cell = row.getCell(colNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(cellValue);
			}

			Sheet sheet2 = workbook.getSheetAt(2);
			for (CellData cellData : sqlDatas) {
				int rowNo = cellData.getRowNo();
				int colNo = cellData.getColNo();
				String cellValue = cellData.getCellValue();
				Row row = sheet2.getRow(rowNo - 1);
				Cell cell = row.getCell(colNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(cellValue);
			}
			os = new FileOutputStream(new File(targetExcelPath));
			workbook.write(os);
		} catch (Exception e) {
			logger.error("数据回写出现异常:" + e.getMessage());
			e.printStackTrace();
		} finally {
			close(is, workbook, os);
		}
	}

	/**
	 * @Desc 注意回写的时候增加了一列，所以要注意在对应对象增加相关的属性
	 * @param sourceExcelPath
	 * @param targetExcelPath
	 * @param sheetIndex
	 * @param caseId
	 * @param cellNo
	 * @param actualInfo
	 *            缺陷 1.假如1000条数据每次都io读写，性能问题--读一次，写一次，收集所有响应结果
	 *            2.如果caseId对应的一行比较靠后，出现没有必要的遍历匹配--效率低
	 *            -给一个apiCaseDetail对象，就知道哪一行
	 */
	@Deprecated
	public static void writeExcel(String sourceExcelPath, String targetExcelPath, int sheetIndex, String caseId,
			int cellNo, String actualInfo) {
		InputStream is = null;
		Workbook workbook = null;
		OutputStream os = null;
		try {
			// 因为用类路径加载是不会变化的，而用相对路径数据能够动态变化
			is = new FileInputStream(new File(sourceExcelPath));
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(sheetIndex);

			int lastRowNum = sheet.getLastRowNum(); // 得到最后一行
			for (int i = 1; i <= lastRowNum; i++) {
				Row currentRow = sheet.getRow(i);
				// 拿到此行第一列的内容
				Cell firstCell = currentRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK); // 比实际列号小1
				firstCell.setCellType(CellType.STRING);
				String firstCellValue = firstCell.getStringCellValue();
				// 通过caseId来匹配到对应的行
				if (firstCellValue.equals(caseId)) {
					// 拿到对应行需要回写数据的列
					Cell cell = currentRow.getCell(cellNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					cell.setCellValue(actualInfo); // cell设置数据
					break; // 拿到对应行设置了数据之后就可以结束当前循环
				}
			}
			os = new FileOutputStream(new File(targetExcelPath));
			workbook.write(os);
		} catch (Exception e) {
			logger.error("数据回写出现异常:" + e.getMessage());
		} finally {
			close(is, workbook, os);
		}
	}

	/**
	 * @Desc Excel读取关闭流对象
	 * @param is
	 * @param workbook
	 */
	private static void close(InputStream is, Workbook workbook) {
		if (workbook != null) {
			try {
				workbook.close();
			} catch (IOException e) {
				logger.error("关闭workbook流出现异常：" + e.getMessage());
			}
		}
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("关闭输入流出现异常：" + e.getMessage());
			}
		}
	}

	/**
	 * @Desc 数据回写关闭流对象
	 * @param is
	 * @param workbook
	 * @param os
	 */
	private static void close(InputStream is, Workbook workbook, OutputStream os) {
		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				logger.error("输出流出现异常:" + e.getMessage());
			}
		}
		if (workbook != null) {
			try {
				workbook.close();
			} catch (IOException e) {
				logger.error("excel文件对象出现异常:" + e.getMessage());
			}
		}
		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				logger.error("输入流出现异常:" + e.getMessage());
			}
		}
	}
}
