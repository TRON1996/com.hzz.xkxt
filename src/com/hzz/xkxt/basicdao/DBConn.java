package com.hzz.xkxt.basicdao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接类 * 
 * @author gudh
 * 2019-4-2
 */
public class DBConn {

	private String DriverName;
	private String url;
	private String username;
	private String pwd;

	/*
	 * 数据库连接类的构造方法，在构造数据库对象时从配置文件中获取连接参数，并注册驱动
	 */
	public DBConn() {
		try {
			Properties pro = new Properties();
			pro.load(DBConn.class.getClassLoader().getResourceAsStream(
					"conn.ini"));
			DriverName = pro.getProperty("DriverName");
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			pwd = pro.getProperty("pwd");
			Class.forName(DriverName);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获取连接对象	 * 
	 * @return 连接对象
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, pwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 关闭Connection、Statement、ResultSet
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public void closeDB(Connection conn, PreparedStatement ps,ResultSet rs) {

		try {
			if (conn != null)
				conn.close();
			if (ps != null)
				ps.close();
			if(rs!=null)
				rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
