package com.servlet_banking_controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Customerdao;
import com.servlet_banking_dto.Customer;

@WebServlet("/customersignup")
public class Customersignup extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customerdao dao=new Customerdao();
		
		String email=req.getParameter("email");
		String mob=req.getParameter("mobile");
		long mobile=Long.parseLong(mob);
		Date date=Date.valueOf(req.getParameter("dob"));
		int age=Period.between(date.toLocalDate(), LocalDate.now()).getYears();
		
    	if(age<18)
    	{
    		resp.getWriter().print("<h1>You have to be 18+ to create a bank account</h1>");
    		req.getRequestDispatcher("signup.html").include(req, resp);
    	}
    	else{
    		if(dao.check(mobile).isEmpty() && dao.check(email).isEmpty())
    		{
    			Customer customer=new Customer();
    			customer.setName(req.getParameter("name"));
    			customer.setGender(req.getParameter("gender"));
    			customer.setPaswword(req.getParameter("password"));
    			customer.setDob(date);
    			customer.setEmail(email);
    			customer.setMobile(mobile);
    			dao.save(customer);
    			
    			Customer customer2=dao.check(email).get(0);
    			
    			resp.getWriter().print("<h1>Account Created Successfully</h1>");
    			
    			if(customer2.getGender().equals("male"))
    				resp.getWriter().print("<h1>Hello Sir</h1>");
    				
    			else
    				resp.getWriter().print("<h1>Hello Mam</h1>");
    			resp.getWriter().print("<h1>Your Customer Id is: "+customer2.getCust_id()+" </h1>");
    			req.getRequestDispatcher("home.html").include(req, resp);
    			
    		}
    		else{
    			resp.getWriter().print("<h1>Email or Phone Number is already Exists");
    			req.getRequestDispatcher("signup.html").include(req, resp);
    		}
    	
    	}
		
		
	}
	

}
