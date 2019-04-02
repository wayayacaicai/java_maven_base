package com.demo.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class TestSort {

	public static void main(String[] args) {
		ArrayList<User1> au = new ArrayList<>();
		User1 u1 = new User1(1, "yi", 20, 20.5);
		User1 u2 = new User1(2, "er", 15, 10.5);
		User1 u3 = new User1(3, "san", 25, 25.5);
		User1 u4 = new User1(4, "si", 23, 16.5);
		User1 u5 = new User1(5, "wu", 17, 8.5);
		au.add(u1);
		au.add(u2);
		au.add(u3);
		au.add(u4);
		au.add(u5);
		
		
		//老版
//		SortArrayList sa = new SortArrayList();
//		sa.sort(au,new BiJiaoQiImpl());
		
		
		//使用泛型
//		SortArrayListFanXing<User1> auu = new SortArrayListFanXing<>();
//		auu.sort(au, new BiJiaoQiImplFanXing());
		
//		SortArrayListFanXing<User1> auu = new SortArrayListFanXing<>();
//		auu.sort(au, new BiJiaoQiFanXing<User1>() {
//			
//			@Override
//			public boolean biJiao(User1 y1, User1 y2) {
//				if(y1.id>y2.id){
//					return true;
//				}
//				return false;
//			}
//		});
		
		Collections.sort(au, new Comparator<User1>() {

			@Override
			public int compare(User1 o1, User1 o2) {
				if(o1.age>o2.age){
					return -1;
				}
				return 1;
			}
		});
		
		for (User1 user1 : au) {
			System.out.println(user1);
		}
	}

}
