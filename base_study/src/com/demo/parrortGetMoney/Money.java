package com.demo.parrortGetMoney;

public class Money {
	private int amount;
	private boolean isClean;
	private boolean isTrue;

	public Money() {
	}

	public Money(int amount, boolean isClean, boolean isTrue) {
		super();
		this.amount = amount;
		this.isClean = isClean;
		this.isTrue = isTrue;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public boolean isClean() {
		return isClean;
	}

	public void setClean(boolean isClean) {
		this.isClean = isClean;
	}

	public boolean isTrue() {
		return isTrue;
	}

	public void setTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}

	public void set(int amount, boolean isClean, boolean isTrue) {
		this.amount = amount;
		this.isClean = isClean;
		this.isTrue = isTrue;
	}

	@Override
	public String toString() {

		return "金额：" + this.amount + ",干净：" + this.isClean() + ",真钱：" + this.isTrue();
	}

}
