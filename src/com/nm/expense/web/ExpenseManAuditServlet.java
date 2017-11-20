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
		// 1.���ձ�����id������id��ѯ�����������Ϣ
		Expense expense = RequestBeanUtils.requestToBean(req, Expense.class);
		// 2.����service ���в�ѯ��������Ϣ����������ϸ�������ʷ
		// (1)����ID��ѯ��������Ϣ
		IExpenseService expenseService = new ExpenseServiceImpl();
		List<Expense> expenseList = expenseService.queryExpense(expense);
		Expense expenseInfo = expenseList.get(0);
		// (2)����ID��ѯ��������ϸ
		IExpenseDetailService detailService = new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList = detailService.queryExpenseDetail(expenseInfo.getExpenseId());
		//(3)���ݱ�����id��ѯ�����ʷ
		IAuditService auditService =new AuditServiceImpl();
		List<AuditRecord> auditList=auditService.queryAuditRecord(expenseInfo.getExpenseId());
		// ���ز�������ת
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		
		req.getRequestDispatcher("/view/expense/manageraudit/expense_audit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.������˼�¼����
		AuditRecord auditRecord=RequestBeanUtils.requestToBean(req, AuditRecord.class);
		//2.����service���һ����˼�¼
		IAuditService auditService=new AuditServiceImpl();
		boolean flag=auditService.addAuditRecord(auditRecord);
		//3.����
		if(flag){
			req.setAttribute("tip", "��˱������ɹ�");	
		}else{
			req.setAttribute("tip", "��˱�����ʧ��");
		}
		//(1)��ѯ��������Ϣ
		Expense expense=new Expense();
		expense.setExpenseId(auditRecord.getExpenseId());
		// 2.����service ���в�ѯ��������Ϣ����������ϸ�������ʷ
				// (1)����ID��ѯ��������Ϣ
				IExpenseService expenseService = new ExpenseServiceImpl();
				List<Expense> expenseList = expenseService.queryExpense(expense);
				Expense expenseInfo = expenseList.get(0);
				// (2)����ID��ѯ��������ϸ
				IExpenseDetailService detailService = new ExpenseDetailServiceImpl();
				List<ExpenseDetail> detailList = detailService.queryExpenseDetail(expenseInfo.getExpenseId());
				//(3)���ݱ�����id��ѯ�����ʷ
				List<AuditRecord> auditList=auditService.queryAuditRecord(expenseInfo.getExpenseId());
				// ���ز�������ת
				req.setAttribute("expenseInfo", expenseInfo);
				req.setAttribute("detailList", detailList);
				req.setAttribute("auditList", auditList);
		
		req.getRequestDispatcher("/view/expense/manageraudit/expense_audit.jsp").forward(req, resp);
	}
	

}
