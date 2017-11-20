package com.nm.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import com.mchange.v2.c3p0.ComboPooledDataSource;



public class JDBCUtill {
	//˽�л���Ա����
	private static DataSource dataSource=null;
	static{
		dataSource=new ComboPooledDataSource();
	}
	//˽�л����캯��
	private JDBCUtill(){}
	//�ṩһ����̬�ⲿ����
	public static DataSource getDataSourceInstance(){
		return dataSource;
	}
	public static QueryRunner getQuerryrunner(){
		//QueryRunner �ڴ�����sql���ִ�ж���ʱ������ָ��һ��ִ��sql������Դ
		return new QueryRunner(dataSource);
	}
	/**
	 * �õ�һ������
	 * @return
	 */
	public static Connection getConnection(){
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * �ر�����
	 * @param conn
	 */
	public static void closeConnection(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ͨ�õĲ�ѯlist���󷽷�
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static <T>List<T> queryList(String sql,Class<T> t,Object...params) throws SQLException{
	QueryRunner qr=getQuerryrunner();
		List<T> list=qr.query(sql, new BeanListHandler<T>(t),params);
	return list;
	
		
	}
	/**
	 * ͨ�ò�ѯid�ķ���
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static int getUserId(String sql,Object...params) throws SQLException{
		QueryRunner qr=getQuerryrunner();
		
			int id=qr.query(sql, new ScalarHandler<Integer>(),params);
		return id;
		
	}
	/**
	 * ͨ�ò�ѯһ�����ݵķ���
	 * @param sql
	 * @param t
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static <T>T queryOne(String sql,Class<T> t,Object...params) throws SQLException{
		QueryRunner qr=getQuerryrunner();
		
			T obj=qr.query(sql, new BeanHandler<T>(t), params);
		return obj;
		
	}
	/**
	 * ͨ�����ӡ�ɾ�����޸ķ���
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException 
	 */
	public static int update(String sql,Object...params) throws SQLException{
		QueryRunner qr=getQuerryrunner();
		
			int rowNumber=qr.update(sql,params);
			return rowNumber;
	}
	/**
	 * ��������
	 * @param conn
	 */
	public static void startTranscation(Connection conn){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * �ύ����
	 * @param conn
	 */
	public static void commitTranscation(Connection conn){
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ����ع�
	 * @param conn
	 */
	public static void rollbackTranscation(Connection conn){
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
