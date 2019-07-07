package base10.pojo;

/**
 * @Desc:断言响应数据基本类 
 * @author:zpp 
 * @time:2019年7月5日 下午11:02:24
 */
public class AssertKeyInfoObject {
	//提取响应体数据的jsonPath
	private String jsonPath;
	//期望的响应结果
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
