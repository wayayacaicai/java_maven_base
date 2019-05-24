package web.base08.testcase.register;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import web.base08.util.DataProviderUtil;



public class RegisterDPClass3 {
	/**
	 * 注册的反向测试用例数据
	 * @return
	 */
	@DataProvider(name="failure_data")
	public static Object[][] register_failure_data(Method method){
		return DataProviderUtil.getData("/testcase/register/register.xlsx", 0, RegisterInfo.class);
	}
	/**
	 * 注册的正向测试用例数据
	 * @return
	 */
	@DataProvider(name="success_data")
	public static Object[][] register_success_data(Method method){
		return DataProviderUtil.getData("/testcase/register/register.xlsx", 1, RegisterInfo.class);
	}
	
	public static void main(String[] args) {
		
	}
}
