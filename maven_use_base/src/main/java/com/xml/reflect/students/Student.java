package com.xml.reflect.students;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @Desc:学生类
 * @author:zpp
 * @time:2019年3月22日 下午10:50:53
 */
public class Student implements Serializable {
	private String id;
	private String name;
	private int age;
	private String sex;
	private ArrayList<String> hobby;

	public Student() {
		super();

	}

	public Student(String id, String name, int age, String sex, ArrayList<String> hobby) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.hobby = hobby;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public ArrayList<String> getHobby() {
		return hobby;
	}

	public void setHobby(ArrayList<String> hobby) {
		this.hobby = hobby;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", hobby=" + hobby + "]";
	}

}
