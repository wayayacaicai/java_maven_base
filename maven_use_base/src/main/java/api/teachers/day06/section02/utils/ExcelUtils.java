package api.teachers.day06.section02.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import api.teachers.day06.section02.pojo.ApiCaseDetail;
import api.teachers.day06.section02.pojo.CellData;
import api.teachers.day06.section02.pojo.ExcelSheetObject;

public class ExcelUtils {

	//我不知道是哪个类型，但是一定是继承自ExcelSheetObject的类型
	public static List<? extends ExcelSheetObject> readExcel(String excelPath, int sheetIndex, Class<? extends ExcelSheetObject> clazz) {
		//准备一个容器，来存放所有的行对应的对象
		List<ExcelSheetObject> allDataList = new ArrayList<ExcelSheetObject>();
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
			/**-------------------读第一行数据 start---------------------------------- */

			//得到所有的字段（得到对象的属性）-->第一行--》遍历第一行的所有列
			Row firstRow = sheet.getRow(0);
			//得到第一行的列数
			int firstRowCellNum = firstRow.getLastCellNum();
			//创建一个容器，保存所有的字段
			String[] fieldArray = new String[firstRowCellNum];
			//遍历第一行的每一列
			for (int i = 0; i < firstRowCellNum; i++) {
				//获得对应索引的列
//				Cell cell = firstRow.getCell(i);
				Cell cell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				//所有的cell当成字符串处理
				cell.setCellType(CellType.STRING);
				//获得当前cell的值
				String firstRowCellValue = cell.getStringCellValue();
				//截取出字段名，忽略掉备注信息
				String fieldName = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				//把字段名保存到字段容器中
				fieldArray[i] = fieldName;
				//				System.out.print(fieldName + "   ");
			}
			/**-------------------读第一行数据 end---------------------------------- */

			//遍历所有的行
			for (int i = 1; i <= lastRowNum; i++) {
				//获得对应的行
				Row row = sheet.getRow(i);
				//获得的最大的列数
//				int lastCellNum = row.getLastCellNum();
				//创建一个数组，保存当前行的数据
				Object[] rowDataArray = new Object[firstRowCellNum];
				//如果读的是第一个sheet--》ApiInfo对象，第二个sheet--》ApiCaseDetail对象
				//生成一个对应的对象来：ApiInfo?ApiCaseDetail?ApiXXX,ApiNNN,ApiMMM.....
				//				Object obj = clazz.newInstance();
				//要生成对象：1：new   2：构造方法反射  3：clazz.newInstance
				ExcelSheetObject obj = clazz.newInstance();//clazz是ApiInfo，用Object类型接收，本质还是ApiInfo
				//设置行对应的行号属性
				obj.setRowNo(i + 1);

				//获得每行的列
				for (int j = 0; j < firstRowCellNum; j++) {
					//获得对应索引的列
					Cell cell = row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//所有的cell当成字符串处理
					cell.setCellType(CellType.STRING);
					//获得当前cell的值
					String cellValue = cell.getStringCellValue();
					//把值保存到数组
					rowDataArray[j] = cellValue;
					/**-------------------反射设值 start---------------------------------- */
					//拿到当前列对应的字段名
					String currentFieldName = fieldArray[j];
					//拼接处对应的setter方法：CaseId --》setCaseId,IsExcute-->setIsExcute
					String setterName = "set" + currentFieldName;
					//获得对应的方法
					Method setterMethod = clazz.getMethod(setterName, String.class);
					//反射调用方法，完成对象的属性设值
					setterMethod.invoke(obj, cellValue);
					/**-------------------反射设值 end---------------------------------- */
				}

				//				System.out.println(obj);
				allDataList.add(obj);
			}
		} catch (Exception e) {
			//日志信息
			e.printStackTrace();
		} finally {
			close(is, workbook);
		}
		return allDataList;
	}

	/**
	 * 读取excel
	 * @param excelPath excel注释
	 * @param sheetIndex 表单索引：从0开始
	 * @return
	 */
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
				//如果读的是第一个sheet--》ApiInfo对象，第二个sheet--》ApiCaseDetail对象
				//生成一个对应的对象来：ApiInfo?ApiCaseDetail?
				//				Object obj = clazz.newInstance();

				//获得每行的列
				for (int j = 0; j < lastCellNum; j++) {
					//获得对应索引的列
//					Cell cell = row.getCell(j);
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					//所有的cell当成字符串处理
					cell.setCellType(CellType.STRING);
					//获得当前cell的值
					String cellValue = cell.getStringCellValue();
					//把值保存到数组
					rowDataArray[j] = cellValue;
				}

				//把一行的数据（一维数组）--》二维数组
				allDatasArray[i - 1] = rowDataArray;
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
		close(is, workbook,null);
	}
	
	private static void close(InputStream is, Workbook workbook, OutputStream os) {
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
		if (os != null) {
			try {
				os.close();
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
	
	
	//数据的回写：响应的结构、数据验证的结果--》写到excel中去
	//poi
	//static:类方法、工具方法
	//写回到某一个单元格去，需要准备什么数据：源excel路径、目标excel路径、sheet索引、行号、列号、要写回的内容
	/**
	 * 写excel
	 * @param sourceExcelPath 源路径
	 * @param targetExcelPath 目标路径
	 * @param sheetIndex 索引，0-based 
	 * @param rowNo 行号，1-based 
	 * @param cellNo 列号，1-based 
	 * @param cententStr 要写的内容
	 * 
	 * 缺陷：
	 * 	1：如果有1000个测试用例，1000次读写excel--》不合理 --》 读一次，写一次（所有的响应结果）--》收集响应结果
	 *  2：如果说caseId对应的这一行比较靠后面的话，出现没有必要的遍历匹配--》效率低 --》给我一个apiCaseDetail对象，我就能知道在那一行--》行号的属性 rowNo
	 *  
	 */
	public static void writeExcel(String sourceExcelPath,String targetExcelPath,int sheetIndex,String caseId,int cellNo,String cententStr){
		InputStream is = null;
		//新建一个输出流
		OutputStream os = null;
		Workbook workbook = null;
		try {
//			is = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			is = new FileInputStream(new File("target\\classes\\api_test_case_01.xlsx"));
			workbook = WorkbookFactory.create(is);
			//得到第2个sheet
			Sheet sheet = workbook.getSheetAt(sheetIndex);
			
			//通过caseId拿到对应的rowNo（行号）
			//遍历所有的行，拿到每行的caseId这列的内容和传入进来的caseId匹配
			//如果匹配上了，就结束循环，否则继续循环匹配
			int lastRowNum = sheet.getLastRowNum();
			for(int i=1;i<=lastRowNum;i++){
				//得到当前行
				Row currentRow = sheet.getRow(i);
				//拿到第一列的内容
				Cell firstCell = currentRow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				firstCell.setCellType(CellType.STRING);
				//获得对应的内容
				String value = firstCell.getStringCellValue();
				if (value.equals(caseId)) {
					//i此时的值就是我们的行号
					//如果是空单元格，就作为空白的单元格
					Cell cell = currentRow.getCell(cellNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					//cell设值数据
					cell.setCellValue(cententStr);
					break;
				}
				
			}
//			os = new FileOutputStream(new File(targetExcelPath));
			//  src/main/resources/login.xlsx
			// ‪ target\classes\api_test_case_01.xlsx
			os = new FileOutputStream(new File("target\\classes\\api_test_case_01.xlsx"));
			//写谁？？
			workbook.write(os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, workbook, os);
		}
		
	}
	
	public static void batchWriteExcel(String sourceExcelPath,String targetExcelPath,int sheetIndex){
		InputStream is = null;
		//新建一个输出流
		OutputStream os = null;
		Workbook workbook = null;
		try {
			is = ExcelUtils.class.getResourceAsStream(sourceExcelPath);
			workbook = WorkbookFactory.create(is);
			//得到第2个sheet
			Sheet sheet = workbook.getSheetAt(sheetIndex);
		
			List<CellData> cellDatas = ApiUtils.getCellDataList();
			for (CellData cellData : cellDatas) {
				int rowNo = cellData.getRowNo();//行号
				int coloumNo = cellData.getColumnNo();//列号
				String content = cellData.getContent();//要写的内容
				//拿到要写的行
				Row currentRow = sheet.getRow(rowNo - 1);
				//拿到要写的列
				Cell cell = currentRow.getCell(coloumNo - 1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				//设置要写的内容
				cell.setCellValue(content);
			}	
			//写回excel
			os = new FileOutputStream(new File(targetExcelPath));
			workbook.write(os);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, workbook, os);
		}
		
	}

	
	//把什么内容写回excel：
	//主观
	//1：用来调试
	//2:example：案例
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		/*InputStream is = ExcelUtils.class.getResourceAsStream("/api_test_case_01.xlsx");
		Workbook workbook = WorkbookFactory.create(is);
		//得到第2个sheet
		Sheet sheet = workbook.getSheetAt(1);
		//得到第2行
		Row row = sheet.getRow(1);
		//得到第6列
//		Cell cell = row.getCell(5);
		//如果是空单元格，就作为空白的单元格
		//
		Cell cell = row.getCell(5, MissingCellPolicy.CREATE_NULL_AS_BLANK);
		
		//cell设值数据
		cell.setCellValue("java10期的同学们大家好");
		//新建一个输出流
		OutputStream os = new FileOutputStream(new File("d://api_test_case_02.xlsx"));
		//写谁？？
		workbook.write(os);
		
		os.close();
		is.close();*/
		
//		writeExcel("/api_test_case_01.xlsx", "d://api_test_case_2019-4-9.xlsx", 1, 2, 6, "java10期的同学们大家好!!!!!!!!!");
		
//		List<Object> apiInfoList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 0, ApiInfo.class);
//		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
//		for (Object object : apiCaseDetailList) {
//			System.out.println(object);
//		}
		List<ApiCaseDetail> excelSheetObjects = (List<ApiCaseDetail>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
		for (ApiCaseDetail apiCaseDetail : excelSheetObjects) {
			System.out.println(apiCaseDetail);
		}
	}
	
	

}
