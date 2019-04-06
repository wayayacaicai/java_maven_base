package api.advanced.reflectUpdate;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 当excel结构变化：增加、删除列、顺序调整 2：大量的冗余，不好维护--》怎么解决冗余，重复的
 * 数据库冗余：一个信息系统所有数据都放到一个表中--》通过多个表、设计关联字段解决的
 * 
 * @Desc:结合json数据和反射进行请求(执行测试用例)
 * @author:zpp
 * @time:2019年4月1日 下午9:08:57
 */
public class AllTestCase {
	@DataProvider
	public Object[][] getDatas() {
		return DataProviderUtils.getDatas();
	}

	@Test(dataProvider = "getDatas")
	// 每一个apiCaseDetail就是一条测试用例，测试用例有一条对应得接口信息
	// 接口信息是接口测试用例的属性
	public static void get(ApiCaseDetail apiCaseDetail) {
		// 通过注入进来的apiId，到第1个sheet去找
		// 数据是excel中，然后我们读取出来时保存到了二维数组对象--》遍历这个数组中的每个元素，一一匹配
		// 给我一个apiId，我就能知道对应的api的基本信息

		// json字符串转换为对象
		// Map<String, String> params = (Map<String, String>)
		// JSONObject.parse(apiCaseDetail.getRequestData());
		// String entityStr =
		// HttpUtilsReflect.get(apiCaseDetail.getApiInfo().getUrl(), params);

		String entityStr = HttpUtilsReflect.get(apiCaseDetail);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}

	@Test(dataProvider = "getDatas")
	public static void post(ApiCaseDetail apiCaseDetail) {
		// Map<String, String> params = (Map<String, String>)
		// JSONObject.parse(apiCaseDetail.getRequestData());
		// String entityStr =
		// HttpUtilsReflect.post(apiCaseDetail.getApiInfo().getUrl(), params);

		String entityStr = HttpUtilsReflect.post(apiCaseDetail);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}
}
