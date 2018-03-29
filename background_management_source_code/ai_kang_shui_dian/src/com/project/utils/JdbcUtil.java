package com.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	private static String URL = "jdbc:mysql://106.14.168.155:3306/ai_kang_shui_dian?useSSL=false&amp;serverTimezone=UTC&amp;characterEncoding=UTF-8";
	private static String USERNAME = "root";
	private static String PASSWORD = "AkanShifu100#";
	
	public static Connection con = null;  
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return con;
	}
	public static void close(Connection connection,PreparedStatement preparedStatement,ResultSet resultSet) {
		try {
			if(resultSet!=null) {
				resultSet.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
