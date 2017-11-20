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

@WebServlet("/system/deleteUsers")
public class UserDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//接收参数，接收多个要删除的用户id
		UserInfo user =RequestBeanUtils.requestToBean(req, UserInfo.class);
		//调用service
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.deleteUser(user);
		//3.返回
		
			req.getRequestDispatcher("/system/queryUsers").forward(req, resp);
		
	}

}
