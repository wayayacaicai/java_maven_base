package api.advanced.reflect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

/**
 * 当excel结构变化：增加、删除列、顺序调整 2：大量的冗余，不好维护--》怎么解决冗余，重复的
 * 数据库冗余：一个信息系统所有数据都放到一个表中--》通过多个表、设计关联字段解决的
 * 
 * @Desc:结合json数据和反射进行请求
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class AllTestCase {
	@DataProvider
	public Object[][] getDatas() {
		// 接口基本信息
		List<Object> datasInfoList = ExcelUtilsReflect.readExcel("/excel/reflect/excelReflect.xlsx", 0, ApiInfo.class);
		// 接口详细信息
		List<Object> datasDetailList = ExcelUtilsReflect.readExcel("/excel/reflect/excelReflect.xlsx", 1,
				ApiCaseDetail.class);
		// ApiId的容器转换
		Map<String, ApiInfo> apiInfoMap = new HashMap<String, ApiInfo>();
		for (Object object : datasInfoList) {
			ApiInfo apiInfo = (ApiInfo) object;
			apiInfoMap.put(apiInfo.getApiId(), apiInfo);
		}

		// 二维数据放一维数组，每个一维数组放一个ApiCaseDetail得对象
		Object[][] objects = new Object[datasDetailList.size()][];
		for (int i = 0; i < datasDetailList.size(); i++) {
			ApiCaseDetail apiCaseDetail = (ApiCaseDetail) datasDetailList.get(i); // 遍历得到一个对象

			// 告诉apiId进行外键匹配,每一次匹配apiId，都会全部重新匹配一边，性能太差
			// for (Object object : datasInfoList) {
			// ApiInfo apiInfo = (ApiInfo)object;
			// if(apiInfo.getApiId().equals(apiCaseDetail.getApiId())){
			// apiCaseDetail.setApiInfo(apiInfo); //设置对应的apiInfo对象
			// }
			// }
			apiCaseDetail.setApiInfo(apiInfoMap.get(apiCaseDetail.getApiId()));
			;

			Object[] dataArray = new Object[] { apiCaseDetail }; // 把对象放入一个一维数组
			objects[i] = dataArray; // 把一维数组放入二维数组
		}
		return objects;
	}

	@Test(dataProvider = "getDatas")
	// 每一个apiCaseDetail就是一条测试用例，测试用例有一条对应得接口信息
	// 接口信息是接口测试用例的属性
	public static void get(ApiCaseDetail apiCaseDetail) {
		// 通过注入进来的apiId，到第1个sheet去找
		// 数据是excel中，然后我们读取出来时保存到了二维数组对象--》遍历这个数组中的每个元素，一一匹配
		// 给我一个apiId，我就能知道对应的api的基本信息

		// json字符串转换为对象
		Map<String, String> params = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtilsReflect.get(apiCaseDetail.getApiInfo().getUrl(), params);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}

	@Test(dataProvider = "getDatas")
	public static void post(ApiCaseDetail apiCaseDetail) {
		Map<String, String> params = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtilsReflect.post(apiCaseDetail.getApiInfo().getUrl(), params);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}
}
