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
		//jsp请求-->servlet-->调用service-->调用dao
		//接收参数
		//UserInfo user=new UserInfo();
		//user.setUserName(req.getParameter("userName"));
		//user.setUserAge(Integer.valueOf(req.getParameter("userAge")));
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//System.out.println(user);
		//2.调用service业务逻辑层
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.addUser(user);
		//返回并跳转页面
		if(flag){
			req.setAttribute("tip", "添加用户成功");
		}else{
			req.setAttribute("tip", "添加用户失败");
		}
		//将添加后的user信息显示到页面上
		req.setAttribute("user", user);
		req.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(req, resp);
	}

}
