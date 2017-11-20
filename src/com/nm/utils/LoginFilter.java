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
		//1.��¼�����û������ٴε�¼
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpSession session=req.getSession();
		//2.��ȡsession���汣����û���Ϣ
		UserInfo user=(UserInfo) session.getAttribute("userinfo");
		//��ȡ�û�����ĵ�ַ��Ϣ
		String url=req.getRequestURI();
		if(user!=null){
			if(url.contains("/login.jsp")){
				session.invalidate();
			}
			arg2.doFilter(req, arg1);//�û��Ѿ���¼��ֱ�ӷ���
		}else if(url.contains("/login"))
			arg2.doFilter(req, arg1);//�����û�ִ�е�¼����
		else if(url.contains("/login.jsp"))
			arg2.doFilter(req, arg1);//�����û����ʵ�¼ҳ��
		else if(url.contains("/resource"))
			arg2.doFilter(req, arg1);//�����û�������Դ�ļ�
		 else{
			req.setAttribute("tip", "�������û�������");
			//���session
			session.invalidate();
			req.getRequestDispatcher("/view/login.jsp").forward(req, arg1);;
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
