package com.nm.expense.web;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Expense;
import com.nm.bean.UserInfo;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.utils.DateConvertUtil;

@WebServlet("/expense/queryMyExpense")
public class MyExpenseQueryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收我的报销查询参数
		ConvertUtils.register(new DateConvertUtil(), Date.class);
		Expense expense=RequestBeanUtils.requestToSimpleBean(req, Expense.class);
		HttpSession session=req.getSession();
		UserInfo user=(UserInfo) session.getAttribute("userinfo");
		//将session对象里面的用户id设置给报销单
		expense.setUserId(user.getUserId());
		//2.调用service
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		//3.返回并跳转
		req.setAttribute("expense", expense);
		req.setAttribute("expenseList", expenseList);
		req.getRequestDispatcher("/view/expense/expense/expense_mylist.jsp").forward(req, resp);
	}

}
