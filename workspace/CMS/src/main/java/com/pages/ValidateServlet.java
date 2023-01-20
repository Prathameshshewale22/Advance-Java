package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DBUtils.DBManager;
import com.dao.Topicdao;
import com.dao.Tutorialdao;
import com.dao.Userdao;
import com.pojos.User;

@WebServlet(value="/validate",loadOnStartup = 1)
public class ValidateServlet extends HttpServlet {
	
	private Userdao user;
	private Topicdao topic;
	private Tutorialdao tutorial;
	@Override
	public void init() {
		System.out.println("in init of servlet");
		try {
			DBManager.OpenConnection();
		    user=new Userdao();
		    topic=new Topicdao();
		    tutorial=new Tutorialdao();
		    
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		System.out.println("in dopost");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		System.out.println(email +"====="+password);
		resp.setContentType("text/html");
		try(PrintWriter wr= resp.getWriter()){
		User us=user.valiateUser(email, password);
		if(us!=null) {
			
			wr.write("<h1>Welcome  "+us.getName()+"</h1>");
			wr.write("<h3>"+us+"</h3>");
			
		   HttpSession sh=req.getSession();
		   
		   sh.setAttribute("userinfo",us);
		   sh.setAttribute("user_dao", user);
		   sh.setAttribute("Topic_dao",topic);
		   sh.setAttribute("tutorialdao",tutorial);
		   resp.sendRedirect("topic");
		}
		else {
			wr.write("<h1>Tay again</h1>");
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void destroy() {
		System.out.println("in destroy");
		try {
			DBManager.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
