package com.shop.utils;

/**
 * 操作文件的工具类
 **/
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.shop.user.pojo.User;

public class FileOperateUtils<T> {
	public HashMap<String, T> getMapFromFile(String file_path) throws Exception {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file_path));
		@SuppressWarnings("unchecked")
		HashMap<String, T> hm = (HashMap<String, T>) ois.readObject();
		ois.close();
		return hm;
	}

	public void setMapToFile(HashMap<String, T> hm, String file_path) throws Exception {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file_path));
		oos.writeObject(hm);
		oos.close();
	}
}
