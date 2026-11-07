
<%@ page import="com.Bean.UserBean" %>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
</head>

<body>

<center>
<h1>

<%
UserBean ub = (UserBean)application.getAttribute("ubean");

String fname = ub.getFIRSTNAME();

out.println("Welcome " + fname + "<br><br>");
%>

<a href="ViewProfile.jsp">View Profile</a>
<br><br>

<a href="logout">Logout</a>

</h1>
</center>

</body>
</html>