package api.advanced.reflect;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @Desc:excel工具类(反射型)
 * @author:zpp
 * @time:2019年4月1日 下午8:18:53
 */
public class ExcelUtilsReflect {
	public static void main(String[] args) throws Exception {
		// 打印excel所有数据
//		List<Object> readExcel = readExcel("/excel/reflect/excelRflect.xlsx", 0,ApiInfo.class);
		List<Object> readExcel = readExcel("/excel/reflect/excelRflect.xlsx", 1,ApiCaseDetail.class);
		for (Object object : readExcel) {
			System.out.println(object);
		}
	}

	/**
	 * @Desc 不打印第一行，需要注意少一行，并且索引应该从0开始
	 * @param excelPath
	 * @param index
	 * @return
	 */
	public static List<Object> readExcel(String excelPath, int index,Class clazz) {
		InputStream is = null; // 定义流
		Workbook workbook = null; // 定义工作薄
		List<Object> aList = new ArrayList<>(); //反射型的
		try {
			is = ExcelUtilsReflect.class.getResourceAsStream(excelPath); // Excel文件流
			workbook = WorkbookFactory.create(is); // 获得工作薄
			Sheet sheet = workbook.getSheetAt(index); // 获得sheet表
			int lastRowNum = sheet.getLastRowNum(); // 最后一行		
			
			//-------------读第一行数据start--------------------
			Row firstRow = sheet.getRow(0); //获取所有的字段（列名）
			int firstColNum = firstRow.getLastCellNum(); //得到第一行的列数
			String[] fieldArray = new String[firstColNum]; //列名的数组
			for(int m=0;m<firstColNum;m++){
				Cell firstRowCell = firstRow.getCell(m); //得到列
				firstRowCell.setCellType(CellType.STRING); //设置所有的cell为string
				String firstRowCellValue = firstRowCell.getStringCellValue(); //得到列值
				//拿到真正的列名
				String fieldName = firstRowCellValue.substring(0, firstRowCellValue.indexOf("("));
				fieldArray[m] = fieldName; //字段名保存到字段数组中
			}
			//-------------读第一行数据end--------------------
					
			for (int i = 1; i <= lastRowNum; i++) { // 第一行不要，从第二行开始
				Row row = sheet.getRow(i); // 遍历每一行
				int lastCellNum = row.getLastCellNum(); // 总列数
				//反射型的
				Object object = clazz.newInstance();  //创建一个字节码对象
							
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j); // 遍历每一列
					cell.setCellType(CellType.STRING); // 设置所有的cell为string
					String cellValue = cell.getStringCellValue(); // 获取当前cell值
									
					//反射型的
					String currentFieldName = fieldArray[j];  //拿到当前的列名
					String setName = "set" + currentFieldName; //拿到set方法
					Method setMethod = clazz.getMethod(setName, String.class); //获得对应的方法
					setMethod.invoke(object, cellValue); //反射调用方法，完成对象的属性设置				
				}			
				//反射型的
				aList.add(object);			
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(is, workbook);
		}
		return aList;
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
