package base07.pojo;

/**
 * @Desc:数据验证基本类 
 * @author:zpp 
 * @time:2019年6月8日 下午2:21:01
 */
public class SqlChecker extends BaseExcelSheet{
	//SqlId(sql的id)	
	private String sqlId;
	//CaseId(测试用例id)
	private String caseId;
	//Type(前置还是后置)
	private String type;
	//Sql(要验证的sql)	
	private String sql;
	//ExpectedResult(期望结果)	
	private String expectedResult;
	//ActualResult(实际结果)
	private String actualResult;
	//CheckResult(验证结果)
	private String checkResult;
	public String getSqlId() {
		return sqlId;
	}
	public void setSqlId(String sqlId) {
		this.sqlId = sqlId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public String getExpectedResult() {
		return expectedResult;
	}
	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}
	public String getActualResult() {
		return actualResult;
	}
	public void setActualResult(String actualResult) {
		this.actualResult = actualResult;
	}
	public String getCheckResult() {
		return checkResult;
	}
	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
	}
	@Override
	public String toString() {
		return "SqlChecker [sqlId=" + sqlId + ", caseId=" + caseId + ", type=" + type + ", sql=" + sql
				+ ", expectedResult=" + expectedResult + ", actualResult=" + actualResult + ", checkResult="
				+ checkResult + ", getRowNo()=" + getRowNo() + "]";
	}
	
	

}
