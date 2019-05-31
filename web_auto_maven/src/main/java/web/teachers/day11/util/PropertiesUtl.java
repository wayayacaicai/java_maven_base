package web.teachers.day11.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtl {
	
	/**
	 * url对应属性对象
	 */
	private static Properties urlProperties;
	
	static{
		urlProperties = new Properties();
		try {
			urlProperties.load(PropertiesUtl.class.getResourceAsStream("/config/url.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getUrlByKey(String key){
		return urlProperties.getProperty(key);
	}
	
	public static void main(String[] args) throws IOException {

//		Properties properties = new Properties();
//		properties.load(PropertiesUtl.class.getResourceAsStream("/config/url.properties"));
//		String baseUrl = properties.getProperty("base_url");
//		String login_url = properties.getProperty("login_url");
//		System.out.println(baseUrl + login_url);
		
//		System.out.println(urlProperties.get("base_url"));
		System.out.println(getUrlByKey("登录页面"));
		
	}

}
