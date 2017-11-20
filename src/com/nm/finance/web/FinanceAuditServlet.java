package com.nm.finance.web;

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

@WebServlet("/finance/financeAudit")
public class FinanceAuditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收报销单id,查询报销单信息、报销单明细、审核记录
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2-1.根据报销单id查询报销单信息
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//2-2根据报销单id查询报销明细
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
		//2-3根据报销单id查询审核记录
		IAuditService auditService=new AuditServiceImpl();
		List<AuditRecord> auditList=auditService.queryAuditRecord(expense.getExpenseId());
		//3.返回结果并跳转
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		req.getRequestDispatcher("/view/finance/financeaudit/financeaudit_audit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收报销单审核对象的参数
		AuditRecord auditRecord=RequestBeanUtils.requestToBean(req, AuditRecord.class);
		//2.调用service添加报销单审核记录
		IAuditService auditService=new AuditServiceImpl();
		boolean flag=auditService.addAuditRecord(auditRecord);
		//3.返回参数并跳转页面
		if(flag){
			req.setAttribute("tip", "财务审核成功");
		}else{
			req.setAttribute("tip", "财务审核失败");
		}
	//返回的信息需要重新查询报销单、报销单明细、审核记录
		//3-1根据id查询报销单
		Expense expense=new Expense();
		expense.setExpenseId(auditRecord.getExpenseId());
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//3-2根据报销单id查询报销单明细
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
		//3-3根据报销单id查询审核记录
		List<AuditRecord> auditList=auditService.queryAuditRecord(expense.getExpenseId());
		//返回参数并跳转
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		req.getRequestDispatcher("/view/finance/financeaudit/financeaudit_audit.jsp").forward(req, resp);
	}

}
