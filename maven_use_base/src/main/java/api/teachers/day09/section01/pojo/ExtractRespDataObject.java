package api.teachers.day09.section01.pojo;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class ExtractRespDataObject {
	
	//[{"parameterName":"memberId","jsonPath":"$.data.id"},{"parameterName":"leaveAmount","jsonPath":"$.data.leaveamount"}]

	//设值回全局数据池的key
	private String parameterName;
	//提取响应体数据的jsonPath
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
	
	
	public static void main(String[] args) {
		String json = "[{\"parameterName\":\"memberId\",\"jsonPath\":\"$.data.id\"},{\"parameterName\":\"leaveAmount\",\"jsonPath\":\"$.data.leaveamount\"}]";
		List<ExtractRespDataObject> extractRespDataObjects = JSONObject.parseArray(json, ExtractRespDataObject.class);
		for (ExtractRespDataObject extractRespDataObject : extractRespDataObjects) {
			System.out.println(extractRespDataObject);
		}
	}
	
	

}
