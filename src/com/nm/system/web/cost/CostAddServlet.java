package com.nm.system.web.cost;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Cost;
import com.nm.system.dao.impl.CostDaoImpl;
import com.nm.system.service.ICostService;
import com.nm.system.service.impl.CostServiceImpl;

@WebServlet("/system/addCost")
public class CostAddServlet extends HttpServlet{

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
		//2.调用service添加
		ICostService costService=new CostServiceImpl();
		boolean flag=costService.addCost(cost);
		//返回并跳转
		if(flag){
			req.setAttribute("tip", "添加费用成功");
		}else{
			req.setAttribute("tip", "添加费用失败");
		}
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(req, resp);
	}

}
