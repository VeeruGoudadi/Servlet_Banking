<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Home</title>
<style>
    body{
        background-color: aquamarine;
    }
    div{
        margin-left: 35%;
    }
    #a{
        background-color: rgb(53, 227, 53);
        border-radius: 10px;
        width: 150px;
        height: 30px;
    }
    #b{
        background-color: red;
        border-radius: 10px;
        width: 150px;
        height: 30px;
    }
    #c{
        background-color: goldenrod;
        border-radius: 10px;
        width: 150px;
        height: 30px;
    }
    #d{
        background-color: darkgrey;
        border-radius: 10px;
        width: 150px;
        height: 30px;
    }
    #e{
        background-color: antiquewhite;
        border-radius: 10px;
        width: 150px;
        height: 30px;
    }
    h1{
        margin-left: 30%;
    }
</style>
</head>
<body>
    <h1>Welcome To Account Home</h1>
    <div>
<a href="deposit.html"><button id="a">Deposit</button></a><br><br>
<a href="withdraw.html"><button id="b">Withdraw</button></a><br><br>
<a href="checkbalance.jsp"><button id="c">Check Balance</button></a><br><br>
<a href="viewtransaction.jsp"><button id="d">View Transaction</button></a><br><br>
<a href="logout"><button id="e">Logout</button></a><br><br>
</div>
</body>
</html>