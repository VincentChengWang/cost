package com.nm.expense.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.AuditRecord;
import com.nm.bean.Expense;
import com.nm.bean.ExpenseDetail;
import com.nm.expense.dao.impl.ExpenseDetailDaoImpl;
import com.nm.expense.service.IAuditService;
import com.nm.expense.service.IExpenseDetailService;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.AuditServiceImpl;
import com.nm.expense.service.impl.ExpenseDetailServiceImpl;
import com.nm.expense.service.impl.ExpenseServiceImpl;

@WebServlet("/expense/showExpense")
public class ExpenseShowServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收参数 接收报销单编号 ，根据编号去查询报销单信息、报销单明细、审核历史
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2-1调用service
		//查询报销单信息
		IExpenseService expenseService=new ExpenseServiceImpl();
		 List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//2-2 查询报销单明细
		IExpenseDetailService expenseDetail=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=expenseDetail.queryExpenseDetail(expenseInfo.getExpenseId());
		//2-3查询报销单审核历史
		IAuditService auditService =new AuditServiceImpl();
		List<AuditRecord> auditList=auditService.queryAuditRecord(expenseInfo.getExpenseId());
		
		//3.返回页面
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		req.getRequestDispatcher("/view/expense/expense/expense_show.jsp").forward(req, resp);
	}

}
