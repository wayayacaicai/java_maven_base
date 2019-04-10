package api.teachers.day04.section01;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 1：当excel结构变化：增加、删除列、顺序调整
 * 2：大量的冗余，不好维护--》怎么解决冗余，重复的
 * 数据库冗余：一个信息系统所有数据都放到一个表中--》通过多个表、设计关联字段解决的
 * 
 * 	Member
 * 		id regName
 *  Invest
 *  	id,memberId
 * @author happy
 * @date 2019年4月2日
 * @desc 
 * @email
 */
public class All_TestCase_2 {
	@DataProvider
	public Object[][] getDatas() {
		//读取所有的测试用例详细信息
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);
		//读取所有的接口基本信息
		List<Object> apiInfoList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 0, ApiInfo.class);
		
		//key为apiId，值为apiinfo类型的map
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for(Object obj : apiInfoList){
			ApiInfo apiInfo = (ApiInfo) obj;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		
		Object[][] datas = new Object[apiCaseDetailList.size()][];
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail) apiCaseDetailList.get(i);
			//告诉我apiId，我就能去apiInfoList容器中间去匹配
			//给我一个apiId--》找到对应的apiInfo --》map
			//key--> apiId
			//value--> ApiInfo对象
//			for(Object obj : apiInfoList){
//				ApiInfo apiInfo = (ApiInfo) obj;
//				if (apiCaseDetail.getApiId().equals(apiInfo.getApiId())) {
//					//设置对应的apiinfo对象
//					apiCaseDetail.setApiInfo(apiInfo);
//				}
//			}
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			//把这个一维数组设置到对应索引的二维数组中去
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

	@Test(dataProvider = "getDatas")
	//执行测试需要准备什么数据--》测试用例相关信息--》对象
//	apiCaseDetail对应的是一个测试用例，测试用例都会对应一个接口信息
	//接口信息是测试用例的属性 --》
	public void test_case_1(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		System.out.println(apiCaseDetail);
//		String baseUrl = "";
//		Map<String, String> parameters = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
//		String entityStr = HttpUtils.get(baseUrl, parameters);
//		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}

}
