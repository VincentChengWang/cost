package com.nm.bean;

import java.util.Date;

public class SalaryChart {
	private  Date salaryMonth;
	private double salaryBasicTotal;
	private double salaryCommTotal;
	
	public Date getSalaryMonth() {
		return salaryMonth;
	}
	public void setSalaryMonth(Date salaryMonth) {
		this.salaryMonth = salaryMonth;
	}
	public double getSalaryBasicTotal() {
		return salaryBasicTotal;
	}
	public void setSalaryBasicTotal(double salaryBasicTotal) {
		this.salaryBasicTotal = salaryBasicTotal;
	}
	public double getSalaryCommTotal() {
		return salaryCommTotal;
	}
	public void setSalaryCommTotal(double salaryCommTotal) {
		this.salaryCommTotal = salaryCommTotal;
	}
	@Override
	public String toString() {
		return "SalaryChart [salaryChart=" + salaryMonth + ", salaryBasicTotal=" + salaryBasicTotal
				+ ", salaryCommTotal=" + salaryCommTotal + "]";
	}
	
	
}
