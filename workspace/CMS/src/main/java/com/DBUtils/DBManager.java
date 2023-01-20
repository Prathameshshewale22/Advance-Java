package com.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	private static  Connection con; 
	
	public static  void OpenConnection() throws SQLException {
		
	
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/CMS","root","root@123");
		
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	public static  void closeConnection() throws SQLException {
		if(con!=null) {
			con.close();
		}
	}

}
