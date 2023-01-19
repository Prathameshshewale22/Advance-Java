package DButils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
	
	public static Connection con; 

	public static void connect() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
	    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root@123");
	}
	
	public static Connection getConnection() {
		return con;
	}
}
