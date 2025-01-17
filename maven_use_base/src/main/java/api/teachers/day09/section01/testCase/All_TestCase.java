package api.teachers.day09.section01.testCase;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.teachers.day09.section01.pojo.ApiCaseDetail;
import api.teachers.day09.section01.pojo.CellData;
import api.teachers.day09.section01.utils.ApiUtils;
import api.teachers.day09.section01.utils.AssertUtils;
import api.teachers.day09.section01.utils.DataCheckUtils;
import api.teachers.day09.section01.utils.ExcelUtils;
import api.teachers.day09.section01.utils.HttpUtils;
import api.teachers.day09.section01.utils.ParameterUtils;



/**
 * 执行测试用例
 * @author happy
 * @date 2019年4月2日
 * @desc 
 * @email
 */
public class All_TestCase {
	
	@BeforeSuite
	public void beforeSuite(){
		//查询出最大的手机号，找一个数据库中间没有的手机号
		//最大的手机号：13888888888 
//		13888888888 + 1-->存起来--》替换回测试用例的数据
		//设置参数
		ParameterUtils.addGlobalData("mobilephone", "13999888819");
		ParameterUtils.addGlobalData("pwd", "123456");
		ParameterUtils.addGlobalData("nickname", "jack");
	}
	
	@DataProvider
	public Object[][] getDatas() {
		return ApiUtils.getCaseDetailList();
	}

	@Test(dataProvider = "getDatas")
	public void execute_test_case(ApiCaseDetail apiCaseDetail) throws ClientProtocolException, IOException {
		//1：前置验证
		DataCheckUtils.beforeCheck(apiCaseDetail);
		//发包得到的响应提数据
		String entityStr = HttpUtils.request(apiCaseDetail);
		
		//如果这个字符串不为null并且不为纯空格，不会空字符串
//		if (apiCaseDetail.getExtractRespData()!=null && apiCaseDetail.getExtractRespData().length()>0) {
//			
//		}
		ApiUtils.extractRespData(entityStr,apiCaseDetail);
		
		//2:后置验证
		DataCheckUtils.afterCheck(apiCaseDetail);
		
		System.out.println(entityStr);
		//收集要写的数据：把接口的响应写回到对应行的第6列
		CellData cellData = new CellData(apiCaseDetail.getRowNo(), 6, entityStr);
		//添加到容器中
		ApiUtils.addCellData(cellData);
		
		//断言响应结果
		AssertUtils.assertResponseEntity(entityStr,apiCaseDetail);
	}
	
	@AfterSuite
	public void afterSuite(){
		//批量回写数据
		ExcelUtils.batchWriteExcel("/api_test_case_01.xlsx", "target/classes/api_test_case_01_all.xlsx");
	}
	
	public static void main(String[] args) {
		//参数案例
		ParameterUtils.addGlobalData("mobilephone", "13888888888");
		ParameterUtils.addGlobalData("pwd", "123456");
		ParameterUtils.addGlobalData("regName", "happy");
		
		String str = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"${pwd}\",\"regname\":\"${regName}\"}";
		String resultStr = ParameterUtils.getReplacedStr(str);
		System.out.println(resultStr);
	}

}
