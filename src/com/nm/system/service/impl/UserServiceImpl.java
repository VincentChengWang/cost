package com.nm.system.service.impl;

import java.util.List;

import com.nm.bean.UserInfo;
import com.nm.system.dao.IUserDao;
import com.nm.system.dao.impl.UserDaoImpl;
import com.nm.system.service.IuserService;

public class UserServiceImpl implements IuserService{
//service����dao��
	IUserDao userDao=new UserDaoImpl();
	@Override
	public boolean addUser(UserInfo user) {
		// TODO Auto-generated method stub
		
		//������Ӱ�������
		int rows=userDao.addUser(user);
		if(rows>0)
			return true;
		return false;
	}

	@Override
	public boolean deleteUser(UserInfo user) {
		// TODO Auto-generated method stub
		int rows=userDao.delete(user);
		if(rows>0)
			return true;
		return false;
	}

	@Override
	public boolean updateUser(UserInfo user) {
		// TODO Auto-generated method stub
		int rows=userDao.update(user);
		if(rows>0)
			return true;
		return false;
	}

	@Override
	public List<UserInfo> queryUsers(UserInfo user) {
		// ����dao����ݲ�ѯ������ѯ�û�
		
		return userDao.queryUsers(user);
	}

	@Override
	public boolean checkUser(String userAccount) {
		// TODO Auto-generated method stub
		UserInfo user=userDao.checkUser(userAccount);
		if(user != null)
			return true;
		
		return false;
	}

	@Override
	public UserInfo doLogin(UserInfo user) {
		// TODO Auto-generated method stub
		//service ����dao
		return userDao.queryUser(user);
	}

}
