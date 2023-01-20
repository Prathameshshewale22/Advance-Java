package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.DBUtils.DBManager;
import com.pojos.Tutorial;

public class Tutorialdao {
	
	private PreparedStatement stm,stm1;
	private Connection con;
	public Tutorialdao() throws SQLException {
		con=DBManager.getConnection();
		stm=con.prepareStatement("select * from tutorials where topic_id=?");
		stm1=con.prepareStatement("select * from tutorials where name=?");
	}
	
	public List<Tutorial> getallturorial(int topicid) throws SQLException{
		List<Tutorial> alltutorial=new ArrayList<Tutorial>(); 
		stm.setInt(1, topicid);
		ResultSet rst=stm.executeQuery();
		while(rst.next()) {
		   alltutorial.add(new Tutorial(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getDate(4), rst.getInt(5),rst.getString(6),rst.getInt(7)));
		}
		return alltutorial;
	}
	
	public Tutorial getTutorialDetailsByName(String tutName) throws SQLException {
		stm1.setString(1, tutName);
		try (ResultSet rst = stm1.executeQuery()) {
			if (rst.next())
				return new Tutorial(rst.getInt(1), tutName, rst.getString(3), rst.getDate(4), rst.getInt(5),
						rst.getString(6), rst.getInt(7));
		}
		return null;
	}

}
