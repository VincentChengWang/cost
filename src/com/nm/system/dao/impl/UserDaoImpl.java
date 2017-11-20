package com.nm.system.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.nm.bean.UserInfo;
import com.nm.system.dao.IUserDao;
import com.nm.utils.ConstUtil;
import com.nm.utils.JDBCUtill;
import com.nm.utils.StringUtill;

public class UserDaoImpl implements IUserDao {

	@Override
	public int addUser(UserInfo user) {
		// TODO Auto-generated method stub
		// �������
		// 1.ƴ��sql���
		String sql = "insert into t_users (userName,userSex,userAge,userPhone,"
				+ "userAccount,userPwd,userRole,userSalary,userMark) values(?,?,?,?,?,?,?,?,?)";

		// 2.sql����
		List<Object> params = new ArrayList<>();
		params.add(user.getUserName());
		params.add(user.getUserSex());
		params.add(user.getUserAge());
		params.add(user.getUserPhone());
		params.add(user.getUserAccount());
		params.add(user.getUserPwd());
		params.add(user.getUserRole());
		params.add(user.getUserSalary());
		params.add(ConstUtil.NORMAL_MARK);
		try {
			JDBCUtill.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 3.ִ��sql
		int rows = 0;
		try {
			rows = JDBCUtill.update(sql, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;

	}

	@Override
	public int delete(UserInfo user) {
		// TODO Auto-generated method stub
		// 1.sql���
		String sql = "update t_users set userMark=? where userId=?";
		// ͨ������ִ�ж���sql���
		Connection conn = JDBCUtill.getConnection();
		JDBCUtill.startTranscation(conn);
		Integer[] ids = user.getIds();
		// ����һ��sqlִ�ж���
		try {
			QueryRunner qr = new QueryRunner();
			int rows = 0;
			for (int i = 0; i < ids.length; i++) {

				rows += qr.update(conn, sql, 1, ids[i]);
			}
			// ִ��sql�ɹ���Ҫ�ύ����
			JDBCUtill.commitTranscation(conn);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// ����ع�
			JDBCUtill.rollbackTranscation(conn);
		}

		return 0;
	}

	@Override
	public int update(UserInfo user) {
		// TODO Auto-generated method stub
		// 1.ƴ��sql
		String sql = "update t_users set userName=?,userSex=?,userAge=?,userPhone=?,userPwd=?,userRole=?,userSalary=? where userId=?";
		try {
			int rows = JDBCUtill.update(sql, user.getUserName(), user.getUserSex(), user.getUserAge(),
					user.getUserPhone(), user.getUserPwd(), user.getUserRole(), user.getUserSalary(),user.getUserId());
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<UserInfo> queryUsers(UserInfo user) {
		// 1.ƴ��sql���
		StringBuffer sb = new StringBuffer("select * from t_users where 1=1 and userMark=0");
		// 2.ƴ��sql����
		List<Object> params = new ArrayList<Object>();
		if (StringUtill.isNotNull(user.getUserId())) {
			sb.append(" and userId=?");
			params.add(user.getUserId());
		}
		if (StringUtill.isNotNull(user.getUserName())) {
			sb.append(" and userName like ?");
			params.add("%" + user.getUserName() + "%");
		}
		// 3.ִ��sql
		try {
			return JDBCUtill.queryList(sb.toString(), UserInfo.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserInfo checkUser(String userAccount) {
		// TODO Auto-generated method stub
		// sql���
		String sql = "select * from t_users where userAccount=?";
		// ִ��
		try {
			return JDBCUtill.queryOne(sql, UserInfo.class, userAccount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * �û���¼ �����û��������ѯ�û�
	 */
	@Override
	public UserInfo queryUser(UserInfo user) {
		// TODO Auto-generated method stub
		// 1.sql���
		String sql = "select * from t_users where userAccount=? and userPwd=?";
		// 2.ִ��sql
		try {
			return JDBCUtill.queryOne(sql, UserInfo.class, user.getUserAccount(), user.getUserPwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
