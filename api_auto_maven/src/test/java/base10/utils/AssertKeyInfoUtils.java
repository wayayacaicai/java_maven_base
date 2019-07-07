package base10.utils;

import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import base10.pojo.ApiCaseDetail;
import base10.pojo.AssertKeyInfoObject;

/**
 * @Desc:断言响应数据的工具类 
 * @author:zpp 
 * @time:2019年7月5日 下午11:08:52
 */
public class AssertKeyInfoUtils {

	/**
	 * @Desc 断言响应体数据
	 * @param actualResult
	 * @param apiCaseDetail
	 */
	public static void assertResponseEntity(String actualResult, ApiCaseDetail apiCaseDetail) {
		List<AssertKeyInfoObject> assertKeyInfoObjects = JSONObject.parseArray(apiCaseDetail.getAssertKeyInfo(), AssertKeyInfoObject.class);
		for (AssertKeyInfoObject assertKeyInfoObject : assertKeyInfoObjects) {
			//提取的规则
			String jsonPath = assertKeyInfoObject.getJsonPath();
			//期望结果
			String expectedValue = assertKeyInfoObject.getExpectedValue();
			//一次性解析，这样后续不用重复去解析json
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(actualResult);
			//提取出来的值
			Object extractObject = JsonPath.read(document, jsonPath);
			//断言
			Assert.assertEquals(extractObject.toString(), expectedValue);
		}
		
	}
	
}
