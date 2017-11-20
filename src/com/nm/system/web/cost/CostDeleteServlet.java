package com.nm.system.web.cost;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Cost;
import com.nm.system.service.ICostService;
import com.nm.system.service.impl.CostServiceImpl;

@WebServlet("/system/deleteCosts")
public class CostDeleteServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//1.接收要删除的costId参数数组
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.调用service删除cost
		ICostService costService=new CostServiceImpl();
		boolean flag=costService.delCost(cost);
		//3.删除完成后重新执行查询
		req.getRequestDispatcher("/system/queryCosts").forward(req, resp);
	}

}
