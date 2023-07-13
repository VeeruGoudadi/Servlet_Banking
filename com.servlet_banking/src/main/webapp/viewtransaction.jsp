<%@page import="com.servlet_banking_dto.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="com.servlet_banking_dto.BankAccount"%>
<%@page import="com.servlet_banking_dao.Bankdao"%>
<%@page import="com.servlet_banking_dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Transaction</title>
</head>
<body>

<% Customer customer=(Customer)session.getAttribute("customer");
   if(customer==null)
   {
	   response.getWriter().print("<h1>Session Expired Long Again</h1>");
	   request.getRequestDispatcher("login.html").include(request, response);
   }
   
   else{
	   %>
	   <%
	   long acno=(Long)session.getAttribute("acno");
	   Bankdao bankdao=new Bankdao();
	   BankAccount account=bankdao.find(acno);
	   List<BankTransaction> list=account.getTransactions();
	   %>
	   
	   <h1>Account Number:<%=acno %></h1><br>
	   <h1>Account Type:<%=account.getType() %></h1><br>
	   
	   <table border="1">
	   <tr>
	     <tr>
	     <th>Transaction_Id</th>
	     <th>Deposit</th>
	     <th>Withdraw</th>
	     <th>Balance</th>
	     <th>Time</th>
	     </tr>
	   <%for(BankTransaction transaction:list) {%>
	   <tr>
	     <th><%=transaction.getId() %></th>
	     <th>Rs.<%=transaction.getDeposit() %></th>
	     <th>Rs.<%=transaction.getWithdraw() %></th>
	     <th>Rs.<%=transaction.getBalance() %></th>
	     <th><%=transaction.getDateTime() %></th>
	     </tr>
	     <%} %>
	   </table>
	   <br>
	   <br>
	   <a href="accounthome.jsp"><button>Back</button></a>
     <%} %>

</body>
</html>