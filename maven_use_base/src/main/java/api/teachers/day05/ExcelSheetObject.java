package api.teachers.day05;

/**
 * 所有sheet对应的都是ExcelSheetObject
 * 模板设计
 * @author happy
 * @date 2019年4月9日
 * @desc 
 * @email
 */
public abstract class ExcelSheetObject {
	
	/**
	 * 行号：1-based
	 */
	private int rowNo;

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	

}
