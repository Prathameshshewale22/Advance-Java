package com.pages;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	@Override
	public void init() {
		System.out.println("in init of second servlet");
	}
	
	@Override
	public void destroy() {
		System.out.println("in destroy of second servlet");
	}
	
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) {
		System.out.println("in doget method of servlet 2");
	}
}
