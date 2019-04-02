package com.heima.study;

public class ThrowException {

	public static void main(String[] args) throws Exception {
		try {
			Class<?> forName = Class.forName("zzz");
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
		throw new Exception("ceshi");
	}

}
