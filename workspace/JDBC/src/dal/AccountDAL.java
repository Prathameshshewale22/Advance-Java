package dal;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import DButils.DataBaseConnection;
import dol.Account;

public class AccountDAL implements AccountInterface{
	
	private Connection conn;
	private Statement stm;
	private ResultSet set;
	private PreparedStatement pstm1,pstm2,pstm3,pstm4,pstm5;
	private CallableStatement cstm;
	public AccountDAL() throws SQLException {
		
		conn=DataBaseConnection.getConnection();
		stm=conn.createStatement();
		pstm1=conn.prepareStatement("insert into accounts values(?,?,?,?)");
		pstm2=conn.prepareStatement("delete from accounts where id=?");
		pstm3=conn.prepareStatement("update accounts set name=?,type=?,bal=? where id=?");
		pstm4=conn.prepareStatement("update accounts set bal=bal+? where id=?");
		pstm5=conn.prepareStatement("update accounts set bal=bal-? where id=?");
		cstm=conn.prepareCall("{call transfer_funds(?,?,?,?,?)}");
		cstm.registerOutParameter(4, Types.DOUBLE);
		cstm.registerOutParameter(5, Types.DOUBLE);
	}

	@Override
	public List<Account> AllAccounts() {
	
		try {
			List<Account> allAcc=new ArrayList<Account>();
		set=stm.executeQuery("select * from accounts");
		while(set.next()) {
			allAcc.add(new Account(set.getInt("id"),
					set.getString("name"),
					set.getString("type"),
					set.getDouble("bal")));
		}
		return allAcc;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertNewAcc(Account obj) {
		
		try {
			pstm1.setInt(1,obj.getId());
			pstm1.setString(2,obj.getName());
			pstm1.setString(3,obj.getType());
			pstm1.setDouble(4,obj.getBal());
			
		int i=pstm1.executeUpdate();
		System.out.println("inserted account="+obj);
		return i;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public int DeleteAcc(int id) {
		try {
			pstm2.setInt(1, id);
			int i=pstm2.executeUpdate();
			System.out.println("deleted account "+id);
			return i;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int UpdateAcc(Account acc) {
		try {
             pstm3.setString(1, acc.getName());
             pstm3.setString(2,acc.getType());
             pstm3.setDouble(3,acc.getBal());
             pstm3.setInt(4,acc.getId());
             int i=pstm3.executeUpdate();
             System.out.println("Account updated="+acc);
             return i;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int DepositeAmt(double amt,int id) {
		
		try {
			pstm4.setDouble(1, amt);
			pstm4.setInt(2, id);
			int i=pstm4.executeUpdate();
			System.out.println("in deposit");
			return i;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public int WithdrawAmt(double amt,int id) {
		try {
			pstm5.setDouble(1, amt);
			pstm5.setInt(2, id);
			int i=pstm5.executeUpdate();
			return i;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public void MoneyTransfer(int sid,int rid,double amt) throws SQLException {
		cstm.setInt(1, sid);
		cstm.setInt(2, rid);
		cstm.setDouble(3, amt);
		cstm.execute();
		System.out.println("sender balance="+cstm.getDouble(4)+"reciver balance= "+cstm.getDouble(5));
		
	}

}

