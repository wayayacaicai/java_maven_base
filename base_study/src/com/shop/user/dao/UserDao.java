package com.shop.user.dao;

import java.util.List;

import com.shop.user.pojo.User;

public interface UserDao {
	// 增加用户
	public boolean addUser(User u) throws Exception;

	// 删除用户
	public boolean delUser(String account) throws Exception;

	// 更新用户
	public boolean updateUser(User u) throws Exception;

	// 根据账号
	public User getUserByAccount(String account) throws Exception;

	// 根据年龄范围
	public List<User> getUserByAgeRange(int maxAge, int minAge) throws Exception;

	// 根据vip等级
	public List<User> getUserByVipRange(int maxVip, int minVip) throws Exception;

	// 根据姓名
	public List<User> getUserByName(String name) throws Exception;
}
