package com.nm.utils;
/**
 * �ַ���������
 * @author cheng
 *
 */
public class StringUtill {
	/**
	 * �ж��ַ����Ƿ�Ϊnull����ַ���
	 * @param str	�жϵ��ַ���
	 * @return	�����Ϊnull���Ҳ�Ϊ���ַ������򷵻�true�����򷵻�false
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
	 * �ж϶����Ƿ�Ϊnull
	 * @param obj
	 * @return	�������Ϊnull����false�����򷵻�true
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
