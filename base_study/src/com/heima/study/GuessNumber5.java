package com.heima.study;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber5 {

	public static void main(String[] args) {
		int guessNumber = (int) (Math.random() * 100) + 1;
		GuessNumber.guessNumber(guessNumber);

	}

}

class GuessNumber {
	int number;

	public GuessNumber() {
	}

	public GuessNumber(int number) {
		this.number = number;
	}

	public static void guessNumber(int guessNumber) {

		Scanner sc = new Scanner(System.in);
		System.out.println("请输入数字：");
		while (true) {
			if (sc.hasNextInt()) {
				int result = sc.nextInt();
				if (result > guessNumber) {
					System.out.println("输入大了");
				} else if (result < guessNumber) {
					System.out.println("输入小了");
				} else {
					System.out.println("猜对了");
					break;
				}
				System.out.println("继续猜：");
			} else {
				System.out.println("输入的不是整数！");
			}
		}
	}
}