package com.heima.study;

import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class Scanner_1 {

	public static void main(String[] args) {

		// study1
		// Scanner sc = new Scanner(System.in); //创建键盘对象
		// System.out.println("请输入一个整数:");
		// int x = sc.nextInt(); //将数据存入x中
		// System.out.println("x:" + x);
		//
		// System.out.println("请输入第二个整数:");
		// int y = sc.nextInt();
		// System.out.println("y:" + y);

		// study2
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入第一个整数:");
		int x = sc.nextInt();
		System.out.print("请输入第二个整数:");
		int y = sc.nextInt();

		// int sum = x + y;
		// System.out.println("整数的和是：" + sum);

		// int max = (x>y) ? x:y;
		// System.out.println("最大的数是:" + max);

		// boolean b = (x == y);
		// System.out.println("是真的吗:" + b);

		System.out.print("请输入第三个整数:");
		int z = sc.nextInt();
		int temp = (x > y) ? x : y;
		int max = (temp > z) ? temp : z;
		System.out.println("最大的数是:" + max);
	}

}
