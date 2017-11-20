package com.nm.system.dao;

import java.util.List;

import com.nm.bean.UserInfo;

public interface IUserDao {
	int addUser(UserInfo user);
	int delete(UserInfo user);
	int update(UserInfo user);
	List<UserInfo> queryUsers(UserInfo user);
	//����û�ʱ����û���Ϣ
	UserInfo checkUser(String userAccount);
	//��¼��ѯ
	UserInfo queryUser(UserInfo user);
}
