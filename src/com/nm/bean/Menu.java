package com.nm.bean;

import java.io.Serializable;

/**
 * y用户菜单
 * 根据用户的权限不同，获取菜单不同
 * @author cheng
 *
 */
public class Menu implements Serializable{
 private int mid;//菜单的编号
 private String menuName;//菜单的名称
 private int pid;//菜单的父菜单编号
 private String url;//点击菜单时发送的请求地址
public int getMid() {
	return mid;
}
public void setMid(int mid) {
	this.mid = mid;
}
public String getMenuName() {
	return menuName;
}
public void setMenuName(String menuName) {
	this.menuName = menuName;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public Menu(int mid, String menuName, int pid, String url) {
	super();
	this.mid = mid;
	this.menuName = menuName;
	this.pid = pid;
	this.url = url;
}
public Menu() {
	super();
}
@Override
public String toString() {
	return "Menu [mid=" + mid + ", menuName=" + menuName + ", pid=" + pid + ", url=" + url + "]";
}
}
