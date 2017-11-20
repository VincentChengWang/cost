package com.nm.bean;

import java.io.Serializable;

/**
 * y�û��˵�
 * �����û���Ȩ�޲�ͬ����ȡ�˵���ͬ
 * @author cheng
 *
 */
public class Menu implements Serializable{
 private int mid;//�˵��ı��
 private String menuName;//�˵�������
 private int pid;//�˵��ĸ��˵����
 private String url;//����˵�ʱ���͵������ַ
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
