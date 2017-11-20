package com.nm.finance.web;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Expense;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.utils.DateConvertUtil;

@WebServlet("/finance/queryExpense")
public class FinanceQueryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收参数，查询财务可以审核的报销单
		ConvertUtils.register(new DateConvertUtil(), Date.class);
		Expense expense=RequestBeanUtils.requestToSimpleBean(req, Expense.class);
		expense.setExpenseStates(new Integer[]{2,3,-2});
		//2.调用service进行查询报销单
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		//3.返回参数并跳转页面
		req.setAttribute("expense",expense);
		req.setAttribute("expenseList", expenseList);
		req.getRequestDispatcher("/view/finance/financeaudit/financeaudit_list.jsp").forward(req, resp);
	}

}
