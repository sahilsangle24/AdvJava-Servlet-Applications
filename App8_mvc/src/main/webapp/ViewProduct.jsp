<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.bean.ProductBean" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>View Product</title>

</head>

<body>

<center>

<h1>Product Details</h1>

<table border="1" cellpadding="10" cellspacing="0">

    <tr>

        <th>Product Code</th>
        <th>Product Name</th>
        <th>Product Company</th>
        <th>Product Price</th>
        <th>Product Quantity</th>

    </tr>

<%

    ArrayList<ProductBean> al =
        (ArrayList<ProductBean>) request.getAttribute("data");

    if (al.size() == 0)
    {
        out.println("<tr><td colspan='5'>There are No data found</td></tr>");
    }
    else
    {
        Iterator<ProductBean> i = al.iterator();

        while (i.hasNext())
        {
            ProductBean pb = i.next();

%>

    <tr>

        <td><%= pb.getProductCode() %></td>

        <td><%= pb.getProductName() %></td>

        <td><%= pb.getProductCompany() %></td>

        <td><%= pb.getProductPrice() %></td>

        <td><%= pb.getProductQuantity() %></td>

    </tr>

<%

        }
    }

%>

</table>

</center>

<br><br>

<jsp:include page="index.html" />

</body>

</html>