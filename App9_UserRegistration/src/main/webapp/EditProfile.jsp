
<%@ page import="com.Bean.UserBean" %>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Profile</title>
</head>

<body>

<center>
<h1>

<%
    String fname = (String) request.getAttribute("fname");
    UserBean ub = (UserBean) application.getAttribute("ubean");

    out.println("Edit your profile <br><br>");
%>

<form action="update" method="post">

    First Name:
    <input type="text" name="ufname" value="<%= ub.getFIRSTNAME() %>">
    <br><br>

    Last Name:
    <input type="text" name="ulname" value="<%= ub.getLASTNAME() %>">
    <br><br>

    EmailId:
    <input type="text" name="umailid" value="<%= ub.getMAILID() %>">
    <br><br>

    Phone:
    <input type="text" name="uphone" value="<%= ub.getPHONE() %>">
    <br><br>

    <input type="submit" value="Update">

</form>

</h1>
</center>

</body>
</html>
