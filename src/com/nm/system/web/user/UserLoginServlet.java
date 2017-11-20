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
		//判断用户是否登录
		HttpSession session=req.getSession();
		//获取session里面保存的用户信息
		UserInfo userinfo=(UserInfo) session.getAttribute("userinfo");
		//如果用户已经登录，则直接跳转到主页
		if(userinfo!=null){
			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
			return;
		}
		// 1.如果用户没有登录，则接收用户登录信息
		UserInfo user = RequestBeanUtils.requestToBean(req, UserInfo.class);
		// 2.调用service
		IuserService userService = new UserServiceImpl();
		 UserInfo loginUser=userService.doLogin(user);
		
		 //返回并跳转
		if(loginUser==null){
			req.setAttribute("tip", "用户名或密码错误");
			req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
		}else{
			//登录成功
			session.setAttribute("userinfo", loginUser);
			//获取用户权限
			List<Menu> menus=null;
			if(loginUser.getUserRole().equals("超级管理员"))
				menus=getAllMenus();
			else if(loginUser.getUserRole().equals("部门经理"))
				menus=getManagerMenus();
			else if(loginUser.getUserRole().equals("财务人员"))
				menus=getFinanceMenus();
			else 
				menus=getNormalMenus();
			//查询薪资报表信息
			ISalaryService salaryService=new SalaryServiceImpl();
			List<SalaryChart> salaryCharts=salaryService.querySalaryCharts();
			
			session.setAttribute("salaryCharts", salaryCharts);
			session.setAttribute("menus", menus);
			req.getRequestDispatcher("/view/index.jsp").forward(req, resp);
		}
		
	}
	//所有权限
	public List<Menu> getAllMenus(){
		List<Menu> menus=new ArrayList<>();
		//添加一级菜单
		menus.add(new Menu(1, "系统管理", 0, ""));
		menus.add(new Menu(2, "报销管理", 0, ""));
		menus.add(new Menu(3, "财务管理", 0, ""));
		//添加系统管理二级菜单
		menus.add(new Menu(4, "用户管理", 1, "system/queryUsers"));
		menus.add(new Menu(5, "费用管理", 1, "system/queryCosts"));
		menus.add(new Menu(6, "我的信息", 1, "view/system/user/userinfo_show.jsp"));
		menus.add(new Menu(7, "我的工资", 1, "system/queryMySalary"));
		//添加报销管理二级菜单
		menus.add(new Menu(8, "报销单", 2, "expense/inputExpense"));
		menus.add(new Menu(9, "经理审批", 2, "expense/queryManageExpenses"));
		menus.add(new Menu(10, "报销查询", 2, "expense/queryExpenses"));
		menus.add(new Menu(11, "我的报销", 2, "expense/queryMyExpense"));
		//添加财务管理二级菜单
		menus.add(new Menu(12, "财务审批", 3, "finance/queryExpense"));
		menus.add(new Menu(13, "薪资发放", 3, "finance/sendSalary"));
		menus.add(new Menu(14, "薪资查询", 3, "finance/querySalary"));
		
		return menus;
	}
	//部门经理权限
	public List<Menu> getManagerMenus(){
		int[] menuIds=new int[]{1,2,6,7,8,9,10,11};
		return getMenus(menuIds);
	}
	//普通员工权限
	public List<Menu> getNormalMenus(){
		int[] menuIds=new int[]{1,2,6,7,8,11};
		return getMenus(menuIds);
	}
	//财务人员权限
	public List<Menu> getFinanceMenus(){
		int[] menuIds=new int[]{1,2,3,5,6,7,8,9,11,12,13,14};
		return getMenus(menuIds);
	}
	//根据角色的权限id获取相应权限
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
