package web.teachers.day10.testcase.regsiter;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import web.teachers.day10.util.DataProviderUtil;



public class RegisterDPClass {
	/**
	 * 注册的反向测试用例数据
	 * @return
	 */
	@DataProvider(name="failure_data")
	public static Object[][] register_failure_data(Method method){
		return DataProviderUtil.getData(method.getName(), RegisterInfo.class);
	}
	/**
	 * 注册的正向测试用例数据
	 * @return
	 */
	@DataProvider(name="success_data")
	public static Object[][] register_success_data(Method method){
		return DataProviderUtil.getData(method.getName(), RegisterInfo.class);
	}
}
