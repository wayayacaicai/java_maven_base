package base10.utils;

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

import base10.pojo.ApiCaseDetail;
import base10.pojo.BaseExcelSheet;
import base10.pojo.CellData;

/**
 * @Desc:Excel工具类
 * @author:zpp
 * @time:2019年5月21日 下午5:00:09
 */
public class ExcelUtils {
	/**
	 * @Desc 读取
	 * @return
	 */
	@Deprecated
	public static Object[][] readExcel(String excelPath,int index){
		InputStream is = null;
		Workbook workbook = null;
		Object[][] allDatas = null; //定义二维数组来存取每行数据
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);
			
			int lastRowNum = sheet.getLastRowNum(); //最后一行
			allDatas = new Object[lastRowNum][]; //创建二维数组来存取每行数据
			for(int i=1;i<=lastRowNum;i++){
				Row row = sheet.getRow(i);
				int cellNum = row.getLastCellNum(); //总列数
				Object[] rowData = new Object[cellNum]; //创建一维数组来存取每列数据
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue();
					rowData[j] = cellValue; //一维数组接收每列数据
				}
				allDatas[i-1] = rowData; //二维数组接收每行数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(is, workbook);
		}
		return allDatas;
	}
	
	/**
	 * @Desc 读取,字节码对象定义泛型，限制约束条件，并且设置了行号
	 * @Desc 读取,在读取数据时，反射的时候进行参数替换
	 * @param excelPath
	 * @param index
	 * @param clazz
	 * @return
	 */
	public static List<? extends BaseExcelSheet> readExcel(String excelPath,int index,Class<? extends BaseExcelSheet> clazz){
		InputStream is = null;
		Workbook workbook = null;
		List<BaseExcelSheet> aList = null; //定义列表接收所有对象
		try {
			is = ExcelUtils.class.getResourceAsStream(excelPath);
			workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(index);
			
			//------获取第一行表头各个字段------
			Row firstRow = sheet.getRow(0); //获取第一行
			int cellNum = firstRow.getLastCellNum(); //获取总列数
			String[] fieldArray = new String[cellNum]; //定义接收表头字段的数组
			for(int i=0;i<cellNum;i++){
				Cell cell = firstRow.getCell(i, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				String cellValue = cell.getStringCellValue();
				String fieldCellValue = cellValue.substring(0, cellValue.indexOf("("));
				fieldArray[i] = fieldCellValue;
			}
			
			int lastRowNum = sheet.getLastRowNum(); //最后一行
			aList = new ArrayList<>(); //创建列表接收所有对象
					
			for(int i=1;i<=lastRowNum;i++){
				Row row = sheet.getRow(i);
				BaseExcelSheet baseExcelSheet = clazz.newInstance(); //创建一个对象来存取每列数据
				
				baseExcelSheet.setRowNo(i + 1); //设置行号
				for(int j=0;j<cellNum;j++){
					Cell cell = row.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String cellValue = cell.getStringCellValue(); //得到单元格的值
					//进行参数替换后的数据,此处注释掉，
					//因为还要做响应数据提取和数据依赖以及断言数据所以需要在请求之前替换
					//还有在Excel中替换的地方都需要注意，比如第二张表和第三张表的数据
//					cellValue = ParamsOperUtils.getReplaceJsonData(cellValue); 
					String fieldName = fieldArray[j]; //得到列名
					String setFieldName = "set" + fieldName; //得到set方法
					Method method = clazz.getMethod(setFieldName, String.class);
					method.invoke(baseExcelSheet, cellValue);
				}
				aList.add(baseExcelSheet); //用列表接收每行数据
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(is, workbook);
		}
		return aList;
	}
	
	
	/**
	 * @Desc  一次性回写完数据，不用重复读写数据
	 * @param srcExcelPath
	 * @param destExcelPath
	 * @param sheetIndex
	 * @param cellDataList
	 */
	public static void writeExcelAll(String srcExcelPath,String destExcelPath,
			int sheetIndexOne,List<CellData> cellDataList,
			int sheetIndexTwo,List<CellData> sqlCheckerList){
		InputStream is = null;
		Workbook workbook = null;
		OutputStream os = null;
		try {
			is = new FileInputStream(new File(srcExcelPath));
			workbook = WorkbookFactory.create(is);
			
			Sheet sheetOne = workbook.getSheetAt(sheetIndexOne);
			//回写用例结果
			for (CellData cellData : cellDataList) {
				int rowNo = cellData.getRowNo();//行号base-1
				int colNo = cellData.getColNo();//列号base-1			
				String content = cellData.getContent();//单元格内容
				
				Row row = sheetOne.getRow(rowNo-1);
				Cell cell = row.getCell(colNo-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(content);
			}
			
			Sheet sheetTwo = workbook.getSheetAt(sheetIndexTwo);
			//回写数据验证结果
			for (CellData cellData : sqlCheckerList) {
				int rowNo = cellData.getRowNo();//行号base-1
				int colNo = cellData.getColNo();//列号base-1			
				String content = cellData.getContent();//单元格内容
				
				Row row = sheetTwo.getRow(rowNo-1);
				Cell cell = row.getCell(colNo-1, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(content);
			}
			
			os = new FileOutputStream(new File(destExcelPath));
			workbook.write(os);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(is, workbook, os);
		}
	}
	
	
	/**
	 * @Desc 写入excel数据的流关闭
	 * @param is
	 * @param workbook
	 * @param os
	 */
	private static void close(InputStream is, Workbook workbook, OutputStream os) {
		if(os != null){
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(workbook != null){
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @Desc 读取excel数据的流关闭
	 * @param is
	 * @param workbook
	 */
	private static void close(InputStream is, Workbook workbook) {
		if(workbook !=null){
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		String excelPath = "/base06/testcase.xlsx";
		List<ApiCaseDetail> readExcel = (List<ApiCaseDetail>) readExcel(excelPath,1,ApiCaseDetail.class);
		for (ApiCaseDetail apiCaseDetail : readExcel) {
			System.out.println(apiCaseDetail);
		}
	}
}
