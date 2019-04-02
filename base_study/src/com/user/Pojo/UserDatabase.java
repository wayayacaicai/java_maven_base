package com.user.Pojo;

import java.util.HashMap;

public class UserDatabase {
	public static HashMap<String, User> hm = new HashMap<>();

	static {
		User u1 = new User();
		u1.set("zhangsan", "123");
		User u2 = new User();
		u2.set("lisi", "123");
		hm.put(u1.getUserName(), u1);
		hm.put(u2.getUserName(), u2);
	}
}
