package com.heima.study;

public class Array_4 {

	public static void main(String[] args) {
		int[] arr = new int[] { 11, 22, 33, 44, 55, 66, 77 };
		reverseArray(arr);
		print(arr);
	}

	public static void reverseArray(int[] arr) { // 数组反转
		int temp;
		for (int i = 0; i < arr.length / 2; i++) {
			temp = arr[i];
			arr[i] = arr[arr.length - 1 - i];
			arr[arr.length - 1 - i] = temp;
		}

	}

	public static void print(int[] arr) { // 打印数组
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ",");
		}
	}

}
