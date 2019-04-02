package com.shop.user.service;

import java.awt.TexturePaint;

import com.shop.user.dao.UserDao;
import com.shop.user.dao.UserDaoImp;
import com.shop.user.pojo.User;

public class UserServiceImp implements UserService {
	UserDao ud = new UserDaoImp();

	@Override
	public boolean checkIfAccountExist(String account) throws Exception {
		User userByAccount = ud.getUserByAccount(account);
		return userByAccount != null;
	}

	@Override
	public boolean addUser(User u) throws Exception {
		boolean addUser = ud.addUser(u);
		return addUser;
	}

	@Override
	public boolean userLogin(String account, String password) throws Exception {
		User userByAccount = ud.getUserByAccount(account);
		if (userByAccount == null) {
			return false;
		} else {
			return userByAccount.getPassword().equals(password);
		}
	}

	@Override
	public boolean updateUserName(String account, String newName) throws Exception {
		User userByAccount = ud.getUserByAccount(account);
		if (userByAccount == null) {
			return false;
		}
		userByAccount.setName(newName);
		ud.updateUser(userByAccount);
		return true;

	}

	@Override
	public boolean updateUserAge(String account, int newAge) throws Exception {
		User userByAccount = ud.getUserByAccount(account);
		if (userByAccount == null) {
			return false;
		}
		userByAccount.setAge(newAge);

		ud.updateUser(userByAccount);
		return true;
	}

	@Override
	public boolean removeUser(String account) throws Exception {
		boolean delUser = ud.delUser(account);
		return delUser;
	}

}
