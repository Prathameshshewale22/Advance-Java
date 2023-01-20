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

import com.dao.Tutorialdao;
import com.pojos.Tutorial;

/**
 * Servlet implementation class TutorialdetailsServlet
 */
@WebServlet("/tutorialdetails")
public class TutorialdetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			
			HttpSession sh = request.getSession();
			Tutorialdao tdao=(Tutorialdao)sh.getAttribute("tutorialdao");
			if(tdao!=null) {
			String name = request.getParameter("tutName");
			System.out.println(name);
			Tutorial details = tdao.getTutorialDetailsByName(name);
//			String str="<P>"+details+"</p>";
			pw.print(details.getTutName()+"<br/>");
			pw.print(details.getContents());
			pw.print("<h5><a href='tutorial?topic_id="+details.getTopicId()+"'>BACK</a></h5>");
			pw.print("<h5><a href='logout'>Logout</a></h5>");
			}
			else {
				pw.print("login first");
			}
		}catch(SQLException e) {
			
		}
	}

}
