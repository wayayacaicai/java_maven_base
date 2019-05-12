package api_auto_test.pojo;

public class AssertKeyInfoObject {
	//[{"jsonPath":"$.msg","expectedValue":"密码不能为空"}]
	private String jsonPath;
	private String expectedValue;
	public String getJsonPath() {
		return jsonPath;
	}
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}
	public String getExpectedValue() {
		return expectedValue;
	}
	public void setExpectedValue(String expectedValue) {
		this.expectedValue = expectedValue;
	}
	@Override
	public String toString() {
		return "AssertKeyInfoObject [jsonPath=" + jsonPath + ", expectedValue=" + expectedValue + "]";
	}
	
	
}
