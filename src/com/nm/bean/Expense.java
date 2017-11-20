package com.nm.bean;

import java.util.Arrays;
import java.util.Date;

public class Expense {
	private int expenseId;
	private int userId;
	private String expenseName;
	private String expenseState;
	private String expenseDesc;
	private Date expenseDate;
	private double expenseMoney;
//����ɾ��
	private Integer[] expenseIds;
	private String ck;
	//������ʾ
	private String userName;
	private String showExpenseState;
	
	//���ڽ��ձ�����ϸ������
	private Integer[] costIds;
	private String[] detailDescs;
	private Double[] detailMoneys;
	//���ڿ�ʼ����ʱ��
	private Date startDate;
	private Date endDate;
	//���ڸ��ݲ�ͬ������ѯ������״̬
	private Integer[] expenseStates;
	//������ʾ�޸İ�ť
	private String updateButton;
	public String getUpdateButton() {
		updateButton="<a href='expense/updateExpense?expenseId="+expenseId+"'>�޸�</a>";
		if(expenseState.equals("2") || expenseState.equals("3")){
			
			updateButton="";
		}
		return updateButton;
	}
	public void setUpdateButton(String updateButton) {
		this.updateButton = updateButton;
	}
	public String getShowExpenseState() {
		if(expenseState.equals("0"))
			showExpenseState="δ�ύ";
		else if(expenseState.equals("1"))
			showExpenseState="<span style='color:blue'>����δ���</span>";
		else if(expenseState.equals("2"))
			showExpenseState="<span style='color:green'>�������ͨ��</span>";
		else if(expenseState.equals("3"))
			showExpenseState="<span style='color:green'>�������ͨ��</span>";
		else if(expenseState.equals("-1"))
			showExpenseState="<span style='color:red'>������˲�ͨ��</span>";
		else if(expenseState.equals("-2"))
			showExpenseState="<span style='color:red'>������˲�ͨ��</span>";
		return showExpenseState;
	}
	public void setShowExpenseState(String showExpenseState) {
		this.showExpenseState = showExpenseState;
	}
	public Integer[] getExpenseStates() {
		return expenseStates;
	}
	public void setExpenseStates(Integer[] expenseStates) {
		this.expenseStates = expenseStates;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer[] getCostIds() {
		return costIds;
	}
	public void setCostIds(Integer[] costIds) {
		this.costIds = costIds;
	}
	public String[] getDetailDescs() {
		return detailDescs;
	}
	public void setDetailDescs(String[] detailDescs) {
		this.detailDescs = detailDescs;
	}
	public Double[] getDetailMoneys() {
		return detailMoneys;
	}
	public void setDetailMoneys(Double[] detailMoneys) {
		this.detailMoneys = detailMoneys;
	}
	
	
	@Override
	public String toString() {
		return "Expense [expenseId=" + expenseId + ", userId=" + userId + ", expenseName=" + expenseName
				+ ", expenseState=" + expenseState + ", expenseDesc=" + expenseDesc + ", expenseDate=" + expenseDate
				+ ", expenseMoney=" + expenseMoney + ", expenseIds=" + Arrays.toString(expenseIds) + ", ck=" + ck
				+ ", userName=" + userName + ", costIds=" + Arrays.toString(costIds) + ", detailDescs="
				+ Arrays.toString(detailDescs) + ", detailMoneys=" + Arrays.toString(detailMoneys) + ", startDate="
				+ startDate + ", endDate=" + endDate + ", expenseStates=" + Arrays.toString(expenseStates) + "]";
	}
	public int getExpenseId() {
		return expenseId;
	}
	public void setExpenseId(int expenseId) {
		this.expenseId = expenseId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getExpenseName() {
		return expenseName;
	}
	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}
	public String getExpenseState() {
		return expenseState;
	}
	public void setExpenseState(String expenseState) {
		this.expenseState = expenseState;
	}
	public String getExpenseDesc() {
		return expenseDesc;
	}
	public void setExpenseDesc(String expenseDesc) {
		this.expenseDesc = expenseDesc;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public double getExpenseMoney() {
		return expenseMoney;
	}
	public void setExpenseMoney(double expenseMoney) {
		this.expenseMoney = expenseMoney;
	}
	public Integer[] getExpenseIds() {
		return expenseIds;
	}
	public void setExpenseIds(Integer[] expenseIds) {
		this.expenseIds = expenseIds;
	}
	public String getCk() {
		return "<input type='checkbox' name='expenseIds' value='"+expenseIds+"'";
	}
	public void setCk(String ck) {
		this.ck = ck;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
