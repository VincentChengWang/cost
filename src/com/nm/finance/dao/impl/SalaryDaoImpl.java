package com.nm.finance.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nm.bean.Salary;
import com.nm.bean.SalaryChart;
import com.nm.finance.dao.ISalaryDao;
import com.nm.utils.DateUtill;
import com.nm.utils.JDBCUtill;
import com.nm.utils.StringUtill;

public class SalaryDaoImpl implements ISalaryDao{

	@Override
	public int addSalary(Salary salary) {
		//1.添加薪资sql
		String sql="insert into t_salary(userId,salaryMonth,salaryDate,salaryBasic,salaryComm) values(?,?,now(),?,?)";
		//2.sql参数
		try {
			return JDBCUtill.update(sql, salary.getUserId(),DateUtill.date2str(salary.getSalaryMonth()),salary.getSalaryBasic(),salary.getSalaryComm());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Salary> salaryList(Salary salary) {
		// 1.拼接sql
		StringBuffer sb=new StringBuffer("select ts.*,tu.userName from t_salary ts,t_users tu where ts.userId=tu.userId");
		//2.拼接sql查询参数
		List<Object> params=new ArrayList<Object>();
		if(StringUtill.isNotNull(salary.getUserId())){
			sb.append(" and tu.userId=?");
			params.add(salary.getUserId());
		}
		if(StringUtill.isNotNull(salary.getUserName())){
			sb.append(" and tu.userName like ?");
			params.add("%"+salary.getUserName()+"%");
		}if(StringUtill.isNotNull(salary.getSalaryMonth())){
			sb.append(" and ts.salaryMonth = ?");
			params.add(DateUtill.date2str(salary.getSalaryMonth()));
		}
		//3.执行sql语句
		try {
			return JDBCUtill.queryList(sb.toString(), Salary.class, params.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<SalaryChart> querySalaryCharts() {
		// 1.查询报表的sql
		String sql="select salaryMonth,sum(salaryBasic) as salaryBasicTotal,sum(salaryComm) as salaryCommTotal from t_salary GROUP BY salaryMonth";
		try {
			return JDBCUtill.queryList(sql, SalaryChart.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
