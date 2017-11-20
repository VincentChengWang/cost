package com.nm.expense.dao;

import java.util.List;

import com.nm.bean.Expense;

public interface IExpenseDao {
	int addExpense(Expense expense);
	int updateExpense(Expense expense);
	int deleteExpense(Expense expense);
	List<Expense> queryExpense(Expense expense);
}
