package api.teachers.day09.section01.utils;

import static org.testng.Assert.assertEqualsNoOrder;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

public class ParameterUtils {

	//创建参数化的容器
	private static Map<String, String> globalDataMap = new HashMap<String, String>();

	/**
	 * 通过参数名，返回数据池中间对应的值
	 * @param parameterName
	 * @return
	 */
	public static String getGlobalData(String parameterName) {
		return globalDataMap.get(parameterName);
	}

	/**
	 * 添加数据到全局数据池中
	 * @param parameterName
	 * @param parameterValue
	 */
	public static void addGlobalData(String parameterName, String parameterValue) {
		globalDataMap.put(parameterName, parameterValue);
	}

	/**
	 * 正则匹配并替换为数据池中间的参数值
	 * @param args
	 */
	public static String getReplacedStr(String str) {
		//把所有符合参数化规则的字符串提取出来
		//正则表达式，又称规则表达式。（英语：Regular Expression，在代码中常简写为regex、regexp或RE），
		//计算机科学的一个概念。正则表达式通常被用来检索、替换那些符合某个模式(规则)的文本。

		//符合${mobilephone}规则的正则表达式
		//规则：开头是${,中间是任意字符串，皆为是}
		//  \$\{.*\}
		String regex = "\\$\\{(.*?)\\}";
		//编译正则表达式，得到模式对象
		Pattern pattern = Pattern.compile(regex);
		//匹配字符串（检索）,得到匹配对象
		Matcher matcher = pattern.matcher(str);
		//当检索出有下一个匹配的字符串后
		while (matcher.find()) {
			String totalStr = matcher.group(0);//完整的符合规则的字符串 -->${mobilephone}
			String parameterName = matcher.group(1);//对应的参数名-->mobilephone -->到容器中找对应的值
			String parameterValue = globalDataMap.get(parameterName); //-->13777777777
//			System.out.println(totalStr + "-->" + parameterName + "-->" + parameterValue);
			//把原字符串中间的符合规则的字符串，替换为对应参数的值
			//${mobilephone} -->13777777777
			str = str.replace(totalStr, parameterValue);
		}
		return str;
	}

	public static void main(String[] args) {
//		example();
		
		String jsonStr = "{\"status\":1,\"code\":\"10001\",\r\n" + 
				"	 \"data\":{\"id\":3,\"regname\":\"jack\",\r\n" + 
				"	 \"pwd\":\"E10ADC3949BA59ABBE56E057F20F883E\",\r\n" + 
				"	 \"mobilephone\":\"13999888819\",\"leaveamount\":\"10000.00\",\r\n" + 
				"	 \"type\":\"1\",\"regtime\":\"2019-04-20 11:39:05.0\"},\r\n" + 
				"	 \"msg\":\"充值成功\"}";
		Object document = Configuration.defaultConfiguration().jsonProvider().parse(jsonStr);
		Object idStr = JsonPath.read(document, "$.data.regtime");
		System.out.println(idStr);
	}

	private static void example() {
		//准备一个没有注册过的手机号：13777777777 --》保存到一个容器中
		//要把13666666666替换为13777777777
		//设计一个规则，就是告诉系统从容器中去拿没有注册过的手机号
		//{"mobilephone":"${mobilephone}","pwd":"123456","regname":"lemonban"}
		//${mobilephone}:去容器中读取出mobilephone对应的值

		/**
		 * 参数化：
		 * 	1：完成接口的关联
		 *  2：分离出静态的数据，让接口测试用例更加容易维护
		 */
		Map<String, String> parametersMap = new HashMap<String, String>();
		parametersMap.put("mobilephone", "13777777778");
		parametersMap.put("pwd", "123456");
		String str = "{\"mobilephone\":\"${mobilephone}\",\"pwd\":\"${pwd}\",\"regname\":\"lemonban\"}";
		System.out.println("原始字符串：" + str);
		//把${mobilephone}替换为13777777777

		//把所有符合参数化规则的字符串提取出来
		//正则表达式，又称规则表达式。（英语：Regular Expression，在代码中常简写为regex、regexp或RE），
		//计算机科学的一个概念。正则表达式通常被用来检索、替换那些符合某个模式(规则)的文本。

		//符合${mobilephone}规则的正则表达式
		//规则：开头是${,中间是任意字符串，皆为是}
		//  \$\{.*\}
		String regex = "\\$\\{(.*?)\\}";
		//编译正则表达式，得到模式对象
		Pattern pattern = Pattern.compile(regex);
		//匹配字符串（检索）,得到匹配对象
		Matcher matcher = pattern.matcher(str);
		//当检索出有下一个匹配的字符串后
		while (matcher.find()) {
			String totalStr = matcher.group(0);//完整的符合规则的字符串 -->${mobilephone}
			String parameterName = matcher.group(1);//对应的参数名-->mobilephone -->到容器中找对应的值
			String parameterValue = parametersMap.get(parameterName); //-->13777777777
			System.out.println(totalStr + "-->" + parameterName + "-->" + parameterValue);
			//把原字符串中间的符合规则的字符串，替换为对应参数的值
			//${mobilephone} -->13777777777
			str = str.replace(totalStr, parameterValue);
		}

		System.out.println("替换后的字符串：" + str);
	}
	

}
