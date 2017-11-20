package com.nm.finance.service.impl;

import java.util.List;

import com.nm.bean.Salary;
import com.nm.bean.SalaryChart;
import com.nm.finance.dao.ISalaryDao;
import com.nm.finance.dao.impl.SalaryDaoImpl;
import com.nm.finance.service.ISalaryService;

public class SalaryServiceImpl implements ISalaryService{
	ISalaryDao salaryDao=new SalaryDaoImpl();
	@Override
	public boolean addSalary(Salary salary) {
		// TODO Auto-generated method stub
		int row=salaryDao.addSalary(salary);
		if(row>0){
			return true;
		}
		return false;
	}

	@Override
	public List<Salary> querySalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryDao.salaryList(salary);
	}

	@Override
	public List<SalaryChart> querySalaryCharts() {
		// TODO Auto-generated method stub
		return salaryDao.querySalaryCharts();
	}

}
