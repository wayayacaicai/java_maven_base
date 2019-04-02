package com.heima.study;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

//递归遍历出.mp4结尾的文件对象
public class FileDieDai {
	public static void main(String[] args) {
		ArrayList<File> list = new ArrayList<>(); // 用一个列表对象接收所有文件对象

		File dir = getFile(); // 得到一个文件路径，其他则重新输入

		ArrayList<File> list1 = getJavaFile(dir, list); // 列表已收集符合要求的文件对象

		for (File file : list1) { // 打印所有文件对象
			System.out.println(file);
		}
	}

	public static File getFile() { // 判断是一个文件路径才返回
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入一个文件路径：");
		while (true) {
			String line = sc.nextLine();
			File file = new File(line);
			if (!file.exists()) {
				System.out.println("文件路径不存在，请重新输入：");
			} else if (file.isFile()) {
				System.out.println("这是一个文件，请重新输入：");
			} else {
				return file;
			}
		}
	}

	public static ArrayList<File> getJavaFile(File dir, ArrayList<File> list) { // 递归遍历出所有文件
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isFile() && file.getName().endsWith(".mp4")) {
				list.add(file);
			} else if (file.isDirectory()) {
				getJavaFile(file, list);
			}
		}
		return list;
	}
}
