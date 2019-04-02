package com.base.day13;

import java.io.File;

public class FileTest {

	public static void main(String[] args) {
		String pathname = "D:" + File.separator + "1.txt";
		File file = new File(pathname);
		System.out.println(file.exists());
		if(file.isFile()){
			System.out.println("是一个文件");
		}
	}

}
