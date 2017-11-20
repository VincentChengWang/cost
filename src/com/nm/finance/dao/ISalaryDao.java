package com.nm.finance.dao;

import java.util.List;

import com.nm.bean.Salary;
import com.nm.bean.SalaryChart;

public interface ISalaryDao {
	int addSalary(Salary salary);
	List<Salary> salaryList(Salary salary);
	List<SalaryChart> querySalaryCharts();
}
