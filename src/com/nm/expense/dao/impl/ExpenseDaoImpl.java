package com.nm.expense.dao.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.nm.bean.Expense;
import com.nm.expense.dao.IExpenseDao;
import com.nm.expense.service.IExpenseService;
import com.nm.utils.DateUtill;
import com.nm.utils.JDBCUtill;
import com.nm.utils.StringUtill;

public class ExpenseDaoImpl implements IExpenseDao{

	@Override
	public int addExpense(Expense expense) {
		// TODO Auto-generated method stub
		//执行多条sql语句时要开启事务
		//1.获取连接
		 Connection conn=JDBCUtill.getConnection();
		 try {
			//开启事务，设置jdbc事务不自动提交
			 conn.setAutoCommit(false);
			 //2.添加报销单信息
			 String expenseSql="insert into t_expense(userId,expenseName,"
			 		+ "expenseState,expenseDesc,expenseDate,expenseMoney) values(?,?,?,?,now(),?)";
			 List<Object> params=new ArrayList<Object>();
			 params.add(expense.getUserId());
			 params.add(expense.getExpenseName());
			 params.add(expense.getExpenseState());
			 params.add(expense.getExpenseDesc());
			 params.add(expense.getExpenseMoney());
			 //4.执行添加报销单sql
			 QueryRunner qr=new QueryRunner();
			 int rows=qr.update(conn, expenseSql, params.toArray());
			 //执行添加报销明细sql
			 //查询刚才添加的报销单id select last_insert_id()获取最后添加的自增的值
			 //显示表状态的sql语句：show table status like "tableName"
			 BigInteger expenseId=qr.query(conn, "select last_insert_id()",new ScalarHandler<BigInteger>());
			 Integer[] costIds=expense.getCostIds();
			 String[] detailDescs=expense.getDetailDescs();
			 Double[] detailMoneys=expense.getDetailMoneys();
			//添加报销详情的sql语句
			 String detailSql="insert into t_expensedetail(expenseId,costId,detailDesc,"
			 		+ "detailMoney) values(?,?,?,?)";
			 //执行添加报销详情的sql
			 if(StringUtill.isNotNull(costIds) && costIds.length>0){
				 for (int i = 0; i < costIds.length; i++) {
					rows+=qr.update(conn,detailSql,expenseId,costIds[i],detailDescs[i],detailMoneys[i]);
				}
					 
			 }
			 
			 //提交事务
			 conn.commit();
			 return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int updateExpense(Expense expense) {
		// 修改报销单信息、修改报销明细
		// 开启事务
		Connection conn=JDBCUtill.getConnection();
		try {
			conn.setAutoCommit(false);
			//1.sql
			String sql="update t_expense set expenseName=?,expenseState=?,expenseDesc=?,expenseDate=now(),expenseMoney=? where expenseId=?";
			QueryRunner qr=new QueryRunner();
			//sql参数
			List<Object> params=new ArrayList<Object>();
			params.add(expense.getExpenseName());
			params.add(expense.getExpenseState());
			params.add(expense.getExpenseDesc());
			params.add(expense.getExpenseMoney());
			params.add(expense.getExpenseId());
			int rows=qr.update(conn, sql, params.toArray());
			//2修改报销明细的信息
			//2-1先执行删除所有原报销单的报销明细
			String sql1="delete from t_expensedetail where expenseId=?";
			rows+=qr.update(conn,sql1,expense.getExpenseId());
			//2-2 再执行添加现有的报销明细
			String sql2="insert into t_expensedetail(expenseId,costId,detailDesc,detailMoney) values(?,?,?,?)";
			Integer[] costIds=expense.getCostIds();
			String[] detailDescs=expense.getDetailDescs();
			Double[] detailMoneys=expense.getDetailMoneys();
			if(StringUtill.isNotNull(costIds) && costIds.length>0){
				for (int i = 0; i< costIds.length; i++) {
					rows+=qr.update(conn, sql2, expense.getExpenseId(),costIds[i],detailDescs[i],detailMoneys[i]);
				}
				
			}
			conn.commit();
			return rows;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int deleteExpense(Expense expense) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Expense> queryExpense(Expense expense) {
		// 拼接sql语句
		StringBuffer sb=new StringBuffer("select te.*,tu.userName from t_expense te,t_users tu "
				+ "where te.userId=tu.userId");
		//拼接sql查询参数
		List<Object> params=new ArrayList<Object>();
		if(StringUtill.isNotNull(expense.getExpenseId())){
			sb.append(" and te.expenseId=?");
			params.add(expense.getExpenseId());
		}
		if(StringUtill.isNotNull(expense.getUserId())){
			sb.append(" and tu.userId=?");
			params.add(expense.getUserId());
		}
		if(StringUtill.isNotNull(expense.getUserName())){
			sb.append(" and tu.userName like ?");
			params.add("%"+expense.getUserName()+"%");
		}
		if(StringUtill.isNotNull(expense.getExpenseName())){
			sb.append(" and te.expenseName like?");
			params.add("%"+expense.getExpenseName()+"%");
		}
		if(StringUtill.isNotNull(expense.getStartDate())){
			sb.append(" and te.expenseDate >=?");
			params.add(DateUtill.date2str(expense.getStartDate(), "YYYY-MM-dd HH:mm:ss"));
		}
		if(StringUtill.isNotNull(expense.getEndDate())){
			sb.append(" and te.expenseDate <? ");
			params.add(DateUtill.date2str(expense.getEndDate(),"YYYY-MM-dd HH:mm:ss",1));
		}
		if(StringUtill.isNotNull(expense.getExpenseState())){
			sb.append(" and te.expenseState =?");
			params.add(expense.getExpenseState());
		}
		//还要根据报销单的状态进行查询
		Integer[] expenseStates=expense.getExpenseStates();
		if(StringUtill.isNotNull(expenseStates) && expenseStates.length>0){
			sb.append(" and expenseState in (");
			String str=Arrays.toString(expenseStates);
			sb.append(str.substring(1, str.length()-1));
				sb.append(")");
			
		}
		//3.执行sql查询
		try {
			return JDBCUtill.queryList(sb.toString(), Expense.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
