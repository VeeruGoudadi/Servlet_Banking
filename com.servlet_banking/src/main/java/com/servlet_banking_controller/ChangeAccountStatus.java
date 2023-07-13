package com.servlet_banking_controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Bankdao;
import com.servlet_banking_dto.BankAccount;

@WebServlet("/changestatus")
public class ChangeAccountStatus extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long acno=Long.parseLong(req.getParameter("acno"));
		Bankdao bankdao=new Bankdao();
		BankAccount account=bankdao.find(acno);
		
		if(account.isStatus())
		{
			account.setStatus(false);
		}
		else{
			account.setStatus(true);
		}
		
		bankdao.update(account);
		
		resp.getWriter().print("<h1>Update Success</h1>");
		req.setAttribute("list", bankdao.fetchAll());
		req.getRequestDispatcher("adminhome.jsp").include(req, resp);
	}

}
