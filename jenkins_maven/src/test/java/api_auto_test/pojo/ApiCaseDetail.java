package api_auto_test.pojo;

import java.util.List;

/**
 * @Desc:接口详细信息类
 * @author:zpp
 * @time:2019年4月3日 下午9:24:31
 */
public class ApiCaseDetail extends ExcelSheetObject {
	// CaseId(用例编号)
	private String caseId;
	// ApiId(接口编号)
	private String apiId;
	// IsExcute(是否执行)
	private String isExcute;
	// RequestData(接口请求参数)
	private String requestData;
	// ExpectedReponseData(期望响应数据)
	private String expectedResponseData;
	// ActualReponseData(实际响应数据)
	private String actualResponseData;
	//SqlCheckResult(数据验证结果)
	private String sqlCheckResult;
	//ReqHeader(设置Header)	
	private String reqHeader;
	//RespHeader(提取Header数据)
	private String respHeader;
	//ExtractRespData(提取响应数据)	
	private String extractRespData;
	//AssertKeyInfo(关键信息断言)
	private String assertKeyInfo;
	// 接口基本信息对象
	private ApiInfo apiInfo;
	// 前置验证的sql信息列表
	private List<SqlChecker> beforeSqlList;
	// 后置验证的sql信息列表
	private List<SqlChecker> afterSqlList;

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getIsExcute() {
		return isExcute;
	}

	public void setIsExcute(String isExcute) {
		this.isExcute = isExcute;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public String getExpectedResponseData() {
		return expectedResponseData;
	}

	public void setExpectedResponseData(String expectedReponseData) {
		this.expectedResponseData = expectedReponseData;
	}

	public String getActualResponseData() {
		return actualResponseData;
	}

	public void setActualResponseData(String actualResponseData) {
		this.actualResponseData = actualResponseData;
	}

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	public List<SqlChecker> getBeforeSqlList() {
		return beforeSqlList;
	}

	public void setBeforeSqlList(List<SqlChecker> beforeSqlList) {
		this.beforeSqlList = beforeSqlList;
	}

	public List<SqlChecker> getAfterSqlList() {
		return afterSqlList;
	}

	public void setAfterSqlList(List<SqlChecker> afterSqlList) {
		this.afterSqlList = afterSqlList;
	}

	public String getSqlCheckResult() {
		return sqlCheckResult;
	}

	public void setSqlCheckResult(String sqlCheckResult) {
		this.sqlCheckResult = sqlCheckResult;
	}

	public String getReqHeader() {
		return reqHeader;
	}

	public void setReqHeader(String reqHeader) {
		this.reqHeader = reqHeader;
	}

	public String getRespHeader() {
		return respHeader;
	}

	public void setRespHeader(String respHeader) {
		this.respHeader = respHeader;
	}

	public String getExtractRespData() {
		return extractRespData;
	}

	public void setExtractRespData(String extractRespData) {
		this.extractRespData = extractRespData;
	}

	public String getAssertKeyInfo() {
		return assertKeyInfo;
	}

	public void setAssertKeyInfo(String assertKeyInfo) {
		this.assertKeyInfo = assertKeyInfo;
	}

	@Override
	public String toString() {
		return "ApiCaseDetail [caseId=" + caseId + ", apiId=" + apiId + ", isExcute=" + isExcute + ", requestData="
				+ requestData + ", expectedResponseData=" + expectedResponseData + ", actualResponseData="
				+ actualResponseData + ", sqlCheckResult=" + sqlCheckResult + ", reqHeader=" + reqHeader
				+ ", respHeader=" + respHeader + ", extractRespData=" + extractRespData + ", assertKeyInfo="
				+ assertKeyInfo + ", apiInfo=" + apiInfo + ", beforeSqlList=" + beforeSqlList + ", afterSqlList="
				+ afterSqlList + ", getRowNo()=" + getRowNo() + "]";
	}

}
