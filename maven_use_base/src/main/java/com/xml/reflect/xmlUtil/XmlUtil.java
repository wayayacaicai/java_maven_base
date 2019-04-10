package com.xml.reflect.xmlUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Desc:这个demo领悟到了使用object类来实现多态封装方法，是代码更简便,很好用的object的反射方法
 * @author:zpp
 * @time:2019年3月22日 下午10:52:33
 */
public class XmlUtil {

	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(XmlUtil.class.getResourceAsStream("/xml_reflect/xmlUtil/xmlUtil.xml"));

		// 定义反射,定义object类向下好抽出方法，多态
		Class clazz1 = Page.class;
		Class clazz2 = Locator.class;

		// 层级遍历，其中调用反射方法
		HashMap<String, HashMap<String, Locator>> hm = LevelIter(document, clazz1, clazz2);
		// 遍历打印
		printHashMap(hm);
	}

	/**
	 * @Desc 层级遍历
	 * @param document
	 * @param hm2
	 * @param hm1
	 * @param page
	 * @param loc
	 */
	public static HashMap<String, HashMap<String, Locator>> LevelIter(Document document, Class clazz1, Class clazz2) {
		// 返回结果的容器
		HashMap<String, HashMap<String, Locator>> hm1 = new HashMap<>();

		// 第一层
		Element rootElement = document.getRootElement();
		List<Element> elements1 = rootElement.elements();
		for (Element element1 : elements1) {
			// 第一层的对象
			Object page = null;
			try {
				page = (Page) clazz1.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 第一层的属性
			List<Attribute> attributes1 = element1.attributes();
			for (Attribute attribute1 : attributes1) {
				String text1 = attribute1.getName();
				String value1 = attribute1.getValue();

				// 反射
				setReflect(page, text1, value1);

				// 第二层
				List<Element> elements2 = element1.elements();
				for (Element element2 : elements2) {
					// 第二层的容器
					HashMap<String, Locator> hm2 = new HashMap<>();
					// 第二层的对象
					Object loc = null;
					try {
						loc = (Locator) clazz2.newInstance();
					} catch (Exception e) {
						e.printStackTrace();
					}
					// 得到文本信息
					String text2 = element2.getText();
					// 得到第二层属性
					List<Attribute> attributes2 = element2.attributes();
					for (Attribute attribute2 : attributes2) {
						String text22 = attribute2.getName();
						String value22 = attribute2.getValue();

						// 反射
						setReflect(loc, text22, value22);
					}
					// 用第二层容器装第二层的对象
					hm2.put(text2, (Locator) loc);

					// 用第一层容器装第一层对象
					if (text1.equals("name")) {
						hm1.put(value1 + ":" + text2, hm2);
					}
				}
			}
		}
		return hm1;
	}

	/**
	 * @Desc 反射方法
	 * @param loc
	 * @param text
	 * @param value
	 */
	public static void setReflect(Object object, String text, String value) {
		String newMethod = "set" + (text.charAt(0) + "").toUpperCase() + text.substring(1);
		try {
			Field declaredField = object.getClass().getDeclaredField(text);
			Class<?> type = declaredField.getType();
			Method method = object.getClass().getMethod(newMethod, type);
			method.invoke(object, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Desc 打印hashmap
	 * @param hm1
	 */
	public static void printHashMap(HashMap<String, HashMap<String, Locator>> hm1) {
		Set<Entry<String, HashMap<String, Locator>>> entrySet1 = hm1.entrySet();
		for (Entry<String, HashMap<String, Locator>> entry1 : entrySet1) {
			String text1 = entry1.getKey();
			HashMap<String, Locator> value1 = entry1.getValue();
			Set<Entry<String, Locator>> entrySet2 = value1.entrySet();
			for (Entry<String, Locator> entry3 : entrySet2) {
				System.out.println("一级：" + text1 + " 二级：" + entry3.getKey() + ":" + entry3.getValue());
			}
		}
	}
}
