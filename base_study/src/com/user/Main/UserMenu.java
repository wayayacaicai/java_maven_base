package com.user.Main;

import java.util.Scanner;

import com.user.Service.UserService;
import com.user.Service.UserServiceImp;

public class UserMenu {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		UserService us = new UserServiceImp();

		boolean flag = true;
		while (flag) {
			System.out.println("请选择1.登录  2.注册  3.退出（quit）");
			String line1 = sc.nextLine();

			switch (line1) {
			// 登录账号
			case "1":
				System.out.println("请输入用户名");
				String userName1 = sc.nextLine();
				System.out.println("请输入密码");
				String passWord1 = sc.nextLine();
				// 调用业务层逻辑验证
				boolean result1 = us.UserLogin(userName1, passWord1);
				if (result1) {
					System.out.println("登陆成功，欢迎您进入！");
				} else {
					System.out.println("用户名或密码输入错误");
				}
				break;
			// 注册账号
			case "2":
				System.out.println("请输入用户名");
				String userName2 = sc.nextLine();
				System.out.println("请输入密码");
				String passWord2 = sc.nextLine();
				System.out.println("请再次输入密码");
				String passWord3 = sc.nextLine();
				// 调用业务层逻辑验证
				String result2 = us.UserRegister(userName2, passWord2, passWord3);
				switch (result2) {
				case "1":
					System.out.println("两次密码输入不一致！");
					break;
				case "2":
					System.out.println("用户名已存在，请重新输入用户名");
					break;
				case "3":
					System.out.println("注册成功");
					break;
				}
				break;
			case "quit":
				flag = false;
				break;
			}

		}
		System.out.println("您已退出");
	}

}
