package com.nm.expense.service.impl;

import java.util.List;

import com.nm.bean.ExpenseDetail;
import com.nm.expense.dao.IExpenseDetailDao;
import com.nm.expense.dao.impl.ExpenseDetailDaoImpl;
import com.nm.expense.service.IExpenseDetailService;

public class ExpenseDetailServiceImpl implements IExpenseDetailService{
IExpenseDetailDao detailDao=new ExpenseDetailDaoImpl();
	@Override
	public List<ExpenseDetail> queryExpenseDetail(int expenseId) {
		// TODO Auto-generated method stub
		return detailDao.queryExpenseDetail(expenseId);
	}

}
