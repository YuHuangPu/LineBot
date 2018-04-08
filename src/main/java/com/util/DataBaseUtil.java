package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseUtil {
	public static Connection getConnection(Properties conf, Boolean autoCommit) throws ClassNotFoundException, SQLException{
		Class.forName(conf.getProperty("class"));
		Connection conn = null;
		String datasource = conf.getProperty("datasource");
		conn = DriverManager.getConnection(datasource
				, conf.getProperty("username")
				, conf.getProperty("password"));
		if(conn == null){
			throw new SQLException("Connect fail !!");
		}
		conn.setAutoCommit(autoCommit);
		return conn;
	}
	public static void colseConnection(Connection conn){
		if(conn != null){
			try {
				if(!conn.getAutoCommit()){
					conn.rollback();
				}
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
