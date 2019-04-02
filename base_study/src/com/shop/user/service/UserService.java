package com.shop.user.service;

import com.shop.user.pojo.User;

public interface UserService {
	// 检查账号是否存在
	public boolean checkIfAccountExist(String account) throws Exception;

	public boolean addUser(User u) throws Exception;

	public boolean userLogin(String account, String password) throws Exception;

	public boolean updateUserName(String account, String newName) throws Exception;

	public boolean updateUserAge(String account, int newAge) throws Exception;

	// 注销账号
	public boolean removeUser(String account) throws Exception;
}
