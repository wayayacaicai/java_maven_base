package com.heima.study;

import java.util.Scanner;

public class Scanner_demo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// System.out.println("请输入第一个数：");
		// int i = sc.nextInt();
		// System.out.println("请输入第二个数：");
		// String a = sc.nextLine();
		//
		// System.out.println(i);
		// System.out.print("------------111");
		// System.out.print(a);
		// System.out.print("222------------");

		// System.out.println("请输入第一个数：");
		// String a = sc.nextLine();
		// System.out.println("请输入第二个数：");
		// String b = sc.nextLine();
		//
		// System.out.println(a);
		// System.out.print("------------111");
		// System.out.print(b);
		// System.out.print("222------------");

		// String aa = new String("abc");
		// System.out.print(aa);

		int[][] arr = { { 1, 2, 3, 4 }, { 2, 3, 4, 5 }, { 4, 5, 6, 7 } };
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]);
			}
			System.out.println("");
		}

	}

}
