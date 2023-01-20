package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.DBUtils.DBManager;
import com.pojos.User;

public class Userdao {
	
	private Connection con;
	private PreparedStatement stm;
	
	public Userdao() throws SQLException {
//		DBManager.OpenConnection();
		con=DBManager.getConnection();
		stm=con.prepareStatement("select * from users where email=? and password=?");
	}
	
	public User valiateUser(String email,String password) throws SQLException {
		System.out.println("in valiate user");
		stm.setString(1, email);
		stm.setString(2, password);
		
		try(ResultSet rest=stm.executeQuery()){
			if(rest.next()) {
			    return new User(rest.getInt("id"),rest.getString("name"),email,password,rest.getDouble("reg_amt"), rest.getDate("reg_date"),rest.getString("role"));
			}	
		}
		return null;
		
	}
	

}
