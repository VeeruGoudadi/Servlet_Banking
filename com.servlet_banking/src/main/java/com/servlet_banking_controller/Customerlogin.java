package com.servlet_banking_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Customerdao;
import com.servlet_banking_dto.Customer;

@WebServlet("/customerlogin")
public class Customerlogin extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int custid=Integer.parseInt(req.getParameter("custid"));
		String password=req.getParameter("password");
		
		Customerdao dao=new Customerdao();
		Customer customer=dao.login(custid);
		
		if(customer==null)
		{
			resp.getWriter().print("<h1>Invlaid Customer Id</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else{
			if(customer.getPaswword().equals(password))
			{
				req.getSession().setAttribute("customer", customer);
				resp.getWriter().print("<h1>Login Success</h1>");
				req.getRequestDispatcher("customerhome.html").include(req, resp);
			}
			else{
				resp.getWriter().print("<h1>Invalid Password</h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
	}
	

}
