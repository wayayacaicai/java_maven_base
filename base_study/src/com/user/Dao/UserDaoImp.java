package com.user.Dao;

import com.user.Pojo.User;
import com.user.Pojo.UserDatabase;

public class UserDaoImp implements UserDao {

	@Override
	public User getUserByName(String userName) {
		User user = UserDatabase.hm.get(userName);
		return user;
	}

	/**
	 * 检查用户存在
	 */
	@Override
	public boolean checkUserExists(String userName) {
		boolean result = UserDatabase.hm.containsKey(userName);
		return result;
	}

	@Override
	public void addUser(User user) {
		UserDatabase.hm.put(user.getUserName(), user);
	}

}
