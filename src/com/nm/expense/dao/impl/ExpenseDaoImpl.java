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
		//ִ�ж���sql���ʱҪ��������
		//1.��ȡ����
		 Connection conn=JDBCUtill.getConnection();
		 try {
			//������������jdbc�����Զ��ύ
			 conn.setAutoCommit(false);
			 //2.��ӱ�������Ϣ
			 String expenseSql="insert into t_expense(userId,expenseName,"
			 		+ "expenseState,expenseDesc,expenseDate,expenseMoney) values(?,?,?,?,now(),?)";
			 List<Object> params=new ArrayList<Object>();
			 params.add(expense.getUserId());
			 params.add(expense.getExpenseName());
			 params.add(expense.getExpenseState());
			 params.add(expense.getExpenseDesc());
			 params.add(expense.getExpenseMoney());
			 //4.ִ����ӱ�����sql
			 QueryRunner qr=new QueryRunner();
			 int rows=qr.update(conn, expenseSql, params.toArray());
			 //ִ����ӱ�����ϸsql
			 //��ѯ�ղ���ӵı�����id select last_insert_id()��ȡ�����ӵ�������ֵ
			 //��ʾ��״̬��sql��䣺show table status like "tableName"
			 BigInteger expenseId=qr.query(conn, "select last_insert_id()",new ScalarHandler<BigInteger>());
			 Integer[] costIds=expense.getCostIds();
			 String[] detailDescs=expense.getDetailDescs();
			 Double[] detailMoneys=expense.getDetailMoneys();
			//��ӱ��������sql���
			 String detailSql="insert into t_expensedetail(expenseId,costId,detailDesc,"
			 		+ "detailMoney) values(?,?,?,?)";
			 //ִ����ӱ��������sql
			 if(StringUtill.isNotNull(costIds) && costIds.length>0){
				 for (int i = 0; i < costIds.length; i++) {
					rows+=qr.update(conn,detailSql,expenseId,costIds[i],detailDescs[i],detailMoneys[i]);
				}
					 
			 }
			 
			 //�ύ����
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
		// �޸ı�������Ϣ���޸ı�����ϸ
		// ��������
		Connection conn=JDBCUtill.getConnection();
		try {
			conn.setAutoCommit(false);
			//1.sql
			String sql="update t_expense set expenseName=?,expenseState=?,expenseDesc=?,expenseDate=now(),expenseMoney=? where expenseId=?";
			QueryRunner qr=new QueryRunner();
			//sql����
			List<Object> params=new ArrayList<Object>();
			params.add(expense.getExpenseName());
			params.add(expense.getExpenseState());
			params.add(expense.getExpenseDesc());
			params.add(expense.getExpenseMoney());
			params.add(expense.getExpenseId());
			int rows=qr.update(conn, sql, params.toArray());
			//2�޸ı�����ϸ����Ϣ
			//2-1��ִ��ɾ������ԭ�������ı�����ϸ
			String sql1="delete from t_expensedetail where expenseId=?";
			rows+=qr.update(conn,sql1,expense.getExpenseId());
			//2-2 ��ִ��������еı�����ϸ
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
		// ƴ��sql���
		StringBuffer sb=new StringBuffer("select te.*,tu.userName from t_expense te,t_users tu "
				+ "where te.userId=tu.userId");
		//ƴ��sql��ѯ����
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
		//��Ҫ���ݱ�������״̬���в�ѯ
		Integer[] expenseStates=expense.getExpenseStates();
		if(StringUtill.isNotNull(expenseStates) && expenseStates.length>0){
			sb.append(" and expenseState in (");
			String str=Arrays.toString(expenseStates);
			sb.append(str.substring(1, str.length()-1));
				sb.append(")");
			
		}
		//3.ִ��sql��ѯ
		try {
			return JDBCUtill.queryList(sb.toString(), Expense.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
