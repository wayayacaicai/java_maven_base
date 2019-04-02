package com.xml.reflect.students;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * @Desc:文件操作方法，操作对象流
 * @author:zpp
 * @time:2019年3月22日 下午10:50:31
 */
public class FileOperateUtils {
	public static void setToFile(ArrayList<Student> aStudents, String file_path) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_path));
		oos.writeObject(aStudents);
		oos.close();
	}

	public static ArrayList<Student> getFromFile(String file_path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file_path));
		ArrayList<Student> aStudents = (ArrayList<Student>) ois.readObject();
		ois.close();
		return aStudents;
	}
}
