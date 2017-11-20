package com.nm.bean;

import java.util.Arrays;

public class Cost {
private int costId;
private String costName;
private String costDesc;
private String costMark;
private String ck;
private Integer ids[];
@Override
public String toString() {
	return "Cost [costId=" + costId + ", costName=" + costName + ", costDesc=" + costDesc + ", costMark=" + costMark
			+ ", ck=" + ck + ", ids=" + Arrays.toString(ids) + "]";
}
public Cost(int costId, String costName, String costDesc, String costMark, String ck, Integer[] ids) {
	super();
	this.costId = costId;
	this.costName = costName;
	this.costDesc = costDesc;
	this.costMark = costMark;
	this.ck = ck;
	this.ids = ids;
}
public Cost() {
	super();
}
public int getCostId() {
	return costId;
}
public void setCostId(int costId) {
	this.costId = costId;
}
public String getCostName() {
	return costName;
}
public void setCostName(String costName) {
	this.costName = costName;
}
public String getCostDesc() {
	return costDesc;
}
public void setCostDesc(String costDesc) {
	this.costDesc = costDesc;
}
public String getCostMark() {
	return costMark;
}
public void setCostMark(String costMark) {
	this.costMark = costMark;
}
public String getCk() {
	return "<input type='checkbox' name='ids' value='"+costId+"'/>";
}
public void setCk(String ck) {
	this.ck = ck;
}
public Integer[] getIds() {
	return ids;
}
public void setIds(Integer[] ids) {
	this.ids = ids;
}
}
