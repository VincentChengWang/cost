package com.nm.expense.service;

import java.util.List;

import com.nm.bean.Expense;

public interface IExpenseService {
	boolean addExpense(Expense expense);
	boolean updateExpense(Expense expense);
	boolean deleteExpense(Expense expense);
	List<Expense> queryExpense(Expense expense);
}
