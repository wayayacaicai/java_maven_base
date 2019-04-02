/**
 * 
 */
package com.json;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;

/**
 * @Desc:TODO
 * @author:zpp
 * @time:2019年3月25日 下午9:12:58
 */
public class TestJson {

	/**
	 * @Desc
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("src/main/resources/config/movie.json"), "UTF-8"));
		String line = br.readLine();
		 Movie movie = MovieJsonUtils.getMovie(line);
		 System.out.println(movie);

	}

}
