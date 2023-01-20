package com.pages;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.Tutorialdao;
import com.pojos.Tutorial;

/**
 * Servlet implementation class TutorialServlet
 */
@WebServlet(value="/tutorial")
public class TutorialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		try(PrintWriter pw= response.getWriter()){
			HttpSession sh=request.getSession();
			Tutorialdao tdao=(Tutorialdao)sh.getAttribute("tutorialdao");
			if(tdao!=null) {
				int tid=Integer.parseInt(request.getParameter("topic_id"));
				List<Tutorial> allturorial = tdao.getallturorial(tid);
				String str="<ol>";
				for(Tutorial t:allturorial) {
					str+="<li><a href='tutorialdetails?tutName="+t.getTutName()+"'>"+t.getTutName()+"</a></li>";
				}
				str+="</ol>";
				pw.write(str);
			}
			else {
				pw.print("<h5> NO Cookies !!!!!!! Session Tracking Failed !!!!!!!!!!!!!!!!</h5>");	
			}
		}catch(SQLException e) {
			
		}
		
	}

}
