package api.teachers.day09.section01.utils;

import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import api.teachers.day09.section01.pojo.ApiCaseDetail;
import api.teachers.day09.section01.pojo.AssertKeyInfoObject;

public class AssertUtils {

	/**
	 * 断言响应体
	 * @param entityStr 实际响应结果
	 * @param apiCaseDetail 对应测试用例详细信息对象
	 */
	public static void assertResponseEntity(String entityStr, ApiCaseDetail apiCaseDetail) {
		List<AssertKeyInfoObject> assertKeyInfoObjects = JSONObject.parseArray(apiCaseDetail.getAssertKeyInfo(),
				AssertKeyInfoObject.class);
		for (AssertKeyInfoObject assertKeyInfoObject : assertKeyInfoObjects) {
			//提取的规则
			String jsonPath = assertKeyInfoObject.getJsonPath();
			//期望结果
			String expectedValue = assertKeyInfoObject.getExpectedValue();
			//一次性解析，这样后续不用重复去解析json
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(entityStr);
			//提取出来的值
			Object extractObject = JsonPath.read(document, jsonPath);
			//断言、
			Assert.assertEquals(extractObject.toString(), expectedValue);
		}
	}

}
