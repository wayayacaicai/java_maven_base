package base10.pojo;

/**
 * @Desc:请求响应头部信息基本类 
 * @author:zpp 
 * @time:2019年6月12日 下午11:17:02
 */
public class HeaderObject {
	//请求、响应头部信息键
	private String headerName;
	//全局数据池的键
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
	
	
}
