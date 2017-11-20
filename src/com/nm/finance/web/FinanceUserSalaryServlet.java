package com.nm.finance.web;

import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

@WebServlet("/finance/queryUserSalary")
public class FinanceUserSalaryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收用户id，查询用户薪资
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		//2.根据用户id调用service查询用户信息
		IuserService userService=new UserServiceImpl();
		List<UserInfo> userList=userService.queryUsers(user);
		UserInfo userInfo=userList.get(0);
		//3.返回json
		PrintWriter out=resp.getWriter();
		JSONObject obj=new JSONObject();
		obj.put("userSalary", userInfo.getUserSalary());
		out.print(obj);
		//out.print(JSONObject.fromObject(userInfo));
		out.close();
	}

}
