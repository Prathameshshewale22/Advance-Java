package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Userdao;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		try(PrintWriter pw= response.getWriter()){
			HttpSession sh = request.getSession();
			Userdao ud =(Userdao) sh.getAttribute("user_dao");
			if(ud != null) {
				pw.print("<h5> You have logged out....</h5>");	
			}
			else {
				pw.print("no cookie");
			}
			sh.invalidate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
