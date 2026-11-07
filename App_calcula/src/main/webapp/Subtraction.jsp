<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subtraction</title>
</head>
<body>
<center>
<h1>
<%

int num1 = Integer.parseInt(request.getParameter("num1"));
int num2 = Integer.parseInt(request.getParameter("num2"));

int sub = num1 - num2;

out.println("<h1>Subtraction of " + num1 + " and " + num2 + " is: " + sub + "</h1>");

out.println("<h2>Date and Time: " + request.getParameter("data") + "</h2>");

%>


</h1>



</body>
</html>