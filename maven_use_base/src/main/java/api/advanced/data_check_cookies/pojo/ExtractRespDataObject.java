package api.advanced.data_check_cookies.pojo;

/**
 * @Desc:TODO 
 * @author:zpp 
 * @time:2019年4月29日 下午5:00:22
 */
public class ExtractRespDataObject {
	//[{"parameterName":"memberId","jsonPath":"$.data.id"},{"parameterName":"leaveAmount","jsonPath":"$.data.leaveamount"}]
	private String parameterName;
	private String jsonPath;
	public String getParameterName() {
		return parameterName;
	}
	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}
	public String getJsonPath() {
		return jsonPath;
	}
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}
	@Override
	public String toString() {
		return "ExtractRespDataObject [parameterName=" + parameterName + ", jsonPath=" + jsonPath + "]";
	}
}
