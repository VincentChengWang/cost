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
import com.nm.expense.service.IAuditService;
import com.nm.expense.service.IExpenseDetailService;
import com.nm.expense.service.IExpenseService;
import com.nm.expense.service.impl.AuditServiceImpl;
import com.nm.expense.service.impl.ExpenseDetailServiceImpl;
import com.nm.expense.service.impl.ExpenseServiceImpl;

@WebServlet("/expense/manageAduitExpense")
public class ExpenseManAuditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收报销单id，根据id查询报销单相关信息
		Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);
		// 2.调用service 进行查询报销单信息、报销单明细、审核历史
		// (1)根据ID查询报销单信息
		IExpenseService expenseService = new ExpenseServiceImpl();
		List<Expense> expenseList = expenseService.queryExpense(expense);
		Expense expenseInfo = expenseList.get(0);
		// (2)根据ID查询报销单明细
		IExpenseDetailService detailService = new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList = detailService.queryExpenseDetail(expenseInfo.getExpenseId());
		//(3)根据报销单id查询审核历史
		IAuditService auditService =new AuditServiceImpl();
		List<AuditRecord> auditList=auditService.queryAuditRecord(expenseInfo.getExpenseId());
		// 返回参数并跳转
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		
		req.getRequestDispatcher("/view/expense/manageraudit/expense_audit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收审核记录参数
		AuditRecord auditRecord=RequestBeanUtils.requestToBean(req, AuditRecord.class);
		//2.调用service添加一条审核记录
		IAuditService auditService=new AuditServiceImpl();
		boolean flag=auditService.addAuditRecord(auditRecord);
		//3.返回
		if(flag){
			req.setAttribute("tip", "审核报销单成功");	
		}else{
			req.setAttribute("tip", "审核报销单失败");
		}
		//(1)查询报销单信息
		Expense expense=new Expense();
		expense.setExpenseId(auditRecord.getExpenseId());
		// 2.调用service 进行查询报销单信息、报销单明细、审核历史
				// (1)根据ID查询报销单信息
				IExpenseService expenseService = new ExpenseServiceImpl();
				List<Expense> expenseList = expenseService.queryExpense(expense);
				Expense expenseInfo = expenseList.get(0);
				// (2)根据ID查询报销单明细
				IExpenseDetailService detailService = new ExpenseDetailServiceImpl();
				List<ExpenseDetail> detailList = detailService.queryExpenseDetail(expenseInfo.getExpenseId());
				//(3)根据报销单id查询审核历史
				List<AuditRecord> auditList=auditService.queryAuditRecord(expenseInfo.getExpenseId());
				// 返回参数并跳转
				req.setAttribute("expenseInfo", expenseInfo);
				req.setAttribute("detailList", detailList);
				req.setAttribute("auditList", auditList);
		
		req.getRequestDispatcher("/view/expense/manageraudit/expense_audit.jsp").forward(req, resp);
	}
	

}
