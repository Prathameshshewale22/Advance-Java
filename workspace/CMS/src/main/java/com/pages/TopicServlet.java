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

import com.dao.Topicdao;
import com.pojos.Topic;
import com.pojos.User;


@WebServlet("/topic")
public class TopicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		try(PrintWriter pw=response.getWriter()){
			
			HttpSession sh=request.getSession();
			System.out.println("session id="+sh.getId());
			
			User us=(User)sh.getAttribute("userinfo");
			if(us !=null) {
//				pw.write("<h5>User details from httpsession"+us +"</h5>");
				
				Topicdao tp=(Topicdao)sh.getAttribute("Topic_dao");
				List<Topic> alltopics=tp.getalltopics();
				
				String str="<form action='tutorial'><ol>";
				for(Topic t:alltopics) {
					str+="<li><input type='radio' name='topic_id' value='"+t.getTopicId()+"'>"+t.getTopicName()+"</li>";
				}
				str+="</ol>";
			    str+="<h5><input type='submit' value='Choose A Topic'/></h5></form>";
				pw.write(str);
			}
			else {
				pw.print("<h5> No Cookies !!!!!!!!!!!!! Session Management Failed !!!!!!!!!!!!!!!!!!!!!</h5>");
			}
			
		}catch(SQLException e) {
			
		}
		
	}

}
