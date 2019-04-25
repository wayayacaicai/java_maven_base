package api.teachers.day09.section01.pojo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 断言信息的对象
 * @author happy
 * @date 2019年4月24日
 * @desc 
 * @email
 */
public class AssertKeyInfoObject {
	
	//[{"jsonPath":"$.msg","expectedValue":"注册成功"},{"jsonPath":"$.code","expectedValue":"10001"}]

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
	
	public static void main(String[] args) {
		String json = "[{\"jsonPath\":\"$.msg\",\"expectedValue\":\"注册成功\"},{\"jsonPath\":\"$.code\",\"expectedValue\":\"10001\"}]";
		List<AssertKeyInfoObject> assertKeyInfoObjects = JSONObject.parseArray(json, AssertKeyInfoObject.class);
		for (AssertKeyInfoObject assertKeyInfoObject : assertKeyInfoObjects) {
			System.out.println(assertKeyInfoObject);
		}
	}

}
