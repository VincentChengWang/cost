package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Cost;
import com.nm.bean.Expense;
import com.nm.expense.dao.impl.ExpenseDaoImpl;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.system.service.ICostService;
import com.nm.system.service.impl.CostServiceImpl;

@WebServlet("/expense/inputExpense")
public class ExpenseInputServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1.跳转到填写报销单界面
		//2.添加报销单信息需要选择费用明细，查询费用信息
		ICostService costService=new CostServiceImpl();
		List<Cost> list=costService.queryCost(new Cost());
		//3.返回费用列表信息
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收新添加报销单信息
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2.调用service 添加报销单及报销明细
		IExpenseService expenseService=new ExpenseServiceImpl();
		boolean flag=expenseService.addExpense(expense);
		//System.out.println(flag);
		//3.返回数据并跳转
		if(flag){
			req.setAttribute("tip", "添加报销单成功");
		}else{
			req.setAttribute("tip", "添加报销单失败");
		}
		req.setAttribute("expense", expense);
		//添加报销单信息需要选择费用明细，查询费用信息
		ICostService costService =new CostServiceImpl();
		List<Cost> list=costService.queryCost(new Cost()); 
		//返回费用列表信息
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}

}
