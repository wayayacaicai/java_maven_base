package api.retry;

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
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/retry.xlsx", 1, ApiCaseDetail.class);
		
		Object[][] allDatas = new Object[apiCaseDetailList.size()][];
		for(int i=0;i<apiCaseDetailList.size();i++){
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail)apiCaseDetailList.get(i);
			for(int j=0;j<apiInfoList.size();j++){
				ApiInfo apiInfo = (ApiInfo)apiInfoList.get(j);
				if(apiInfo.getApiId().equals(apiCaseDetail.getApiId())){
					apiCaseDetail.setApiInfo(apiInfo);
				}
			}
			Object[] object = new Object[]{apiCaseDetail}; //一维数组
			allDatas[i] = object; //二维数组
		}
		return allDatas;
	}

	@Test(dataProvider = "getDatas")
	public void get(ApiCaseDetail apiCaseDetail) {
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtils.get(apiCaseDetail.getApiInfo().getApiUrl(), paramsMap);
		System.out.println(entityStr);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedResponseData()));
	}

	@Test(dataProvider = "getDatas")
	public void post(ApiCaseDetail apiCaseDetail) {
		Map<String, String> paramsMap = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtils.post(apiCaseDetail.getApiInfo().getApiUrl(), paramsMap);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedResponseData()));
	}
}
