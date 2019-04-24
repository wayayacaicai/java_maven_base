package api.teachers.day08.section01.pojo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.teachers.day08.section01.utils.ExcelUtils;

/**
 * excel中第3个sheet每行
 * @author happy
 * @date 2019年4月18日
 * @desc 
 * @email
 */
public class SqlChecker extends ExcelSheetObject {

	//	SqlId(sql的id)	
	private String sqlId;
	//	CaseId(测试用例id)	
	private String caseId;
	//	Type(前置还是后置)	
	private String type;
	//	Sql(要验证的sql)	
	private String sql;
	//	ExpectedResult(期望结果)
	private String expectedResult;
	
//	ActualResult(实际结果)
	private String actualResult;
//	CheckResult(验证结果)
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

	@Override
	public String toString() {
		return "SqlChecker [sqlId=" + sqlId + ", caseId=" + caseId + ", type=" + type + ", sql=" + sql
				+ ", expectedResult=" + expectedResult + ", actualResult=" + actualResult + ", checkResult="
				+ checkResult + ", getRowNo()=" + getRowNo() + "]";
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

	//Example
	public static void main(String[] args) {
		//读取所有的数据验证的信息
		List<SqlChecker> sqlCheckerList = (List<SqlChecker>) ExcelUtils.readExcel("/api_test_case_01.xlsx", 2,
				SqlChecker.class);

		Map<String, List<SqlChecker>> sqlCheckerMap = new HashMap<String, List<SqlChecker>>();
		//循环每一个数据验证信息
		for (SqlChecker sqlChecker : sqlCheckerList) {
			//需要方法哪个对应的篮子
			String key = sqlChecker.getCaseId() + "-" + sqlChecker.getType();
			//拿到1-before这个key对应的容器
			List<SqlChecker> subSqlCheckerList = sqlCheckerMap.get(key);
			//首先要判断是不是已经有放当前sql的容器了吧
			if (subSqlCheckerList == null) {
				subSqlCheckerList = new ArrayList<SqlChecker>();
			}
			//1:把当前的sql添加到对应容器
			subSqlCheckerList.add(sqlChecker);
			//caseId为1的前置sql --》1-before
			//caseId为2的后置sql --》2-after
			sqlCheckerMap.put(key, subSqlCheckerList);
		}

	}

}
