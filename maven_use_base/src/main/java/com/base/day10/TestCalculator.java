package com.base.day10;

public class TestCalculator {

	public static void main(String[] args) {
		Calculator c1 = new Calculator(10, 5, "+");
		c1.calculator();
		System.out.println(c1.getResult());

		Calculator c2 = new Calculator(10, 5, "*");
		c2.calculator();
		System.out.println(c2.getResult());
	}

}
