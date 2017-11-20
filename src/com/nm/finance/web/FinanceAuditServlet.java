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
		// 1.���ձ�����id,��ѯ��������Ϣ����������ϸ����˼�¼
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2-1.���ݱ�����id��ѯ��������Ϣ
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//2-2���ݱ�����id��ѯ������ϸ
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
		//2-3���ݱ�����id��ѯ��˼�¼
		IAuditService auditService=new AuditServiceImpl();
		List<AuditRecord> auditList=auditService.queryAuditRecord(expense.getExpenseId());
		//3.���ؽ������ת
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		req.getRequestDispatcher("/view/finance/financeaudit/financeaudit_audit.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.���ձ�������˶���Ĳ���
		AuditRecord auditRecord=RequestBeanUtils.requestToBean(req, AuditRecord.class);
		//2.����service��ӱ�������˼�¼
		IAuditService auditService=new AuditServiceImpl();
		boolean flag=auditService.addAuditRecord(auditRecord);
		//3.���ز�������תҳ��
		if(flag){
			req.setAttribute("tip", "������˳ɹ�");
		}else{
			req.setAttribute("tip", "�������ʧ��");
		}
	//���ص���Ϣ��Ҫ���²�ѯ����������������ϸ����˼�¼
		//3-1����id��ѯ������
		Expense expense=new Expense();
		expense.setExpenseId(auditRecord.getExpenseId());
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//3-2���ݱ�����id��ѯ��������ϸ
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
		//3-3���ݱ�����id��ѯ��˼�¼
		List<AuditRecord> auditList=auditService.queryAuditRecord(expense.getExpenseId());
		//���ز�������ת
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("detailList", detailList);
		req.setAttribute("auditList", auditList);
		req.getRequestDispatcher("/view/finance/financeaudit/financeaudit_audit.jsp").forward(req, resp);
	}

}
