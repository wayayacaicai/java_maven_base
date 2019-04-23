package api.advanced.data_check.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Desc:参数化，正则提取 
 * @author:zpp 
 * @time:2019年4月22日 下午9:45:28
 */
public class ParameterUtils {
	//创建参数化容器，全局数据池
	private static Map<String, String> globalDataMap = new HashMap<String,String>();
	
	/**
	 * @Desc 通过参数名，返回数据池中间对应的值
	 * @param parametersName
	 * @return
	 */
	public static String getGlobalData(String parametersName){
		return globalDataMap.get(parametersName);
	}
	
	/**
	 * @Desc  添加数据到全局数据池
	 * @param parametersName
	 * @param parametersValue
	 */
	public static void addGlobalData(String parametersName,String parametersValue){
		globalDataMap.put(parametersName, parametersValue);
	}
	
	/**
	 * @Desc 正则替换参数的方法
	 * @param str
	 * @return
	 */
	public static String getReplacedStr(String str){
		String regex = "\\$\\{(.*?)\\}";
		//模式对象
		Pattern pattern = Pattern.compile(regex);
		//匹配对象
		Matcher matcher = pattern.matcher(str);
		
		while(matcher.find()){
			String totalStr = matcher.group(0); //完整的符合规则的字符串--${mobilephone}
			String parameterName = matcher.group(1); //对应的参数名--mobilephone
			String parameterValue = globalDataMap.get(parameterName);
			str = str.replace(totalStr, parameterValue);
		}
		return str;
	}
	
	
	
	
	
	public static void main(String[] args) {
		Map<String, String> parametersMap = new HashMap<String,String>();
		parametersMap.put("mobilephone", "13666666666");
		parametersMap.put("pwd", "123456");
		parametersMap.put("regname", "caicai");
		
		String str = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"${pwd}\",\"regname\":\"${regname}\"}";
		
		String regex = "\\$\\{(.*?)\\}";
	
		//模式对象
		Pattern pattern = Pattern.compile(regex);
		//匹配对象
		Matcher matcher = pattern.matcher(str);

		while(matcher.find()){
			String totalStr = matcher.group(0); //完整的符合规则的字符串--${mobilephone}
//			System.out.println(totalStr);
			String parameterName = matcher.group(1); //对应的参数名--mobilephone
//			System.out.println(parameterName);
			String parameterValue = parametersMap.get(parameterName);
//			System.out.println(parameterValue);
			str = str.replace(totalStr, parameterValue);
		}
		System.out.println(str);
	}
}
