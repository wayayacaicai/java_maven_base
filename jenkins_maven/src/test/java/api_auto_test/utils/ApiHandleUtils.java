package api_auto_test.utils;

import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import api_auto_test.pojo.ApiCaseDetail;
import api_auto_test.pojo.AssertKeyInfoObject;
import api_auto_test.pojo.ExtractRespDataObject;

/**
 * @Desc:接口信息处理工具类
 * @author:zpp 
 * @time:2019年4月30日 上午11:00:11
 */
public class ApiHandleUtils {

	/**
	 * @Desc 断言响应数据的方法
	 * @param responseData
	 * @param apiCaseDetail
	 */
	public static void assertResult(String responseData, ApiCaseDetail apiCaseDetail) {
		//excel中设置的提取规则
		String assertKeyInfo = apiCaseDetail.getAssertKeyInfo();
		//转换为java对象
		List<AssertKeyInfoObject> assertKeyInfoObjects = JSONObject.parseArray(assertKeyInfo,AssertKeyInfoObject.class);
		for (AssertKeyInfoObject assertKeyInfoObject : assertKeyInfoObjects) {
			String jsonPath = assertKeyInfoObject.getJsonPath();
			String expectedValue = assertKeyInfoObject.getExpectedValue();
			
			//JsonPath解析json数据的方式
			//一次性解析，这样后续不用重复去解析json
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseData);
			//提取出来的值
			Object actualValue = JsonPath.read(document, jsonPath);
			//断言响应结果
			Assert.assertEquals(actualValue.toString(), expectedValue);
		}
	}

	public static void extractResponseData(String responseData, ApiCaseDetail apiCaseDetail) {
		//提取响应数据的规则---字符串形式
		String extractRespData = apiCaseDetail.getExtractRespData();
		if(StringUtils.isEmpty(extractRespData)){
			return;
		}
		
		List<ExtractRespDataObject> extractRespDataObjects = JSONObject.parseArray(extractRespData, ExtractRespDataObject.class);
		for (ExtractRespDataObject extractRespDataObject : extractRespDataObjects) {
			//jsonPath--》提取的规则
			String jsonPath = extractRespDataObject.getJsonPath();
			//参数名--》设值回全局数据池
			String parameterName = extractRespDataObject.getParameterName();
			//一次性解析，这样后续不用重复去解析json
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseData);
			//提取出来的值
			Object extractObject = JsonPath.read(document, jsonPath);
			//设值回全局数据池
			ParameterUtils.addGlobalData(parameterName, extractObject.toString());
		}
	}
}
