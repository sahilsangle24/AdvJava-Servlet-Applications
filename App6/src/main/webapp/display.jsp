<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Product Details</title>
</head>

<body>

<center>

<h1>Product Details</h1>

<%
String p_name = request.getParameter("pname");
String p_id = request.getParameter("pid");
String p_price = request.getParameter("pprice");
String p_qty = request.getParameter("pqty");

out.println("<h2>Product Name : " + p_name + "</h2>");
out.println("<h2>Product ID : " + p_id + "</h2>");
out.println("<h2>Product Price : " + p_price + "</h2>");
out.println("<h2>Product Qty : " + p_qty + "</h2>");
%>

</body>

</html>