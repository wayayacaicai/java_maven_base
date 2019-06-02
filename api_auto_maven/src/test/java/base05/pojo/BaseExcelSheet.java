package base05.pojo;

/**
 * @Desc:基础excel的sheet的信息类 
 * @author:zpp 
 * @time:2019年5月31日 上午10:22:29
 */
public abstract class BaseExcelSheet {
	//行号 1-based
	private int rowNo;

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
	
}
