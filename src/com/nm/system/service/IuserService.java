package com.nm.system.service;

import java.util.List;

import com.nm.bean.UserInfo;

public interface IuserService {
	boolean addUser(UserInfo user);

	boolean deleteUser(UserInfo user);

	boolean updateUser(UserInfo user);

	List<UserInfo> queryUsers(UserInfo user);

	boolean checkUser(String userAccount);

	UserInfo doLogin(UserInfo user);
}
