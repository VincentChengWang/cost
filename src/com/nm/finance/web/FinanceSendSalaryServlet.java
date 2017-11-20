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
import com.nm.bean.UserInfo;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.system.service.IuserService;
import com.nm.system.service.impl.UserServiceImpl;
import com.nm.utils.DateConvertUtil;

@WebServlet("/finance/sendSalary")
public class FinanceSendSalaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询所有用户，用于财务选择发放人
		IuserService userService=new UserServiceImpl();
		List<UserInfo> userList=userService.queryUsers(new UserInfo());
		req.setAttribute("userList",userList);
	
		// 跳转到薪资发放页面
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收薪资对象参数
		ConvertUtils.register(new DateConvertUtil("yyyy-MM"), Date.class);
		Salary salary=RequestBeanUtils.requestToSimpleBean(req, Salary.class);
		//2.调用service添加薪资记录
		ISalaryService salaryService=new SalaryServiceImpl();
		boolean flag=salaryService.addSalary(salary);
		//3.返回并跳转页面
		if(flag){
			req.setAttribute("tip", "发放工资成功");
		}else{
			req.setAttribute("tip", "发放工资失败");
		}
		//返回时需要再次查询
		IuserService userService=new UserServiceImpl();
		List<UserInfo> userList=userService.queryUsers(new UserInfo());
		req.setAttribute("userList",userList);
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

}
