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
		//��ѯ�����û������ڲ���ѡ�񷢷���
		IuserService userService=new UserServiceImpl();
		List<UserInfo> userList=userService.queryUsers(new UserInfo());
		req.setAttribute("userList",userList);
	
		// ��ת��н�ʷ���ҳ��
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.����н�ʶ������
		ConvertUtils.register(new DateConvertUtil("yyyy-MM"), Date.class);
		Salary salary=RequestBeanUtils.requestToSimpleBean(req, Salary.class);
		//2.����service���н�ʼ�¼
		ISalaryService salaryService=new SalaryServiceImpl();
		boolean flag=salaryService.addSalary(salary);
		//3.���ز���תҳ��
		if(flag){
			req.setAttribute("tip", "���Ź��ʳɹ�");
		}else{
			req.setAttribute("tip", "���Ź���ʧ��");
		}
		//����ʱ��Ҫ�ٴβ�ѯ
		IuserService userService=new UserServiceImpl();
		List<UserInfo> userList=userService.queryUsers(new UserInfo());
		req.setAttribute("userList",userList);
		req.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(req, resp);
	}

}
