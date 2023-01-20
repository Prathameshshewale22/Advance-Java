package com.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/first")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void init() {
		System.out.println("in init of servlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("in destroy of servlet");
	}
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("in servlet response");
		PrintWriter writer = resp.getWriter();
	 resp.setContentType("text/html");
	 writer.write("<h1>Hello world</h1>");
	}

}
