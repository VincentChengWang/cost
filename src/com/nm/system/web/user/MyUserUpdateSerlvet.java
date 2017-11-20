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
		//1.接收要修改的用户参数
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//2.调用service执行修改用户
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.updateUser(user);
		//3.返回页面用户参数并跳转
		//修改用户后，需同时同步修改session中的userinfo对象
		if(flag){
		HttpSession session=req.getSession();
		session.setAttribute("userinfo", user);
		req.setAttribute("tip", "修改个人信息成功");
		}else{
			req.setAttribute("tip", "修改个人信息失败");
		}
		req.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(req, resp);
	}

}
