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
		//1.���ղ���
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.����service���
		ICostService costService=new CostServiceImpl();
		boolean flag=costService.addCost(cost);
		//���ز���ת
		if(flag){
			req.setAttribute("tip", "��ӷ��óɹ�");
		}else{
			req.setAttribute("tip", "��ӷ���ʧ��");
		}
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(req, resp);
	}

}
