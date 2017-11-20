package com.nm.utils;


import java.util.Date;

import org.apache.commons.beanutils.Converter;

public class DateConvertUtil implements Converter{
/**
 * type 对象的属性类型
 * value 接收的参数的值
 * object返回的值是表示将object设置给对象的该属性
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
			//当属性是date类型时，接收的参数为“”字符串时，返回null
			if(value.equals("")){
				return null;
			}
			//将接收的时间格式的字符串转换为date类型设置给对象的该属性
			return DateUtill.str2date((String)value,pattern);
		}
		
		return value;
	}

}
