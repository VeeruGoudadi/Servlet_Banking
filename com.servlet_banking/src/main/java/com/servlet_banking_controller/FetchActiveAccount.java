package com.servlet_banking_controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dto.BankAccount;
import com.servlet_banking_dto.Customer;

@WebServlet("/fetchactiveaccount")
public class FetchActiveAccount extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		
		if (customer==null) {
			resp.getWriter().print("<h1>Session login Again</h1>");
			req.getRequestDispatcher("login.hrml").include(req, resp);
			
		}
		else{
			List<BankAccount> list=customer.getAccounts();
			List<BankAccount> list2=new ArrayList<BankAccount>();
			for (BankAccount account : list) {
				
				if (account.isStatus()) {
					list2.add(account);
					
				}
			}
			req.setAttribute("list", list2);
			req.getRequestDispatcher("accounts.jsp").include(req, resp);
		}
	}

}
