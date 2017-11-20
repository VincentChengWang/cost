package com.nm.system.web.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.UserInfo;
import com.nm.system.service.IuserService;
import com.nm.system.service.impl.UserServiceImpl;

@WebServlet("/system/updateUser")
public class UserUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.���ղ������û�id
		UserInfo user =RequestBeanUtils.requestToBean(req, UserInfo.class);
		//2.����serviceִ�в�ѯ
		IuserService userService=new UserServiceImpl();
		List<UserInfo> list=userService.queryUsers(user);
		UserInfo userinfo=list.get(0);
		//3.����user��Ϣ����תҳ��
		req.setAttribute("user", userinfo);
		req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//���ղ�����
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//����service
		IuserService userService=new UserServiceImpl();
		boolean flag= userService.updateUser(user);
		//3.���ز���ת
		if(flag){
			req.setAttribute("tip", "�޸��û��ɹ�");
		}else{
			req.setAttribute("tip", "�޸��û�ʧ��");
		}
		req.setAttribute("user", user);
		req.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(req, resp);
	}

}
