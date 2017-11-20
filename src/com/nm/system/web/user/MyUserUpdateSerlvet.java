package com.nm.system.web.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.UserInfo;
import com.nm.system.service.IuserService;
import com.nm.system.service.impl.UserServiceImpl;

@WebServlet("/system/updateMyUserInfo")
public class MyUserUpdateSerlvet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.����Ҫ�޸ĵ��û�����
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//2.����serviceִ���޸��û�
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.updateUser(user);
		//3.����ҳ���û���������ת
		//�޸��û�����ͬʱͬ���޸�session�е�userinfo����
		if(flag){
		HttpSession session=req.getSession();
		session.setAttribute("userinfo", user);
		req.setAttribute("tip", "�޸ĸ�����Ϣ�ɹ�");
		}else{
			req.setAttribute("tip", "�޸ĸ�����Ϣʧ��");
		}
		req.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(req, resp);
	}

}
