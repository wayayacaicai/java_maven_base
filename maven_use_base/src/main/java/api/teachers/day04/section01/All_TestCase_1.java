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
public class All_TestCase_1 {
	@DataProvider
	public Object[][] getDatas(){
//		Object[][] datas = ExcelUtils.readExcel("/api_test_case_01.xlsx",1);
		
		//Object[][]二维数组，里面的元素是啥--》一维数组
		//一维数组放什么数据--》ApiCaseDetail对象
		//一维数组中放几个ApiCaseDetail对象-->1个
		/**
		 {
		 	{apiCaseDetail},
		 	{apiCaseDetail},
		 	{apiCaseDetail},
		 	{apiCaseDetail}
		 }
		 
		 * 
		 */
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/api_test_case_01.xlsx",1, ApiCaseDetail.class);
		
		Object[][] datas = new Object[apiCaseDetailList.size()][];
		for(int i=0;i<apiCaseDetailList.size();i++){
			Object object = apiCaseDetailList.get(i);
//			//把这个一维数组设置到对应索引的二维数组中去
			Object[] itemArray = {object};
			datas[i] = itemArray;
		}
		return datas;
	}
	//对象--》HashMap--》json对象
	@Test(dataProvider="getDatas")
	//CaseId(用例编号)	ApiId(接口编号)	IsExcute(是否执行)	RequestData(接口请求参数)	ExpectedReponseData(期望响应数据)
	//执行测试需要准备什么数据--》测试用例相关信息--》对象
	public void test_case_1(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		//通过注入进来的apiId，到第1个sheet去找
		//数据是excel中，然后我们读取出来时保存到了二维数组对象--》遍历这个数组中的每个元素，一一匹配
		//给我一个apiId，我就能知道对应的api的基本信息
		System.out.println("-----------------------------");
		System.out.println(apiCaseDetail);
		
		String baseUrl = "";
		Map<String, String> parameters = (Map<String, String>) JSONObject.parse(apiCaseDetail.getRequestData());
		String entityStr = HttpUtils.get(baseUrl, parameters);
		Assert.assertTrue(entityStr.contains(apiCaseDetail.getExpectedReponseData()));
	}
	
	
	
	public static void main(String[] args) {
		List<Object> apiCaseDetailList = ExcelUtils.readExcel("/api_test_case_01.xlsx", 1, ApiCaseDetail.class);

		Object[][] datas = new Object[apiCaseDetailList.size()][];
		
		for (int i = 0; i < apiCaseDetailList.size(); i++) {
			Object object = apiCaseDetailList.get(i);
			
//			Object[] itemArray = new Object[1];//size  声明一个数组
//			itemArray[0] = apiCaseDetailList.get(i);//数组只有一个元素，设置到索引为0的
			
//			//把这个一维数组设置到对应索引的二维数组中去
			Object[] itemArray = {object};
			datas[i] = itemArray;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
