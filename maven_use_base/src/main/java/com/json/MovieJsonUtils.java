/**
 * 
 */
package com.json;

import com.google.gson.Gson;

/**
 * @Desc:解析json数据
 * @author:zpp
 * @time:2019年3月25日 下午9:08:18
 */
public class MovieJsonUtils {
	public static Movie getMovie(String line) {
		Gson gson = new Gson();
		Movie movie = gson.fromJson(line, Movie.class);
		return movie;
	}
}
