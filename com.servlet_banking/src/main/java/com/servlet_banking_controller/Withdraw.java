package com.servlet_banking_controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.servlet_banking_dao.Bankdao;
import com.servlet_banking_dto.BankAccount;
import com.servlet_banking_dto.BankTransaction;
import com.servlet_banking_dto.Customer;

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer=(Customer) req.getSession().getAttribute("customer");
		
		if(customer==null)
		{
			resp.getWriter().print("<h1>Session Expired Login Again</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
			
		}
		else{
			double amt=Double.parseDouble(req.getParameter("amt"));
			long acno=(Long)req.getSession().getAttribute("acno");
			
			Bankdao bankdao=new Bankdao();
			
			BankAccount account=bankdao.find(acno);
			
			if(amt>account.getAmount())
			{
				resp.getWriter().print("<h1>Insufficient Balance</h1>");
				req.getRequestDispatcher("accounthome.jsp").include(req, resp);
			}
			
			else{
				if(amt>account.getAclimit())
				{
					resp.getWriter().print("<h1>Out of Limit enter amount within"+account.getAclimit()+"  </h1>");
					req.getRequestDispatcher("accounthome.jsp").include(req, resp);
				}
				else{
					account.setAmount(account.getAmount()-amt);
					
					BankTransaction bankTransaction=new BankTransaction();
					bankTransaction.setDeposit(0);
					bankTransaction.setWithdraw(amt);
					bankTransaction.setBalance(account.getAmount());
					bankTransaction.setDateTime(LocalDateTime.now());
					
					List<BankTransaction> list=account.getTransactions();
					list.add(bankTransaction);
					
					account.setTransactions(list);
					
					bankdao.update(account);
					
					resp.getWriter().print("<h1>Amount withdrawed Successfully</h1>");
					req.getRequestDispatcher("accounthome.jsp").include(req, resp);
					
				}
			}
		}
	}

}
