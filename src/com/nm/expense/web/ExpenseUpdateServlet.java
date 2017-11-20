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
import com.nm.bean.ExpenseDetail;
import com.nm.expense.service.IExpenseDetailService;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.ExpenseDetailServiceImpl;
import com.nm.expense.service.impl.ExpenseServiceImpl;
import com.nm.system.service.ICostService;
import com.nm.system.service.impl.CostServiceImpl;

@WebServlet("/expense/updateExpense")
public class ExpenseUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收报销单id，根据报销单查询报销单信息，费用信息以及报销明细
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2-1根据报销单id查询报销单信息
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//2-2 调用service查询费用信息
		ICostService costService=new CostServiceImpl();
		List<Cost> costList=costService.queryCost(new Cost());
		//2-3根据报销单id查询明细
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expenseInfo.getExpenseId());
		//3.返回参数并跳转
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("costList", costList);
		req.setAttribute("detailList", detailList);
		req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收报销单信息及费用明细
			Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
			//System.out.println(expense);
		//2.调用service执行修改报销单信息以及报销明细信息
			IExpenseService expenseService=new ExpenseServiceImpl();
			boolean flag=expenseService.updateExpense(expense);
		//3.返回参数并跳转页面
			if(flag){
				req.setAttribute("tip", "修改报销单成功");
			}else{
				req.setAttribute("tip","修改报销单失败");
			}
			//3-1 根据报销单id查询报销单信息
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//3-2查询费用信息
		ICostService costService=new CostServiceImpl();
		List<Cost> costList=costService.queryCost(new Cost());
		//3-3查询明细费用
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
			req.setAttribute("expenseInfo", expenseInfo);
			req.setAttribute("costList", costList);
			req.setAttribute("detailList", detailList);
			req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
	}

}
