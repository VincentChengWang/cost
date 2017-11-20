package com.nm.system.dao;

import java.util.List;

import com.nm.bean.UserInfo;

public interface IUserDao {
	int addUser(UserInfo user);
	int delete(UserInfo user);
	int update(UserInfo user);
	List<UserInfo> queryUsers(UserInfo user);
	//添加用户时检查用户信息
	UserInfo checkUser(String userAccount);
	//登录查询
	UserInfo queryUser(UserInfo user);
}
