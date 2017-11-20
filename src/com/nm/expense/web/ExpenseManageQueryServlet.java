package com.nm.expense.web;

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

@WebServlet("/expense/queryManageExpenses")
public class ExpenseManageQueryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收参数
		//Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//注册一个时间转换器
		ConvertUtils.register(new DateConvertUtil(),Date.class);
		//System.out.println("-------");
		//使用requestToSimpleBean方法
		Expense expense=RequestBeanUtils.requestToSimpleBean(req, Expense.class);
		expense.setExpenseStates(new Integer[]{1,2,-1});
		//2.调用service查询
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> list=expenseService.queryExpense(expense);
		//3.返回数据并跳转页面
		req.setAttribute("expense", expense);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/expense/manageraudit/expense_managerlist.jsp").forward(req, resp);
	}

}
