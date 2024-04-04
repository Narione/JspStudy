<%@page import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
hello
<!-- Scriptlet 스크립틀릿 -->
<% 
	LocalDateTime now = LocalDateTime.now();
%>
<!-- 표현식(Expression) -->
<%=now %>
</body>
</html>