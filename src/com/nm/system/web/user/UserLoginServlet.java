package com.nm.system.web.user;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.bean.Menu;
import com.nm.bean.SalaryChart;
import com.nm.bean.UserInfo;
import com.nm.finance.service.ISalaryService;
import com.nm.finance.service.impl.SalaryServiceImpl;
import com.nm.system.service.IuserService;
import com.nm.system.service.impl.UserServiceImpl;

@WebServlet("/system/login")
public class UserLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//�ж��û��Ƿ��¼
		HttpSession session=req.getSession();
		//��ȡsession���汣����û���Ϣ
		UserInfo userinfo=(UserInfo) session.getAttribute("userinfo");
		//����û��Ѿ���¼����ֱ����ת����ҳ
		if(userinfo!=null){
			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
			return;
		}
		// 1.����û�û�е�¼��������û���¼��Ϣ
		UserInfo user = RequestBeanUtils.requestToBean(req, UserInfo.class);
		// 2.����service
		IuserService userService = new UserServiceImpl();
		 UserInfo loginUser=userService.doLogin(user);
		
		 //���ز���ת
		if(loginUser==null){
			req.setAttribute("tip", "�û������������");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}else{
			//��¼�ɹ�
			session.setAttribute("userinfo", loginUser);
			//��ȡ�û�Ȩ��
			List<Menu> menus=null;
			if(loginUser.getUserRole().equals("��������Ա"))
				menus=getAllMenus();
			else if(loginUser.getUserRole().equals("���ž���"))
				menus=getManagerMenus();
			else if(loginUser.getUserRole().equals("������Ա"))
				menus=getFinanceMenus();
			else 
				menus=getNormalMenus();
			//��ѯн�ʱ�����Ϣ
			ISalaryService salaryService=new SalaryServiceImpl();
			List<SalaryChart> salaryCharts=salaryService.querySalaryCharts();
			
			session.setAttribute("salaryCharts", salaryCharts);
			session.setAttribute("menus", menus);
			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
		}
		
	}
	//����Ȩ��
	public List<Menu> getAllMenus(){
		List<Menu> menus=new ArrayList<>();
		//���һ���˵�
		menus.add(new Menu(1, "ϵͳ����", 0, ""));
		menus.add(new Menu(2, "��������", 0, ""));
		menus.add(new Menu(3, "�������", 0, ""));
		//���ϵͳ��������˵�
		menus.add(new Menu(4, "�û�����", 1, "system/queryUsers"));
		menus.add(new Menu(5, "���ù���", 1, "system/queryCosts"));
		menus.add(new Menu(6, "�ҵ���Ϣ", 1, "view/system/user/userinfo_show.jsp"));
		menus.add(new Menu(7, "�ҵĹ���", 1, "system/queryMySalary"));
		//��ӱ�����������˵�
		menus.add(new Menu(8, "������", 2, "expense/inputExpense"));
		menus.add(new Menu(9, "��������", 2, "expense/queryManageExpenses"));
		menus.add(new Menu(10, "������ѯ", 2, "expense/queryExpenses"));
		menus.add(new Menu(11, "�ҵı���", 2, "expense/queryMyExpense"));
		//��Ӳ����������˵�
		menus.add(new Menu(12, "��������", 3, "finance/queryExpense"));
		menus.add(new Menu(13, "н�ʷ���", 3, "finance/sendSalary"));
		menus.add(new Menu(14, "н�ʲ�ѯ", 3, "finance/querySalary"));
		
		return menus;
	}
	//���ž���Ȩ��
	public List<Menu> getManagerMenus(){
		int[] menuIds=new int[]{1,2,6,7,8,9,10,11};
		return getMenus(menuIds);
	}
	//��ͨԱ��Ȩ��
	public List<Menu> getNormalMenus(){
		int[] menuIds=new int[]{1,2,6,7,8,11};
		return getMenus(menuIds);
	}
	//������ԱȨ��
	public List<Menu> getFinanceMenus(){
		int[] menuIds=new int[]{1,2,3,5,6,7,8,9,11,12,13,14};
		return getMenus(menuIds);
	}
	//���ݽ�ɫ��Ȩ��id��ȡ��ӦȨ��
	public List<Menu> getMenus(int[] menuIds){
		List<Menu> allMenus=getAllMenus();
		List<Menu> newMenus=new ArrayList<Menu>();
		for(int i=0;i<menuIds.length;i++){
			int index=menuIds[i];
			newMenus.add(allMenus.get(index-1));
		}
		return newMenus;
	}
}
