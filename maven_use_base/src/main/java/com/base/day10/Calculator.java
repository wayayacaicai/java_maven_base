package com.base.day10;

public class Calculator {
	private int a, b, result;

	private String operate;

	public Calculator(int a, int b, String operate) {
		this.a = a;
		this.b = b;
		this.operate = operate;
	}

	/**
	 * 
	 * 这是个计算器的方法
	 */
	public void calculator() {
		switch (operate) {
		case "+":
			result = a + b;
			break;
		case "-":
			result = a - b;
			break;
		case "*":
			result = a * b;
			break;
		case "/":
			result = a / b;
			break;
		default:
			break;
		}
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
