package com.heima.study;

import javax.print.attribute.standard.Chromaticity;

public class CheckQQ_demo {

	public static void main(String[] args) {
		System.out.println(checkQQ("4328748274628123"));
		System.out.println(checkQQ("a12345"));
		System.out.println(checkQQ("12345"));
		System.out.println(checkQQ("123451234512345"));
		System.out.println(checkQQ("02345"));

		String regex = "[1-9]\\d{4,14}";
		System.out.println("012345".matches(regex));
	}

	public static boolean checkQQ(String str) {
		boolean flag = true;
		char[] chs = str.toCharArray();
		if (!str.startsWith("0")) { // 不能以0开头
			if (chs.length >= 5 && chs.length <= 15) { // 5到15个数
				for (int i = 0; i < chs.length; i++) {
					if (chs[i] > '0' && chs[i] < '9') { // 必须是数字

					} else {
						flag = false;
						break;
					}
				}
			} else {
				flag = false;
			}
		} else {
			flag = false;
		}

		return flag;
	}
}
