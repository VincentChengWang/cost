package com.nm.bean;

import java.util.Date;

public class AuditRecord {
	private int auditId;
	private int userId;
	private int expenseId;
	private Date auditDate;
	private String auditState;
	private String auditSugg;

	private String userName;
	//用于显示的审核记录中的审核状态
	private String showAuditState;
	
	public String getShowAuditState() {
		if(auditState.equals("2"))
		showAuditState="<span style='color:green'>经理审核通过</span>";
		else if(auditState.equals("3"))
			showAuditState="<span style='color:green'>财务审核通过</span>";
		else if(auditState.equals("-1"))
			showAuditState="<span style='color:red'>经理审核不通过</span>";
		else if(auditState.equals("-2"))
			showAuditState="<span style='color:red'>财务审核不通过</span>";
		return showAuditState;
	}




	public void setShowAuditState(String showAuditState) {
		this.showAuditState = showAuditState;
	}




	@Override
	public String toString() {
		return "AuditRecord [auditId=" + auditId + ", userId=" + userId + ", expenseId=" + expenseId + ", auditDate="
				+ auditDate + ", auditState=" + auditState + ", auditSugg=" + auditSugg + ", userName=" + userName
				+ "]";
	}




	public String getAuditSugg() {
		return auditSugg;
	}


	public void setAuditSugg(String auditSugg) {
		this.auditSugg = auditSugg;
	}


	


	

	public int getAuditId() {
		return auditId;
	}


	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getExpenseId() {
		return expenseId;
	}


	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}


	public Date getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}


	public String getAuditState() {
		return auditState;
	}


	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}


	


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}
}
