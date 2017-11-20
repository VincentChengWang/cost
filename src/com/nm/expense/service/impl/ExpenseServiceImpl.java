package com.nm.expense.service.impl;

import java.util.List;

import com.nm.bean.Expense;
import com.nm.expense.dao.IExpenseDao;
import com.nm.expense.dao.impl.ExpenseDaoImpl;
import com.nm.expense.service.IExpenseService;

public class ExpenseServiceImpl implements IExpenseService{
	IExpenseDao expenseDao=new ExpenseDaoImpl();
	@Override
	public boolean addExpense(Expense expense) {
		// TODO Auto-generated method stub
		//����daoִ����ӱ������ͱ�����ϸ
		int rows=expenseDao.addExpense(expense);
		if(rows>0)
			return true;
		return false;
	}

	@Override
	public boolean updateExpense(Expense expense) {
		// service ����dao���޸ı�����
		int row=expenseDao.updateExpense(expense);
		if(row>0)
			return true;
		return false;
	
	}
	@Override
	public boolean deleteExpense(Expense expense) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Expense> queryExpense(Expense expense) {
		// TODO Auto-generated method stub
		return expenseDao.queryExpense(expense);
	}

}
