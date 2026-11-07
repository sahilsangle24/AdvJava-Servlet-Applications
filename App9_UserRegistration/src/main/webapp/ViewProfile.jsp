
<%@ page import="com.Bean.UserBean" %>
<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Profile</title>
</head>

<body>

<center>
<h1>

<%
    String fname = (String) request.getAttribute("fname");
    UserBean ub = (UserBean) application.getAttribute("ubean");

    out.println("This session belongs to " + fname + "<br><br>");
    out.println("<u>Your Profile</u><br><br>");

    String pwd = ub.getPASSWORD();

    String cpwd = pwd.substring(0, 1) + "*****"
                + pwd.substring(pwd.length() - 1);

    out.println(
        ub.getUSERNAME() + " " +
        cpwd + " " +
        ub.getFIRSTNAME() + " " +
        ub.getLASTNAME() + " " +
        ub.getMAILID() + " " +
        ub.getPHONE() +
        "<br><br>"
    );
%>

<a href="edit">Edit Profile</a>
<br><br>

<a href="logout">Logout</a>

</h1>
</center>

</body>
</html>
