package web.teachers.day09.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import web.teachers.day09.pojo.Locator;



public class PagesUtils {

	/**
	 * 保存所有页面元素定位信息的容器
	 */
	private static Map<String, Map<String, Locator>> allLocatorsMap;

	//只加载一次
	static {
		allLocatorsMap = loadAllLocators();
	}

	/**
	 * 根据页面名称获得该页面所有的定位信息
	 * @param pageName
	 * @return
	 */
	public static Map<String, Locator> getAllLocatorsByPageName(String pageName) {
		return allLocatorsMap.get(pageName);
	}

	/**
	 * 根据页面名称和定位器的名称返回定位器对象
	 * @param pageName
	 * @param locatorName
	 * @return
	 */
	public static Locator getLocatorByPageNameAndLoacatorName(String pageName, String locatorName) {
		return allLocatorsMap.get(pageName).get(locatorName);
	}

	/**
	 * 加载所有的定位信息对象
	 * @return
	 */
	private static Map<String, Map<String, Locator>> loadAllLocators() {
		SAXReader saxReader = new SAXReader();
		Document document = null;
		try {
			document = saxReader.read(PagesUtils.class.getResourceAsStream("/pages.xml"));
		} catch (DocumentException e) {
			//			e.printStackTrace();
		}
		Element rootElement = document.getRootElement();
		List<Element> pageSubElementList = rootElement.elements("page");
		//准备一个容器，存放所有页面的定位器
		Map<String, Map<String, Locator>> allLocatorsMap = new HashMap<>();

		//String--》页面名称--》页面所有的对象？？
		//遍历每个页面
		for (Element pageSubElement : pageSubElementList) {
			//拿到页面的名称属性对应的值
			String pageName = pageSubElement.attributeValue("pageName");
			//获得页面的所有的Locator
			List<Element> locatorSubElementList = pageSubElement.elements("locator");
			//创建一个容器，保存当前页面的所有元素信息
			Map<String, Locator> currentPageMap = new HashMap<String, Locator>();
			//遍历所有的locator
			for (Element locatorSubElement : locatorSubElementList) {
				//获得每个locator的属性
				String name = locatorSubElement.attributeValue("name");
				String by = locatorSubElement.attributeValue("by");
				String value = locatorSubElement.attributeValue("value");
				String delay = locatorSubElement.attributeValue("delay");
				String clickable = locatorSubElement.attributeValue("clickable");
				String displayed = locatorSubElement.attributeValue("displayed");

				//封装到Locator对象中
				Locator locator = new Locator(name, by, value, delay, clickable, displayed);
				//				System.out.println(locator);
				currentPageMap.put(name, locator);
			}
			// 把每个页面元素集合对象存放到所有页面元素集合对象中去
			allLocatorsMap.put(pageName, currentPageMap);
		}
		return allLocatorsMap;
	}

	public static void main(String[] args) throws Exception {
		//		Map<String,Map<String,Locator>> allLocators = loadAllLocators();
		//获取登录登录的登录按钮
		//		Locator locator = allLocators.get("登录页面").get("登录按钮");
		//		Locator locator = allLocatorsMap.get("注册页面").get("手机号码输入框");
		//		System.out.println(locator);

		Locator locator = getLocatorByPageNameAndLoacatorName("登录页面", "登录按钮");
		System.out.println(locator);
	}

}
