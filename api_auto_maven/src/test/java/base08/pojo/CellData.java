package base08.pojo;

/**
 * @Desc:一个Excel单元格的基本类 
 * @author:zpp 
 * @time:2019年6月7日 下午5:00:34
 */
public class CellData {
	//行号 base-1
	private int rowNo;
	//列号 base-1
	private int colNo;
	//单元格的内容
	private String content;
	
	public CellData(int rowNo,int colNo,String content){
		this.rowNo = rowNo;
		this.colNo = colNo;
		this.content = content;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "CellData [rowNo=" + rowNo + ", colNo=" + colNo + ", content=" + content + "]";
	}
	
	
	
}
