package com.nm.finance.web;

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
import com.nm.bean.Salary;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.utils.DateConvertUtil;

@WebServlet("/finance/querySalary")
public class FinanceQuerySalaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收参数
		ConvertUtils.register(new DateConvertUtil("yyyy-MM"),Date.class );
		Salary salary=RequestBeanUtils.requestToSimpleBean(req,Salary.class);
		//2.调用service查询
		ISalaryService salaryService=new SalaryServiceImpl();
		List<Salary> salaryList=salaryService.querySalary(salary);
		//返回并跳转
		req.setAttribute("salary", salary);
		req.setAttribute("salaryList", salaryList);
		req.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(req, resp);
	}

}
