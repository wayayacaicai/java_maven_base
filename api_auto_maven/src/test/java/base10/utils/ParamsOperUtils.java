package base10.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONObject;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;

import base10.pojo.ApiCaseDetail;
import base10.pojo.ExtractRespDataObject;

/**
 * @Desc:参数替换、数据依赖工具类 
 * @author:zpp 
 * @time:2019年6月9日 下午6:34:16
 */
public class ParamsOperUtils {
	//全局数据池
	private static Map<String, String> globalDataMap = new HashMap<>();
	//通过参数名得到数据池中数据
	public static String getGlobalValue(String key){
		return globalDataMap.get(key);
	}
	//添加数据到全局数据池
	public static void addGlobalData(String key,String value){
		globalDataMap.put(key, value);
	}
	
	/**
	 * @Desc 正则匹配并替换为数据池中间的参数值
	 * @param str
	 * @return
	 */
	public static String getReplaceJsonData(String str){
		String regex = "\\$\\{(.*?)\\}";
		Pattern pattern = Pattern.compile(regex); //编译正则表达式，得到模式对象
		Matcher matcher = pattern.matcher(str); //匹配字符串（检索）,得到匹配对象
		while(matcher.find()){
			String totalStr = matcher.group(0);//完整的符合规则的字符串 -->${mobilephone}
			String key = matcher.group(1);//对应的参数名-->mobilephone -->到容器中找对应的值
			String value = globalDataMap.get(key); 
			str = str.replace(totalStr, value);
		}
		return str;
	}
	
	/**
	 * @Desc 抽取数据依赖数据的方法
	 * @param actualResult
	 * @param apiCaseDetail
	 */
	public static void extractRespData(String actualResult, ApiCaseDetail apiCaseDetail) {
		//提取的响应数据规则的字符串形式
		String extractRespData = apiCaseDetail.getExtractRespData();
		if(StringUtils.isEmpty(extractRespData)){
			return;
		}
		List<ExtractRespDataObject> extractRespDataObjects = JSONObject.parseArray(extractRespData, ExtractRespDataObject.class);
		for (ExtractRespDataObject extractRespDataObject : extractRespDataObjects) {
			//参数名--》设值回全局数据池
			String parameterName  =extractRespDataObject.getParameterName();
			//jsonPath--》提取的规则
			String jsonPath = extractRespDataObject.getJsonPath();
			//一次性解析，这样后续不用重复去解析json
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(actualResult);
			//提取出来的值
			Object extractObject = JsonPath.read(document, jsonPath);
			//设值回全局数据池
			ParamsOperUtils.addGlobalData(parameterName, extractObject.toString());
		}
		
	}
	
	
}
