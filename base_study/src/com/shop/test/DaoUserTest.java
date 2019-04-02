package com.shop.test;

import java.util.List;

import org.junit.Test;

import com.shop.constant.UserVipLevelConstant;
import com.shop.user.dao.UserDaoImp;
import com.shop.user.pojo.User;

public class DaoUserTest {
	@Test
	public void testAddUser() throws Exception {
		User u1 = new User("zhangsan1", "123", "zhangsan1", 12, UserVipLevelConstant.VIP_2, "12345678911", "CQ");
		User u2 = new User("zhangsan2", "123", "zhangsan2", 15, UserVipLevelConstant.VIP_1, "12345678911", "CQ");
		User u3 = new User("zhangsan3", "123", "zhangsan3", 18, UserVipLevelConstant.VIP_3, "12345678911", "CQ");
		User u4 = new User("zhangsan4", "123", "zhangsan4", 21, UserVipLevelConstant.VIP_4, "12345678911", "CQ");
		User u5 = new User("zhangsan5", "123", "zhangsan5", 14, UserVipLevelConstant.VIP_1, "12345678911", "CQ");

		UserDaoImp userDaoImp = new UserDaoImp();
		userDaoImp.addUser(u1);
		userDaoImp.addUser(u2);
		userDaoImp.addUser(u3);
		userDaoImp.addUser(u4);
		userDaoImp.addUser(u5);

		User user = userDaoImp.getUserByAccount("zhangsan3");
		System.out.println(user);

	}
	
	@Test
	public void testShowUserRangeAge()throws Exception{
		UserDaoImp userDaoImp = new UserDaoImp();
		//根据年龄查询用户
//		List<User> userByAgeRange = userDaoImp.getUserByAgeRange(18, 14);
//		System.out.println(userByAgeRange);
		//根据名字查询用户
		List<User> userByAgeRange = userDaoImp.getUserByName("zhangsan3");
		System.out.println(userByAgeRange);
		
	}
}
