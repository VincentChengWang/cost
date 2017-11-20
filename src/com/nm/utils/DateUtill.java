package com.nm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ʱ�乤����
 * @author cheng
 *
 */
public class DateUtill {
	public static final String PATTERN="yyyy-MM-dd HH:mm:ss";
	/**
	 * ���ַ�ת��Ϊʱ������
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
	 * �����û�����Ҫ���ַ���ת��Ϊʱ���ʽ
	 * @param str
	 * @param pattern	ת����ʱ���ʽ
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
	 * ��ʱ���ʽ��Ϊ�ַ���yyyy-MM-dd HH:mm:ss
	 * @param date
	 * @return
	 */
	public static String date2str(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat(PATTERN);
		return sdf.format(date);
	}
	/**
	 * �����û���Ҫ��ʱ���ʽ��Ϊ�û�������ַ���
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String date2str(Date date,String pattern){
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
				return sdf.format(date);
	}
	/**
	 * ��ʱ������int day ���ͬʱ��ʽ��Ϊ�ַ���
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
