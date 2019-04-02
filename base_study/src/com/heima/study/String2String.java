package com.heima.study;

import java.lang.reflect.Array;
import java.util.Arrays;

public class String2String {

	public static void main(String[] args) {
		String s1 = "91 27 46 38 50";
		String[] arr1 = s1.split(" "); // 变成字符串组

		int[] arr2 = new int[arr1.length];
		for (int i = 0; i < arr2.length; i++) {
			arr2[i] = Integer.parseInt(arr1[i]);
		}
		Arrays.sort(arr2);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr2.length; i++) {
			if (i == arr2.length - 1) {
				sb.append(arr2[i]);
			} else {
				sb.append(arr2[i]).append(" ");
			}
		}

		System.out.println(sb.toString());

	}

}
