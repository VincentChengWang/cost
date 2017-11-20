package com.nm.expense.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.nm.bean.AuditRecord;
import com.nm.expense.dao.IAuditDao;
import com.nm.utils.JDBCUtill;

public class AuditDaoImpl implements IAuditDao{

	@Override
	public List<AuditRecord> queryAuditRecord(int expenseId) {
		// 1.sql���
		String sql="select ta.*,tu.userName from t_auditrecord ta,"
				+ "t_users tu where ta.userId=tu.userId and ta.expenseId=?";
		//2.ִ��sql���
		try {
			return JDBCUtill.queryList(sql, AuditRecord.class, expenseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addAuditRecord(AuditRecord auditRecord) {
		// 1.���һ����˼�¼���޸ı�������״̬
		Connection conn=JDBCUtill.getConnection();
		try {
			conn.setAutoCommit(false);
			//1.�����˼�¼
			String sql="insert into t_auditrecord(userId,expenseId,auditDate,auditState,auditSugg) "
					+ "value(?,?,now(),?,?)";
			QueryRunner qr=new QueryRunner();
			//2.ִ����������ʷsql
			int rows=qr.update(conn, sql,auditRecord.getUserId(),auditRecord.getExpenseId(),
					auditRecord.getAuditState(),auditRecord.getAuditSugg());
			//3.�޸ı�����״̬
			String sql2="update t_expense set expenseState=? where expenseId=?";
			//4.ִ���޸ı�����״̬sql
			rows+=qr.update(conn, sql2, auditRecord.getAuditState(),auditRecord.getExpenseId());
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

}
