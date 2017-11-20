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
		// 添加数据
		// 1.拼接sql语句
		String sql = "insert into t_users (userName,userSex,userAge,userPhone,"
				+ "userAccount,userPwd,userRole,userSalary,userMark) values(?,?,?,?,?,?,?,?,?)";

		// 2.sql参数
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
		// 3.执行sql
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
		// 1.sql语句
		String sql = "update t_users set userMark=? where userId=?";
		// 通过事务执行多条sql语句
		Connection conn = JDBCUtill.getConnection();
		JDBCUtill.startTranscation(conn);
		Integer[] ids = user.getIds();
		// 创建一个sql执行对象
		try {
			QueryRunner qr = new QueryRunner();
			int rows = 0;
			for (int i = 0; i < ids.length; i++) {

				rows += qr.update(conn, sql, 1, ids[i]);
			}
			// 执行sql成功后要提交事务
			JDBCUtill.commitTranscation(conn);
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// 事务回滚
			JDBCUtill.rollbackTranscation(conn);
		}

		return 0;
	}

	@Override
	public int update(UserInfo user) {
		// TODO Auto-generated method stub
		// 1.拼接sql
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
		// 1.拼接sql语句
		StringBuffer sb = new StringBuffer("select * from t_users where 1=1 and userMark=0");
		// 2.拼接sql条件
		List<Object> params = new ArrayList<Object>();
		if (StringUtill.isNotNull(user.getUserId())) {
			sb.append(" and userId=?");
			params.add(user.getUserId());
		}
		if (StringUtill.isNotNull(user.getUserName())) {
			sb.append(" and userName like ?");
			params.add("%" + user.getUserName() + "%");
		}
		// 3.执行sql
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
		// sql语句
		String sql = "select * from t_users where userAccount=?";
		// 执行
		try {
			return JDBCUtill.queryOne(sql, UserInfo.class, userAccount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 用户登录 根据用户名密码查询用户
	 */
	@Override
	public UserInfo queryUser(UserInfo user) {
		// TODO Auto-generated method stub
		// 1.sql语句
		String sql = "select * from t_users where userAccount=? and userPwd=?";
		// 2.执行sql
		try {
			return JDBCUtill.queryOne(sql, UserInfo.class, user.getUserAccount(), user.getUserPwd());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
