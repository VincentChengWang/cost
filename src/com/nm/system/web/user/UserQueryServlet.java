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
/**
 * 用户管理
 * @author cheng
 *
 */
@WebServlet("/system/queryUsers")
public class UserQueryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收参数，根据用户名或者用户编号查询
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//2.调用service
		IuserService userService=new UserServiceImpl();
		List<UserInfo> list=userService.queryUsers(user);
		//3.返回数据并跳转页面
		req.setAttribute("list", list);
		req.setAttribute("user", user);//返回查询条件
		req.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(req, resp);
	}

}
