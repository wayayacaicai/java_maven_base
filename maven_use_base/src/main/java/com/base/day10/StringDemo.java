package com.base.day10;

/**
 * 字符串练习
 */
public class StringDemo {

	public static void main(String[] args) {
		String s = "abcdefghijklmnopqrstabcuvwxyzabc";
		System.out.println(s.substring(1, 3));
		System.out.println(s.indexOf("bcd"));
		System.out.println(s.lastIndexOf("abc"));
		System.out.println(s.valueOf(1));

		System.out.println("a".compareTo("中"));
	}

}
