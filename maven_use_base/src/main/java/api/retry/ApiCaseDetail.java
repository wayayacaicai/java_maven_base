package api.retry;

public class ApiCaseDetail extends ExcelSheetObject{
	// CaseId(用例编号)
	private String caseId;
	// ApiId(接口编号)
	private String apiId;
	// IsExcute(是否执行)
	private String isExcute;
	// RequestData(接口请求参数)
	private String requestData;
	// ExpectedResponseData(期望响应结果)
	private String expectedResponseData;
	// ActualResponseData(实际响应结果)
	private String actualResponseData;
	// 接口基本信息对象
	private ApiInfo apiInfo;

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

	public void setExpectedResponseData(String expectedResponseData) {
		this.expectedResponseData = expectedResponseData;
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

	@Override
	public String toString() {
		return "ApiCaseDetail [caseId=" + caseId + ", apiId=" + apiId + ", isExcute=" + isExcute + ", requestData="
				+ requestData + ", expectedResponseData=" + expectedResponseData + ", actualResponseData="
				+ actualResponseData + ", apiInfo=" + apiInfo + ", getRowNo=" + getRowNo() + "]";
	}

	
}
