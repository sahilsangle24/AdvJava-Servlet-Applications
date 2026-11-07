<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.bean.EmpBean" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>View Employee</title>

</head>

<body>

<center>

<h1>Employee Details</h1>

<table border="1" cellpadding="10" cellspacing="0">

    <tr>
        <th>Employee ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Salary</th>
        <th>Address</th>
    </tr>

<%

    ArrayList<EmpBean> al =
        (ArrayList<EmpBean>) request.getAttribute("data");

    if (al.size() == 0)
    {
        out.println("<tr><td colspan='5'>There are No data found</td></tr>");
    }
    else
    {
        Iterator<EmpBean> i = al.iterator();

        while (i.hasNext())
        {
            EmpBean eb = i.next();

%>

    <tr>
        <td><%= eb.getEmpId() %></td>
        <td><%= eb.getEmpFname() %></td>
        <td><%= eb.getEmpLname() %></td>
        <td><%= eb.getEmpSal() %></td>
        <td><%= eb.getEmpAddr() %></td>
    </tr>

<%
        }
    }

%>

</table>

</center><br><br>

<jsp:include page="index.html" />

</body>

</html>