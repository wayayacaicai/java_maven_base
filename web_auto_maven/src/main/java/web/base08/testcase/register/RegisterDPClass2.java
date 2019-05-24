package web.base08.testcase.register;

import java.util.List;

import org.testng.annotations.DataProvider;

import web.base08.util.ExcelUtils;



public class RegisterDPClass2 {
	/**
	 * 注册的反向测试用例数据
	 * @return
	 */
	@DataProvider(name="failure_data")
	public static Object[][] register_failure_data(){
		List<RegisterInfo> registerInfoList = (List<RegisterInfo>) ExcelUtils.readExcel("/testcase/register/register.xlsx",
				0, RegisterInfo.class);
		
		//创建一个二维数组
		Object[][] datas = new Object[registerInfoList.size()][];
		for(int i=0;i<registerInfoList.size();i++){
			RegisterInfo registerInfo = registerInfoList.get(i);
			Object[] itemData = { registerInfo };
			datas[i] = itemData;
		}
		
		return datas;
		/*return new Object[][]{
			{"","","","","用户名不能为空"},
			{"lemon","","","","非法的手机号"},
			{"柠檬班","","","","非法的手机号"},
			{"13888888888","","","LM19","密码不能为空"},
			{"13888888888","12345","","LM19","密码长度至少为6位"},
			{"13888888888","123456","","LM19","重复密码不能为空"},
			{"13888888888","123456","1234567","LM19","密码不一致"},
			{"13888888888","123456","123456","","验证码不能为空"},
		};*/
	}
	
	/**
	 * 注册的正向测试用例数据
	 * @return
	 */
	@DataProvider(name="success_data")
	public static Object[][] register_success_data(){
		
		List<RegisterInfo> registerInfoList = (List<RegisterInfo>) ExcelUtils.readExcel("/testcase/register/register.xlsx",
				1, RegisterInfo.class);
		
		//创建一个二维数组
		Object[][] datas = new Object[registerInfoList.size()][];
		for(int i=0;i<registerInfoList.size();i++){
			RegisterInfo registerInfo = registerInfoList.get(i);
			Object[] itemData = { registerInfo };
			datas[i] = itemData;
		}
		
		return datas;
		
		/*
		  {
			{registerInfo},
			{registerInfo}
		 }
		 * return new Object[][]{
			{"13567891111","123456","123456","LM19"},
			{"13567892222","abcedf","abcedf","LM19"},
		};*/
		
		
	}
	
	public static void main(String[] args) {
		List<RegisterInfo> registerInfoList = (List<RegisterInfo>) ExcelUtils.readExcel("/testcase/login/register.xlsx",
				1, RegisterInfo.class);
		
		//创建一个二维数组
		Object[][] datas = new Object[registerInfoList.size()][];
		for(int i=0;i<registerInfoList.size();i++){
			RegisterInfo registerInfo = registerInfoList.get(i);
			Object[] itemData = { registerInfo };
			datas[i] = itemData;
		}
	}
}
