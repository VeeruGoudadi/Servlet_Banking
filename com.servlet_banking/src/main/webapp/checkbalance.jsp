<%@page import="com.servlet_banking_dto.BankAccount"%>
<%@page import="com.servlet_banking_dao.Bankdao"%>
<%@page import="com.servlet_banking_dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Balance</title>
</head>
<body>

<%Customer customer=(Customer)request.getSession().getAttribute("customer");
if(customer==null)
{
	response.getWriter().print("<h1>Session Expired Login Again");
	request.getRequestDispatcher("login.html").include(request, response);
	
}
else{
	%>
	<%
	long acno=(Long)session.getAttribute("acno");
	Bankdao bankdao=new Bankdao();
	BankAccount account=bankdao.find(acno);
	Customer customer2=account.getCustomer();
	%>
	
	<h1>Hello <%if(customer2.getGender().equals("male")) {%>Mr. <%}else{%>Ms. <%} %> <%=customer2.getName() %></h1>
<h1>Your <%=account.getType() %> account balance is <%=account.getAmount() %></h1>
<br><br>
<a href="accounthome.jsp"><button>Back</button></a>
<%} %>
	
</body>
</html>