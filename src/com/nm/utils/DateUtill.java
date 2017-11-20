package com.nm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * @author cheng
 *
 */
public class DateUtill {
	public static final String PATTERN="yyyy-MM-dd HH:mm:ss";
	/**
	 * 将字符转换为时间类型
	 * @param str
	 * @return
	 */
	public static Date str2date(String str){
		SimpleDateFormat sdf=new SimpleDateFormat(PATTERN);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据用户的需要将字符串转换为时间格式
	 * @param str
	 * @param pattern	转换的时间格式
	 * @return
	 */
	public static Date str2date(String str,String pattern){
		
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		try {
			return sdf.parse(str);
	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

			}
	/**
	 * 将时间格式化为字符串yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String date2str(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat(PATTERN);
		return sdf.format(date);
	}
	/**
	 * 根据用户需要将时间格式化为用户所需的字符串
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2str(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
				return sdf.format(date);
	}
	/**
	 * 将时间增加int day 天的同时格式化为字符串
	 * @param date
	 * @param pattern
	 * @param day
	 * @return
	 */
	public static String date2str(Date date,String pattern,int day){
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH,day);
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		return sdf.format(c.getTime());
	}
}
