package base03;

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
	/**
	 * @Desc 读取
	 * @return
	 */
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
		String excelPath = "/base02/testcase.xlsx";
		Object[][] readExcel = readExcel(excelPath,0);
		for (Object[] objects : readExcel) {
			for (Object object : objects) {
				System.out.print(object + " ");
			}
			System.out.println();
		}
	}
}
