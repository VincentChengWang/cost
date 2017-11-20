package com.nm.bean;

import java.util.Date;

public class Salary {
	private int salaryId;
	private int userId;
	private Date salaryMonth;
	private Date salaryDate;
	private double salaryBasic;
	private double salaryComm;
	
	private String userName;

	public int getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(int salaryId) {
		this.salaryId = salaryId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getSalaryMonth() {
		return salaryMonth;
	}

	public void setSalaryMonth(Date salaryMonth) {
		this.salaryMonth = salaryMonth;
	}

	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	public double getSalaryBasic() {
		return salaryBasic;
	}

	public void setSalaryBasic(double salaryBasic) {
		this.salaryBasic = salaryBasic;
	}

	public double getSalaryComm() {
		return salaryComm;
	}

	public void setSalaryComm(double salaryComm) {
		this.salaryComm = salaryComm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Salary [salaryId=" + salaryId + ", userId=" + userId + ", salaryMonth=" + salaryMonth + ", salaryDate="
				+ salaryDate + ", salaryBasic=" + salaryBasic + ", salaryComm=" + salaryComm + ", userName=" + userName
				+ "]";
	}
	
}
