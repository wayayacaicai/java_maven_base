package com.testng1;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.base.licai.Invester;
import com.base.licai.Member;

/**
 * @Desc:TestNG的invester的模板
 * @author:zpp 
 * @time:2019年3月22日 下午10:57:13
 */
public class Member_Test {
	static Member m = null;

	@BeforeTest
	public void init() {
		m = new Invester();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		System.out.println(1);
	}
	
	@AfterMethod
	public void afterMethod(){
		System.out.println(2);
	}
}
