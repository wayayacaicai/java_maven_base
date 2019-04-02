package com.demo.compare;

import java.awt.TexturePaint;

public class BiJiaoQiImplFanXing implements BiJiaoQiFanXing<User1> {
	// 比较薪资
	@Override
	public boolean biJiao(User1 u1, User1 u2) {
		if (u1.getSalary() < u2.getSalary()) {
			return true;
		}
		return false;
	}

	// 实现age比较
	// @Override
	// public boolean biJiao(User1 u1, User1 u2) {
	// if(u1.getAge()>u2.getAge()){
	// return true;
	// }
	// return false;
	// }

}
