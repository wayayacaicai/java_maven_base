package api.retry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class Request {
	@DataProvider
	public Object[][] getDatas() {
		List<Object> apiInfoList = ExcelUtils.readExcel("/retry.xlsx", 0, ApiInfo.class);
		//容器转换
		HashMap<String, ApiInfo> apiInfoMap = new HashMap<>();
		for (Object object : apiInfoList) {
			ApiInfo apiInfo = (ApiInfo)object;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}
		
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/retry.xlsx", 1, ApiCaseDetail.class);
		
		Object[][] allDatas = new Object[apiCaseDetailList.size()][];
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail)apiCaseDetailList.get(i);
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));  //得到apiInfo对象
			
			Object[] object = new Object[]{apiCaseDetail}; //一维数组
			allDatas[i] = object; //二维数组
		}
		return allDatas;
	}

	@Test(dataProvider = "getDatas")
	public void get(ApiCaseDetail apiCaseDetail) {
		String entityStr = HttpUtils.get(apiCaseDetail);
		System.out.println(entityStr);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedResponseData()));
	}

	@Test(dataProvider = "getDatas")
	public void post(ApiCaseDetail apiCaseDetail) {
		String entityStr = HttpUtils.post(apiCaseDetail);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedResponseData()));
	}
}
