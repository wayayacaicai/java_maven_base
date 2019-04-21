/**
 * 
 */
package api.advanced.data_check.pojo;

/**
 * @Desc:Excel的模板抽象类
 * @author:zpp
 * @time:2019年4月11日 上午1:12:56
 */
public abstract class ExcelSheetObject {
	/**
	 * 行号 1-based
	 */
	private int rowNo;

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
}
