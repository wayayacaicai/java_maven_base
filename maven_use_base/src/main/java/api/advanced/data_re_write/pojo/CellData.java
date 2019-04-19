package api.advanced.data_re_write.pojo;

/**
 * @Desc:一个单元格类
 * @author:zpp 
 * @time:2019年4月17日 下午4:28:17
 */
public class CellData {
	//行号
	private int rowNo;
	//列号
	private int colNo;
	//单元格的值
	private String cellValue;
	
	public CellData(int rowNo, int colNo, String cellValue) {
		super();
		this.rowNo = rowNo;
		this.colNo = colNo;
		this.cellValue = cellValue;
	}
	
	public int getRowNo() {
		return rowNo;
	}
	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	public int getColNo() {
		return colNo;
	}
	public void setColNo(int colNo) {
		this.colNo = colNo;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
}
