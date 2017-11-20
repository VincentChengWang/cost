package com.nm.expense.service;

import java.util.List;

import com.nm.bean.ExpenseDetail;

public interface IExpenseDetailService {
	List<ExpenseDetail> queryExpenseDetail(int expenseId);
}
