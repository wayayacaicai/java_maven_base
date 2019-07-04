package base10.pojo;

/**
 * @Desc:接口基本信息
 * @author:zpp 
 * @time:2019年5月27日 下午4:12:40
 */
public class ApiInfo extends BaseExcelSheet{
	//ApiId(接口编号)	
	private String apiId;
	//ApiName(接口名称)
	private String apiName;
	//Type(接口提交方式)	
	private String type;
	//Url(接口地址)
	private String url;
	
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ApiInfo [apiId=" + apiId + ", apiName=" + apiName + ", type=" + type + ", url=" + url + ", getRowNo()="
				+ getRowNo() + "]";
	}
	
	

	
}
