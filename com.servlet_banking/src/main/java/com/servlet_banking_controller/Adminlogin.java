package com.servlet_banking_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Bankdao;
@WebServlet("/adminlogin")
public class Adminlogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		
		if(email.equals("admin") && password.equals("admin"))
		{
			resp.getWriter().print("<h1>Login Success</h1>");
			
			Bankdao bankdao=new Bankdao();
			req.setAttribute("list", bankdao.fetchAll());
			req.getRequestDispatcher("adminhome.jsp").include(req, resp);
		}
		else{
			resp.getWriter().print("<h1>Invalid Credentials</h1>");
			req.getRequestDispatcher("adminlogin.html").include(req, resp);
		}
	}

}
