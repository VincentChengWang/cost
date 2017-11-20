package com.nm.system.web.user;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.ConvertUtils;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Salary;
import com.nm.bean.UserInfo;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.utils.DateConvertUtil;

@WebServlet("/system/queryMySalary")
public class MySalaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.查询我的薪资
		ConvertUtils.register(new DateConvertUtil("yyyy-MM"), Date.class);
		Salary salary=RequestBeanUtils.requestToSimpleBean(req, Salary.class);
		HttpSession session=req.getSession();
		UserInfo user=(UserInfo) session.getAttribute("userinfo");
		salary.setUserId(user.getUserId());
		//2。调用service查询
		ISalaryService salaryService=new SalaryServiceImpl();
		List<Salary> salaryList=salaryService.querySalary(salary);
		//3.返回参数并跳转页面
		req.setAttribute("salary", salary);
		req.setAttribute("salaryList", salaryList);
		req.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(req, resp);
	}

}
