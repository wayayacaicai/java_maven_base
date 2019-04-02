package com.base.day14;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileAndIO {

	public static void main(String[] args) throws Exception{
//		stream();
//		stringStream();

	}

	private static void stringStream() throws FileNotFoundException, IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("src/com/lemon/java/day14/面向对象实战项目（前程贷）.txt"));
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/com/lemon/java/day14/2）.txt"));
		
		while(true){
			String line = bufferedReader.readLine();
			if(line == null){
				break;
			}else{
				bufferedWriter.write(line);
				bufferedWriter.newLine();
			}
		}
		bufferedWriter.close();
		bufferedReader.close();
	}

	private static void stream() throws Exception {
		FileInputStream fis = new FileInputStream("src/com/lemon/java/day14/1.png");
		FileOutputStream fos = new FileOutputStream("src/com/lemon/java/day14/2.png");
		
		byte[] bytes = new byte[1024];
		while(true){
			int size = fis.read(bytes);
			if(size == -1){
				break;
			}else{
				fos.write(bytes, 0, size);
			}
		}
		fos.close();
		fis.close();
	}

}
