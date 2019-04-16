package api.retry;

public class ApiInfo extends ExcelSheetObject{
	// ApiId(接口编号)
	private String apiId;
	// ApiName(接口名称)
	private String apiName;
	// ApiType(接口类型)
	private String apiType;
	// ApiUrl(接口地址)
	private String apiUrl;

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public String getApiType() {
		return apiType;
	}

	public void setApiType(String apiType) {
		this.apiType = apiType;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	public String toString() {
		return "ApiInfo [apiId=" + apiId + ", apiName=" + apiName + ", apiType=" + apiType + ", apiUrl=" + apiUrl
				+ ", getRowNo=" + getRowNo() + "]";
	}
}
