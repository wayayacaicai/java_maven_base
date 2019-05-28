package base04.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import base04.pojo.ApiCaseDetail;
import base04.pojo.ApiInfo;
import base04.utils.ExcelUtils;
import base04.utils.HttpUtils;

/**
 * @Desc:使用Excel来执行测试用例---使用面向对象---还未完全抽离
 * @author:zpp 
 * @time:2019年5月21日 下午5:01:57
 */
public class ExcelUtilsTest {
	@DataProvider(name="apitest")
	public Object[][] getDatas(){
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/base03/testcase_reflect.xlsx", 1, ApiCaseDetail.class);
		List<Object> apiInfoList = ExcelUtils.readExcel("/base03/testcase_reflect.xlsx", 0, ApiInfo.class);
		Map<String, ApiInfo> apiInfoMap = new HashMap<>(); //容器转换，性能优化
		for (Object object : apiInfoList) {
			ApiInfo apiInfo = (ApiInfo)object;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		Object[][] allDatas = new Object[apiCaseDetailList.size()][]; //创建一个二维数组装所有对象
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail)apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId())); //得到apiInfo对象
			Object[] data = {apiCaseDetail}; //创建一个一维数组装一个对象
			allDatas[i] = data;
		}
		return allDatas;
	}
	
	@Test(dataProvider="apitest")
	public void get(ApiCaseDetail apiCaseDetail){
		Map<String, String> urlParams = (Map<String, String>)JSONObject.parse(apiCaseDetail.getRequestData());
		String actualResult = HttpUtils.get(apiCaseDetail.getApiInfo().getUrl(), urlParams);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
	
	@Test(dataProvider="apitest")
	public void post(ApiCaseDetail apiCaseDetail){
		Map<String, String> urlParams = (Map<String, String>)JSONObject.parse(apiCaseDetail.getRequestData());
		String actualResult = HttpUtils.post(apiCaseDetail.getApiInfo().getUrl(), urlParams);
		Assert.assertTrue(actualResult.contains(apiCaseDetail.getExpectedReponseData()));
	}
}
