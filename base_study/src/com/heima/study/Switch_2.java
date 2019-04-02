package com.heima.study;

import java.util.Scanner;

public class Switch_2 {

	public static void main(String[] args) {
		// String week = "8";
		// switch(week){
		// case "1":
		// System.out.println("星期一");break;
		// case "2":
		// System.out.println("星期二");break;
		// case "3":
		// System.out.println("星期三");break;
		// case "4":
		// System.out.println("星期四");break;
		// case "5":
		// System.out.println("星期五");break;
		// case "6":
		// System.out.println("星期六");break;
		// case "7":
		// System.out.println("星期七");break;
		// default:
		// System.out.println("输入错误！");break;
		// }

		Scanner sc = new Scanner(System.in);
		System.out.println("请输入月份：");
		int month = sc.nextInt();
		switch (month) {
		case 3:
		case 4:
		case 5:
			System.out.println(month + "月是春季");
			break;
		case 6:
		case 7:
		case 8:
			System.out.println(month + "月是夏季");
			break;
		case 9:
		case 10:
		case 11:
			System.out.println(month + "月是秋季");
			break;
		case 12:
		case 1:
		case 2:
			System.out.println(month + "月是冬季");
			break;
		default:
			System.out.println("输入错误！");
			break;
		}
	}

}
