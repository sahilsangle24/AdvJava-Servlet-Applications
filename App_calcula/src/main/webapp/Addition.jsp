<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Addition</title>
</head>
<body>
<center>
<h1>
<%
    
   int num1 = Integer.parseInt(request.getParameter("num1"));
   int num2 = Integer.parseInt(request.getParameter("num2"));

   int sum = num1 + num2;
   
   
   out.println("<h1>Sum of " + num1 + " and " + num2 + " is: " + sum + "</h1>");
   
   out.println("<h2>Date and Time: " + request.getParameter("data") + "</h2>");
   
%>
</h1>
</body>
</html>