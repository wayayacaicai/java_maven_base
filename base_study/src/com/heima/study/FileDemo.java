package com.heima.study;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDemo {

	public static void main(String[] args) throws Exception {
		// File f1 = new File("test1.txt");
		// System.out.println(f1.getAbsolutePath());
		//
		// File parent = new File("E:\\java_workspace\\heima_base_study");
		// String child = "test1.txt";
		// File f2 = new File(parent, child);
		// System.out.println(f2.exists());
		//
		// String parent1 = "E:\\java_workspace\\heima_base_study";
		// String child1 = "test1.txt";
		// File f3 = new File(parent1,child1);
		// System.out.println(f3.exists());
		//
		// File f4 = new File("aaa.txt");
		// System.out.println(f4.createNewFile());
		//
		// File f5 = new File("aaa");
		// System.out.println(f5.mkdir());
		//
		// File f6 = new File("aaa\\bbb");
		// System.out.println(f6.mkdirs());

		// File f7 = new File("F:\\QQMusicCache");
		// String[] strs = f7.list();
		// for(String str:strs){
		// System.out.println(str);
		// }

		// File[] files = f7.listFiles();
		// for(File f:files){
		// for(File i:f.listFiles()){
		// System.out.println(i);
		// }
		// }

		// FileInputStream fis = new FileInputStream("前程贷接口1.0.pdf");//相当重要
		// FileOutputStream fos = new FileOutputStream("fos.pdf");
		// int len;
		// byte[] bb = new byte[1024 * 8];
		// while((len = fis.read(bb))!= -1){
		// fos.write(bb,0,len);
		// }
		// fis.close();
		// fos.close();
		int a = 5 ^ 6;
		System.out.println(a);
		int c = a ^ 6;
		System.out.println(c);

		try (FileInputStream fis = new FileInputStream("aaa.txt");
				FileOutputStream fos = new FileOutputStream("ccc.txt");
				Myclose my = new Myclose();) {
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		}
	}
}

class Myclose implements AutoCloseable {
	@Override
	public void close() throws Exception {
		System.out.println("我关了！");
	}

}
