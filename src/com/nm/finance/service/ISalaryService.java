package com.nm.finance.service;

import java.util.List;

import com.nm.bean.Salary;
import com.nm.bean.SalaryChart;

public interface ISalaryService {
	boolean addSalary(Salary salary);
	List<Salary> querySalary(Salary salary);
	List<SalaryChart> querySalaryCharts();
}
