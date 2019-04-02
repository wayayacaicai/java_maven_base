package com.user.Service;

import com.user.Dao.UserDao;
import com.user.Dao.UserDaoImp;
import com.user.Pojo.User;

public class UserServiceImp implements UserService {
	UserDao ud = new UserDaoImp();

	@Override
	public boolean UserLogin(String userName, String passWord) {
		// 从数据库取出用户对象
		User user = ud.getUserByName(userName);
		if (user == null) {
			return false;
		}
		if (user.getPassWord().equals(passWord)) {
			return true;
		}
		return false;
	}

	@Override
	public String UserRegister(String userName, String passWord1, String passWord2) {
		// 前后密码不一致
		if (!passWord1.equals(passWord2)) {
			return "1";
		}

		// 用户名是否存在
		boolean userIsExist = ud.checkUserExists(userName);
		if (userIsExist) {
			return "2";
		}

		// 添加用户至数据库
		User user = new User();
		user.set(userName, passWord1);
		ud.addUser(user);
		return "3";
	}

}
