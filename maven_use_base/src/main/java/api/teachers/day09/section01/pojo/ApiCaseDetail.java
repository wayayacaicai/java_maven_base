package api.teachers.day09.section01.pojo;

import java.util.List;

/**
 * 测试用例详细信息
 * @author happy
 * @date 2019年4月2日
 * @desc 
 * @email
 */
public class ApiCaseDetail extends ExcelSheetObject {

	//	CaseId(用例编号)	
	private String caseId;
	//	ApiId(接口编号)	
	private String apiId;
	//	IsExcute(是否执行)	
	private String isExcute;
	//	RequestData(接口请求参数)	
	private String requestData;
	//	ExpectedReponseData(期望响应数据)
	private String expectedReponseData;
	//	ActualReponseData(实际响应数据)
	private String ActualReponseData;
	//SqlCheckResult(数据验证结果:所有的sql验证通过则写回成功，否则写回失败)
	private String sqlCheckResult;
	
//	ReqHeader(设置Header)	
	private String reqHeader;
	
//	RespHeader(提取Header数据) 
	private String  respHeader;
	
//	ExtractRespData(提取响应数据)	
	private String extractRespData;
	
//	RespKeyInfo(提取响应数据)
	private String assertKeyInfo;



	//对应的接口信息（每条测试用例都对应一条接口信息）
	private ApiInfo apiInfo;
	
	//一个测试用例对应多个前置验证sql
	
	//前置验证的sql信息列表
	private List<SqlChecker> beforeSqlList;
	
	//后置验证的sql信息列表
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

	public String getExpectedReponseData() {
		return expectedReponseData;
	}

	public void setExpectedReponseData(String expectedReponseData) {
		this.expectedReponseData = expectedReponseData;
	}

	public ApiInfo getApiInfo() {
		return apiInfo;
	}

	public void setApiInfo(ApiInfo apiInfo) {
		this.apiInfo = apiInfo;
	}

	public String getActualReponseData() {
		return ActualReponseData;
	}

	public void setActualReponseData(String actualReponseData) {
		ActualReponseData = actualReponseData;
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
				+ requestData + ", expectedReponseData=" + expectedReponseData + ", ActualReponseData="
				+ ActualReponseData + ", sqlCheckResult=" + sqlCheckResult + ", reqHeader=" + reqHeader
				+ ", respHeader=" + respHeader + ", extractRespData=" + extractRespData + ", assertKeyInfo="
				+ assertKeyInfo + ", apiInfo=" + apiInfo + ", beforeSqlList=" + beforeSqlList + ", afterSqlList="
				+ afterSqlList + ", getRowNo()=" + getRowNo() + "]";
	}

}
