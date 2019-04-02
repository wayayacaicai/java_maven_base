package com.heima.study;

public class Object_demo {

	public static void main(String[] args) {
		Demo d1 = new Demo();
		Demo d2 = new Demo();
		Class d = d1.getClass();
		System.out.println(d1.equals(d2));
		System.out.println(d1.hashCode());
		System.out.println(d1.toString());
		System.out.println(d.toString());
		System.out.println(d.getName());

	}

}

class Demo {
	private String name;
	private int age;

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

	@Override
	public String toString() {

		return "不错哟";
	}

}
