package com.xml.reflect.products;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Desc:商品类xml反射
 * @author:zpp
 * @time:2019年3月22日 下午10:48:58
 */
public class XmlToProduct {
	public static void main(String[] args) {
		String resourcePath = "/Products/Products.xml";

		List<Product> lp = xmlToProduct(resourcePath);

		// 遍历打印所有product
		for (Product product : lp) {
			System.out.println(product);
		}

	}

	private static List<Product> xmlToProduct(String resourcePath) {
		SAXReader sReader = new SAXReader();
		Document read = null;
		try {
			read = sReader.read(XmlToProduct.class.getResourceAsStream(resourcePath));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		Element rootElement = read.getRootElement();
		// 创建一个集合来接收所有product对象
		List<Product> lp = new ArrayList<>();
		// 一层子元素遍历
		List<Element> elements1 = rootElement.elements();
		for (Element element1 : elements1) {
			// 创建一个product实例
			Product product = new Product();
			// 定义一个反射来写活代码
			// Class<Product> clazz = Product.class;
			Class<? extends Product> clazz = product.getClass();
			// 获取一个set方法进行传参
			List<Attribute> attributes = element1.attributes();
			for (Attribute attribute1 : attributes) {
				String name1 = attribute1.getName();
				String value1 = attribute1.getValue();
				reflectMethod(product, clazz, name1, value1);
			}
			// 二层子元素遍历
			List<Element> elements2 = element1.elements();
			for (Element element2 : elements2) {
				String name2 = element2.getName();
				String value2 = element2.getStringValue();
				// 反射方法
				reflectMethod(product, clazz, name2, value2);
			}
			// 把所有product添加进集合
			lp.add(product);
		}
		return lp;
	}

	private static void reflectMethod(Product product, Class<? extends Product> clazz, String name, String value) {
		try {
			String setMethod1 = "set" + (name.charAt(0) + "").toUpperCase() + name.substring(1);
			// 获取声明的属性
			Field field1 = clazz.getDeclaredField(name);
			// 获取声明的属性类型
			Method method1 = clazz.getMethod(setMethod1, field1.getType());

			Object newValue = null;
			if (field1.getType().equals(int.class)) {
				newValue = Integer.parseInt(value);
			} else if (field1.getType().equals(float.class)) {
				newValue = Float.parseFloat(value);
			} else if (field1.getType().equals(String.class)) {
				newValue = value;
			}
			method1.invoke(product, newValue);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
