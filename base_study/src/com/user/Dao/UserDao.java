package com.user.Dao;

import com.user.Pojo.User;

public interface UserDao {
	/**
	 * 通过用户名得到用户
	 */
	public User getUserByName(String userName);

	public boolean checkUserExists(String userName);

	public void addUser(User user);

}
