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

<%

    String data = (String) request.getAttribute("msg");

    out.println("<h1>" + data + "</h1>");

%>

<h1>

    <a href="Addproduct.html">Add Product Details</a>

    <br><br>

    <a href="view">View Product Details</a>

</h1>

</center>

</body>

</html>