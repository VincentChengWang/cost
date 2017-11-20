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
	//私有化成员变量
	private static DataSource dataSource=null;
	static{
		dataSource=new ComboPooledDataSource();
	}
	//私有化构造函数
	private JDBCUtill(){}
	//提供一个静态外部方法
	public static DataSource getDataSourceInstance(){
		return dataSource;
	}
	public static QueryRunner getQuerryrunner(){
		//QueryRunner 在创建改sql语句执行对象时，可以指定一个执行sql的数据源
		return new QueryRunner(dataSource);
	}
	/**
	 * 得到一个连接
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
	 * 关闭连接
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
	 * 通用的查询list对象方法
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
	 * 通用查询id的方法
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
	 * 通用查询一条数据的方法
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
	 * 通用增加、删除、修改方法
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
	 * 开启事务
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
	 * 提交事务
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
	 * 事务回滚
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
