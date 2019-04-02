package com.heima.study;

public class PayTool {
	public boolean pay(int money) {
		if (money < 100) {
			System.out.println("no");
			return false;
		} else {
			System.out.println("yes");
			return true;
		}
	}
}
