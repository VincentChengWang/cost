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
		// 1.���շ��ñ�ţ���ת���޸�ҳ��
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.����costId��ѯ������Ϣ
		ICostService costService=new CostServiceImpl();
		List<Cost> list=costService.queryCost(cost);
		Cost costInfo=list.get(0);
		//3.����cost��Ϣ����תҳ��
		req.setAttribute("cost", costInfo);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//1.�����޸ĵ�cost��Ϣ
		Cost cost=RequestBeanUtils.requestToBean(req, Cost.class);
		//2.����service�����޸�
		ICostService costService=new CostServiceImpl();
		boolean flag=costService.updateCost(cost);
		//3.�����޸ĺ����Ϣ������תҳ��
		if(flag){
			req.setAttribute("tip", "�޸ķ�����Ϣ�ɹ�");
		}else{
			req.setAttribute("tip", "�޸ķ�����Ϣʧ��");
		}
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(req, resp);
		
	}

}
