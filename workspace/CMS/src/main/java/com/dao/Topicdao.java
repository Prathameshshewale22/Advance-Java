package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBUtils.DBManager;
import com.pojos.Topic;

public class Topicdao {
	
	private Connection conn;
	
	private PreparedStatement stm;
	
	public Topicdao() throws SQLException {
		conn=DBManager.getConnection();
		stm=conn.prepareStatement("select * from topics");
	}
	
	public List<Topic> getalltopics() throws SQLException{
		List<Topic> alltopics=new ArrayList<Topic>();
		ResultSet rs=stm.executeQuery();
		while(rs.next()) {
			alltopics.add(new Topic(rs.getInt(1),rs.getString(2)));
		}
		return alltopics;
	}
	
	public void cleanUp() throws SQLException
	{
		if(stm != null)
			stm.close();
		System.out.println("topic dao cleaned up....");
	}

}
