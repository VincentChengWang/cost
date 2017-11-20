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
		
		// 1.��ת����д����������
		//2.��ӱ�������Ϣ��Ҫѡ�������ϸ����ѯ������Ϣ
		ICostService costService=new CostServiceImpl();
		List<Cost> list=costService.queryCost(new Cost());
		//3.���ط����б���Ϣ
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.��������ӱ�������Ϣ
		Expense expense=RequestBeanUtils.requestToBean(req, Expense.class);
		//2.����service ��ӱ�������������ϸ
		IExpenseService expenseService=new ExpenseServiceImpl();
		boolean flag=expenseService.addExpense(expense);
		//System.out.println(flag);
		//3.�������ݲ���ת
		if(flag){
			req.setAttribute("tip", "��ӱ������ɹ�");
		}else{
			req.setAttribute("tip", "��ӱ�����ʧ��");
		}
		req.setAttribute("expense", expense);
		//��ӱ�������Ϣ��Ҫѡ�������ϸ����ѯ������Ϣ
		ICostService costService =new CostServiceImpl();
		List<Cost> list=costService.queryCost(new Cost()); 
		//���ط����б���Ϣ
		req.setAttribute("costs", list);
		req.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(req, resp);
	}

}
