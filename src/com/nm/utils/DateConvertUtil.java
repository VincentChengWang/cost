package com.nm.utils;


import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConvertUtil implements Converter{
/**
 * type �������������
 * value ���յĲ�����ֵ
 * object���ص�ֵ�Ǳ�ʾ��object���ø�����ĸ�����
 */
	private String pattern="yyyy-MM-dd";
	public DateConvertUtil(String pattern) {
	super();
	this.pattern = pattern;
}
	public DateConvertUtil() {
	super();
}
	@Override
	public Object convert(Class type, Object value) {
		if(value==null){
			return null;
		}else if(type==Date.class){
			//��������date����ʱ�����յĲ���Ϊ�����ַ���ʱ������null
			if(value.equals("")){
				return null;
			}
			//�����յ�ʱ���ʽ���ַ���ת��Ϊdate�������ø�����ĸ�����
			return DateUtill.str2date((String)value,pattern);
		}
		
		return value;
	}

}
