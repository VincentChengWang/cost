package com.nm.system.web.cost;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Cost;
import com.nm.system.service.ICostService;
import com.nm.system.service.impl.CostServiceImpl;

@WebServlet("/system/queryCosts")
public class CostQueryServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收参数
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.调用service
		ICostService costService=new CostServiceImpl();
		List<Cost> list=costService.queryCost(cost);
		System.out.println(list);
		//3.返回并跳转
		req.setAttribute("cost", cost);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(req, resp);
	}

}
