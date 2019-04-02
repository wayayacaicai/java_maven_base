package com.shop.user.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.shop.constant.DataBasePathConstant;
import com.shop.user.pojo.User;
import com.shop.utils.FileOperateUtils;

public class UserDaoImp implements UserDao {
	FileOperateUtils<User> ftu = new FileOperateUtils<>();

	@Override
	public boolean addUser(User u) throws Exception {
		HashMap<String, User> hm = null;
		File file = new File(DataBasePathConstant.USER_DATA_PATH);
		// 取出整个user数据库
		if (file.exists()) {
			hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);

		} else {
			file.createNewFile();
			hm = new HashMap<String, User>();
		}
		// 然后添加新用户
		hm.put(u.getAccount(), u);

		// 然后写入整个user数据库
		ftu.setMapToFile(hm, DataBasePathConstant.USER_DATA_PATH);

		return true;

	}

	@Override
	public boolean delUser(String account) throws Exception {
		// 取出整个user数据库
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		User remove = hm.remove(account);
		if (remove == null) {
			return false;
		}
		ftu.setMapToFile(hm, DataBasePathConstant.USER_DATA_PATH);
		return true;
	}

	@Override
	public boolean updateUser(User u) throws Exception {
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		hm.put(u.getAccount(), u);
		ftu.setMapToFile(hm, DataBasePathConstant.USER_DATA_PATH);
		return true;
	}

	@Override
	public User getUserByAccount(String account) throws Exception {
		// 取出整个user数据库
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		return hm.get(account);
	}

	@Override
	public List<User> getUserByAgeRange(int maxAge, int minAge) throws Exception {
		List<User> uList = new ArrayList<>();
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		Collection<User> users = hm.values();
		for (User user : users) {
			if (user.getAge() >= minAge && user.getAge() <= maxAge) {
				uList.add(user);
			}
		}
		return uList;
	}

	@Override
	public List<User> getUserByVipRange(int maxVip, int minVip) throws Exception {
		List<User> uList = new ArrayList<>();
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		Collection<User> users = hm.values();
		for (User user : users) {
			if (user.getVipLevel() >= minVip && user.getVipLevel() <= maxVip) {
				uList.add(user);
			}
		}
		return uList;
	}

	@Override
	public List<User> getUserByName(String name) throws Exception {
		List<User> uList = new ArrayList<>();
		HashMap<String, User> hm = ftu.getMapFromFile(DataBasePathConstant.USER_DATA_PATH);
		Collection<User> users = hm.values();
		for (User user : users) {
			if (user.getName().contains(name)) {
				uList.add(user);
			}
		}
		return uList;
	}

}
