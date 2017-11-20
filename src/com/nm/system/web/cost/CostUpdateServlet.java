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

@WebServlet("/system/updateCost")
public class CostUpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.接收费用编号，跳转到修改页面
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.根据costId查询费用信息
		ICostService costService=new CostServiceImpl();
		List<Cost> list=costService.queryCost(cost);
		Cost costInfo=list.get(0);
		//3.返回cost信息并跳转页面
		req.setAttribute("cost", costInfo);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.接收修改的cost信息
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.调用service进行修改
		ICostService costService=new CostServiceImpl();
		boolean flag=costService.updateCost(cost);
		//3.返回修改后的信息，并跳转页面
		if(flag){
			req.setAttribute("tip", "修改费用信息成功");
		}else{
			req.setAttribute("tip", "修改费用信息失败");
		}
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
		
	}

}
