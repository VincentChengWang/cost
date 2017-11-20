package com.nm.expense.dao;

import java.util.List;

import com.nm.bean.ExpenseDetail;

public interface IExpenseDetailDao {
List<ExpenseDetail> queryExpenseDetail( int expenseId);
}
