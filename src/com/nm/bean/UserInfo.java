package com.nm.bean;

import java.util.Arrays;

public class UserInfo {
	private int userId;
	private String userName;
	private String userSex;
	private int userAge;
	private String userPhone;
	private String userAccount;
	private String userPwd;
	private String userRole;
	private double userSalary;
	private String userMark;
	private String ck;
	//用于多条删除的属性
	private Integer[] ids;
	public Integer[] getIds() {
		return ids;
	}
	public void setIds(Integer[] ids) {
		this.ids = ids;
	}
	public String getCk() {
		return "<input type='checkbox' name='ids' value='"+userId+"'>";
	}
	public void setCk(String ck) {
		this.ck = ck;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public double getUserSalary() {
		return userSalary;
	}
	public void setUserSalary(double userSalary) {
		this.userSalary = userSalary;
	}
	public String getUserMark() {
		return userMark;
	}
	public void setUserMark(String userMark) {
		this.userMark = userMark;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", userName=" + userName + ", userSex=" + userSex + ", userAge=" + userAge
				+ ", userPhone=" + userPhone + ", userAccount=" + userAccount + ", userPwd=" + userPwd + ", userRole="
				+ userRole + ", userSalary=" + userSalary + ", userMark=" + userMark + ", ck=" + ck + ", ids="
				+ Arrays.toString(ids) + "]";
	}
}
