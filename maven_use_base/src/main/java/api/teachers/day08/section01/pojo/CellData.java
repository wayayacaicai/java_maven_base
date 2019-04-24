package api.teachers.day08.section01.pojo;

/**
 * 要写回excel一个单元格的信息描述
 * @author happy
 * @date 2019年4月16日
 * @desc 
 * @email
 */
public class CellData {

	private int rowNo;//行号

	private int columnNo;//列号

	private String content;//要写的内容

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int columnNo) {
		this.columnNo = columnNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public CellData(int rowNo, int columnNo, String content) {
		super();
		this.rowNo = rowNo;
		this.columnNo = columnNo;
		this.content = content;
	}

	@Override
	public String toString() {
		return "CellData [rowNo=" + rowNo + ", columnNo=" + columnNo + ", content=" + content + "]";
	}

}
