package com.nm.system.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.UserInfo;
import com.nm.system.service.IuserService;
import com.nm.system.service.impl.UserServiceImpl;
@WebServlet("/system/adduser")
public class UserAddServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//jsp����-->servlet-->����service-->����dao
		//���ղ���
		//UserInfo user=new UserInfo();
		//user.setUserName(req.getParameter("userName"));
		//user.setUserAge(Integer.valueOf(req.getParameter("userAge")));
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//System.out.println(user);
		//2.����serviceҵ���߼���
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.addUser(user);
		//���ز���תҳ��
		if(flag){
			req.setAttribute("tip", "����û��ɹ�");
		}else{
			req.setAttribute("tip", "����û�ʧ��");
		}
		//����Ӻ��user��Ϣ��ʾ��ҳ����
		req.setAttribute("user", user);
		req.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(req, resp);
	}

}
