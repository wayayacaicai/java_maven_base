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
public class All_TestCase {
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
		
		//封装为二维数组中
		Object[][] datas = new Object[apiCaseDetailList.size()][];
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail) apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			Object[] itemArray = { apiCaseDetail };
			datas[i] = itemArray;
		}
		return datas;
	}

	@Test(dataProvider = "getDatas")
	public void test_case_1(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		Map<String, String> parameters = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtils.get(apiCaseDetail.getApiInfo().getUrl(), parameters);
//		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
		System.out.println(entityStr);
	}

}
