package com.user.Service;

public interface UserService {
	public boolean UserLogin(String userName, String passWord);

	public String UserRegister(String userName, String passWord1, String passWord2);
}
