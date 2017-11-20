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
		// 1.���ձ�����id�����ݱ�������ѯ��������Ϣ��������Ϣ�Լ�������ϸ
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2-1���ݱ�����id��ѯ��������Ϣ
		IExpenseService expenseService=new ExpenseServiceImpl();
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//2-2 ����service��ѯ������Ϣ
		ICostService costService=new CostServiceImpl();
		List<Cost> costList=costService.queryCost(new Cost());
		//2-3���ݱ�����id��ѯ��ϸ
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expenseInfo.getExpenseId());
		//3.���ز�������ת
		req.setAttribute("expenseInfo", expenseInfo);
		req.setAttribute("costList", costList);
		req.setAttribute("detailList", detailList);
		req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.���ձ�������Ϣ��������ϸ
			Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
			//System.out.println(expense);
		//2.����serviceִ���޸ı�������Ϣ�Լ�������ϸ��Ϣ
			IExpenseService expenseService=new ExpenseServiceImpl();
			boolean flag=expenseService.updateExpense(expense);
		//3.���ز�������תҳ��
			if(flag){
				req.setAttribute("tip", "�޸ı������ɹ�");
			}else{
				req.setAttribute("tip","�޸ı�����ʧ��");
			}
			//3-1 ���ݱ�����id��ѯ��������Ϣ
		List<Expense> expenseList=expenseService.queryExpense(expense);
		Expense expenseInfo=expenseList.get(0);
		//3-2��ѯ������Ϣ
		ICostService costService=new CostServiceImpl();
		List<Cost> costList=costService.queryCost(new Cost());
		//3-3��ѯ��ϸ����
		IExpenseDetailService detailService=new ExpenseDetailServiceImpl();
		List<ExpenseDetail> detailList=detailService.queryExpenseDetail(expense.getExpenseId());
			req.setAttribute("expenseInfo", expenseInfo);
			req.setAttribute("costList", costList);
			req.setAttribute("detailList", detailList);
			req.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(req, resp);
	}

}
