package com.demo.parrortGetMoney;

public class Parrort {
	private int id;
	private String name;
	private int age;
	private String color;

	public Parrort() {

	}

	public Parrort(int id, String name, int age, String color) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.color = color;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void set(int id, String name, int age, String color) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.color = color;
	}

	@Override
	public String toString() {

		return this.id + "," + this.name + "," + this.age + "," + this.color;
	}

	public Money ParrortGetMoney(Money m) {
		boolean result1 = validateMoneyAmount(m);
		boolean result2 = validateMoneyIsClean(m);
		boolean result3 = validateMoneyIsTrue(m);
		if (result1 && result2 && result3) {
			return m;
		}
		return null;
	}

	private boolean validateMoneyIsTrue(Money m) {
		if (m.getAmount() < 20) {
			return false;
		}
		return true;
	}

	private boolean validateMoneyIsClean(Money m) {
		if (m.isClean()) {
			return true;
		}
		return false;
	}

	private boolean validateMoneyAmount(Money m) {
		if (m.isTrue()) {
			return true;
		}
		return false;
	}

}
