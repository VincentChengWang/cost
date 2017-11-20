package com.nm.system.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.nm.bean.Cost;
import com.nm.system.dao.ICostDao;
import com.nm.utils.ConstUtil;
import com.nm.utils.JDBCUtill;
import com.nm.utils.StringUtill;

import sun.management.jdp.JdpBroadcaster;

public class CostDaoImpl implements ICostDao{

	@Override
	public int addCost(Cost cost) {
		// TODO Auto-generated method stub
		//1.拼接sql语句
		String sql="insert into t_cost(costName,costDesc,costMark) value(?,?,?)";
		//2.执行sql语句
		try {
			return JDBCUtill.update(sql, cost.getCostName(),cost.getCostDesc(),ConstUtil.NORMAL_MARK);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int delCost(Cost cost) {
		// TODO Auto-generated method stub
		//多条删除时要开始事务
		Connection conn=JDBCUtill.getConnection();
		try {
			conn.setAutoCommit(false);
			//sql语句
			String sql="update t_cost set costMark=? where costId=?";
			//循环执行sql
			Integer[] ids=cost.getIds();
			QueryRunner qr=new QueryRunner();
			int rows=0;
			for(int i=0;i<ids.length;i++){
				rows+=qr.update(conn, sql, ConstUtil.DELETE_MARK,ids[i]);
			}
			//提交事务
			conn.commit();
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateCost(Cost cost) {
		// 拼接sql
		String sql="update t_cost set costName=?,costDesc=? where costId=?";
		try {
			return JDBCUtill.update(sql, cost.getCostName(),cost.getCostDesc(),cost.getCostId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Cost> queryCost(Cost cost) {
		// TODO Auto-generated method stub
		//拼接sql
		StringBuffer sb=new  StringBuffer("select * from t_cost where costMark=0");
		//2.sql参数
		List<Object> params=new ArrayList<Object>();
		if(StringUtill.isNotNull(cost.getCostId())){
			sb.append(" and costId=?");
			params.add(cost.getCostId());
		}
		if(StringUtill.isNotNull(cost.getCostName())){
			sb.append(" and costName like ?");
			params.add("%"+cost.getCostName()+"%"); 
		}
		//3.执行sql查询
		try {
			return JDBCUtill.queryList(sb.toString(), Cost.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
