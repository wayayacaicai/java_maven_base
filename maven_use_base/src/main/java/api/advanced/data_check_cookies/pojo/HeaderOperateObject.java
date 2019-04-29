package api.advanced.data_check_cookies.pojo;

public class HeaderOperateObject {
	//[{"headerName":"Set-Cookie","paramName":"cookie"}]
	private String headerName;
	private String paramName;
	public String getHeaderName() {
		return headerName;
	}
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}
	public String getParamName() {
		return paramName;
	}
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
	@Override
	public String toString() {
		return "HeaderOperateObject [headerName=" + headerName + ", paramName=" + paramName + "]";
	}
}
