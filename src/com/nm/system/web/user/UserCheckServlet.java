package com.nm.system.web.user;

import java.io.IOException;
import java.io.PrintWriter;

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
@WebServlet("/system/checkuser")
public class UserCheckServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收参数
		UserInfo user=RequestBeanUtils.requestToBean(req, UserInfo.class);
		
		//2.调用service查询
		IuserService userService=new UserServiceImpl();
		boolean flag=userService.checkUser(user.getUserAccount());
		//3.返回json
		JSONObject obj=new JSONObject();
		
		if(flag){
			obj.put("tip", "该账号已存在");
			obj.put("status", 1);
		}else{
			obj.put("tip", "该账号可用");
			obj.put("status",0);
		}
		PrintWriter out=resp.getWriter();
		out.print(obj);
		out.close();
	}

}
