package com.nm.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.nm.bean.UserInfo;

@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//1.登录过的用户不用再次登录
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpSession session=req.getSession();
		//2.获取session里面保存的用户信息
		UserInfo user=(UserInfo) session.getAttribute("userinfo");
		//获取用户请求的地址信息
		String url=req.getRequestURI();
		if(user!=null){
			if(url.contains("/login.jsp")){
				session.invalidate();
			}
			arg2.doFilter(req, arg1);//用户已经登录，直接放行
		}else if(url.contains("/login"))
			arg2.doFilter(req, arg1);//允许用户执行登录操作
		else if(url.contains("/login.jsp"))
			arg2.doFilter(req, arg1);//允许用户访问登录页面
		else if(url.contains("/resource"))
			arg2.doFilter(req, arg1);//允许用户访问资源文件
		 else{
			req.setAttribute("tip", "请输入用户名密码");
			//清空session
			session.invalidate();
			req.getRequestDispatcher("/view/login.jsp").forward(req, arg1);;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
