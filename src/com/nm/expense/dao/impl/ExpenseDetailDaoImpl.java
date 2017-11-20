package com.nm.expense.dao.impl;

import java.sql.SQLException;
import java.util.List;

import com.nm.bean.ExpenseDetail;
import com.nm.expense.dao.IExpenseDetailDao;
import com.nm.expense.service.IExpenseDetailService;
import com.nm.utils.JDBCUtill;

public class ExpenseDetailDaoImpl implements IExpenseDetailDao{

	@Override
	public List<ExpenseDetail> queryExpenseDetail(int expenseId) {
		// 1.sql”Ôæ‰
		String sql="select te.*,tc.costName from t_expensedetail te,"
				+ "t_cost tc where te.costId=tc.costId and te.expenseId=?";
		//2.÷¥––sql
		try {
			return JDBCUtill.queryList(sql, ExpenseDetail.class, expenseId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	

}
