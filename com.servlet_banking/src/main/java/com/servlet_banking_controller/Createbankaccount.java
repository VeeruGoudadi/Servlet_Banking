package com.servlet_banking_controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Bankdao;
import com.servlet_banking_dao.Customerdao;
import com.servlet_banking_dto.BankAccount;
import com.servlet_banking_dto.Customer;
@WebServlet("/createbankaccount")
public class Createbankaccount extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String banktype=req.getParameter("banktype");
		
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		List<BankAccount> list=customer.getAccounts();
		boolean flag=true;
		
		for (BankAccount account : list) {
			if (account.getType().equals(banktype)) {
				flag=false;
				break;
				
			}
		}
		if (flag) {
			BankAccount account=new BankAccount();
			account.setType(banktype);
			
			if (banktype.equals("saving")) {
				account.setAclimit(10000);
				
			}
			else{
				account.setAclimit(50000);
			}
			account.setCustomer(customer);
			
			Bankdao bankdao=new Bankdao();
			bankdao.save(account);
			
			list.add(account);
			
			customer.setAccounts(list);
			Customerdao customerdao=new Customerdao();
			customerdao.update(customer);
			
			resp.getWriter().print("<h1>Account Created Successfully waiting for Management to approve your account</h1");;
			req.getRequestDispatcher("login.html").include(req, resp);
		}
		else{
			resp.getWriter().print("<h1>"+banktype+"Account Already Exists</h1>");
			req.getRequestDispatcher("customerhome.html").include(req, resp);
		}
	}

}
