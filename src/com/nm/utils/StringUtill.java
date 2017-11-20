package com.nm.utils;
/**
 * 字符串工具类
 * @author cheng
 *
 */
public class StringUtill {
	/**
	 * 判断字符串是否为null或空字符串
	 * @param str	判断的字符串
	 * @return	如果不为null并且不为空字符串，则返回true，否则返回false
	 */
	public static boolean isNotNull(String str){
		if(str==null){
			return false;
		}
		if(("").equals(str)){
			return false;
		}
		return true;
	}
	/**
	 * 判断对象是否为null
	 * @param obj
	 * @return	如果对象为null返回false，否则返回true
	 */
	public static boolean isNotNull(Object obj){
	if(obj==null){
		return false;
	}
	if(obj instanceof Integer){
		Integer i=(Integer)obj;
		if(i==0){
			return false;
		}
	}
		return true;	
	}
}
