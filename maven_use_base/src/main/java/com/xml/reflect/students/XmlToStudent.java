package com.xml.reflect.students;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @Desc:学生类xml反射
 * @author:zpp
 * @time:2019年3月22日 下午10:51:06
 */
public class XmlToStudent {

	public static void main(String[] args) throws Exception {
		ArrayList<Student> astudents = new ArrayList<>();

		// 需要保存的文件路径
		String file_path = "src/main/resources/Students/Students.txt";

		// 获取xml转换后的student对象
		astudents = XmlToStudent.changeXmlToStudent(astudents);

		// 写入文件中
		FileOperateUtils.setToFile(astudents, file_path);
		System.out.println("已经成功写入文件");

		// 从文件中读出
		ArrayList<Student> fromFile = FileOperateUtils.getFromFile(file_path);
		System.out.println(fromFile);
	}

	public static ArrayList<Student> changeXmlToStudent(ArrayList<Student> aList) throws Exception {
		// 创建一个xml解析器对象
		SAXReader reader = new SAXReader();
		// 获取一个文档对象
		// InputStream fileInputStream = new
		// FileInputStream("src/main/resources/Students.xml");
		// Document read = reader.read(fileInputStream);
		Document read = reader.read(XmlToStudent.class.getResourceAsStream("/Students/Students.xml"));
		// 获取xml根节点
		Element rootElement = read.getRootElement();
		// 获取所有的子标签
		List<Element> elements = rootElement.elements();
		// 第一层
		for (Element element : elements) {
			// 创建一个学生对象
			Student student = new Student();

			// 获取子标签的属性id
			Attribute attribute = element.attribute("id");
			// 获取子标签属性id的名字
			String name = attribute.getName();
			// 获取子标签属性id的值
			String text = attribute.getValue();

			student.setId(text);
			// System.out.println(name + "=" + text);

			// 第二层
			List<Element> elements2 = element.elements();
			for (Element element2 : elements2) {
				// 学生具体属性
				String name2 = element2.getName();
				String text2 = element2.getStringValue();
				if (name2.equals("name")) {
					student.setName(text2);
				}
				if (name2.equals("age")) {
					student.setAge(Integer.parseInt(text2));
				}
				if (name2.equals("sex")) {
					student.setSex(text2);
				}

				// 第三层
				if (element2.getName().equals("hobby")) {
					List<Element> elements3 = element2.elements();
					// 业余爱好有多个用集合接收
					ArrayList<String> hobbies = new ArrayList<>();
					for (Element element3 : elements3) {
						String name3 = element3.getName();
						String text3 = element3.getStringValue();
						hobbies.add(text3);
						student.setHobby(hobbies);
					}
				}
			}
			// System.out.println(student);
			aList.add(student);
		}

		return aList;
	}
}
