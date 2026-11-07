<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<center>
    <h1>Error Occurred!</h1>
    <h3><%= exception.getMessage() %></h3>
    <a href="Input.html">Try Again</a>
</center>
</body>
</html>