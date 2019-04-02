package com.base.Utils;

import java.io.File;

public class FileUtils {
	public static void deleteDir(String dirPath){
		File file = new File(dirPath);
		File[] listFiles = file.listFiles();
		for (File subFile : listFiles) {
			if(subFile.isFile()){
				subFile.delete();
			}else{
				deleteDir(subFile.getAbsolutePath());
			}
		}
		file.delete();
		
		
	}
}
