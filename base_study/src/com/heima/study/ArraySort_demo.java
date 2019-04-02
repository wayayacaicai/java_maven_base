package com.heima.study;

public class ArraySort_demo {

	public static void main(String[] args) {
		int[] arr = { 33, 12, 55, 44, 99, 88 };
		// BubbleSort(arr);
		SelectSort(arr);
		PrintArr(arr);

	}

	public static void BubbleSort(int[] arr) { // 冒泡
		for (int i = 0; i < arr.length - 1; i++) { // 轮询次数
			for (int j = 0; j < arr.length - 1 - i; j++) { // 交换次数，-i优化效率，每次最后一个数不用再取
				if (arr[j] > arr[j + 1]) {
					SwitchArr(arr, j, j + 1);
				}
			}
		}
	}

	public static void SelectSort(int[] arr) { // 选择排序
		for (int i = 0; i < arr.length - 1; i++) { // 轮询次数
			for (int j = 1 + i; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					SwitchArr(arr, i, j);
				}
			}
		}
	}

	public static void SwitchArr(int[] arr, int x, int y) { // 交换
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}

	public static void PrintArr(int[] arr) { // 打印数组
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
	}
}
