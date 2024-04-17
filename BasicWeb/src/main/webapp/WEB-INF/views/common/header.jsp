<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${param.title}</title>
<style>
	header form {
		text-align: right;
		padding-right: 100px;
	}
	
	
	label {
		display: block;
	}
	.error {
		color: red;
	}

</style>
</head>
<body>
<header>
	<c:choose>
		<c:when test="${not empty sessionScope.member}"><!-- member != null, member ne null -->
			<form action="/logout" method="get">
				<span id="loginName">${sessionScope.member.name}님</span>
				<button type="submit">로그아웃</button>
			</form>		
		</c:when>

		<c:otherwise>
			<form action="/login" method="get">
				<button type="submit">로그인</button>
			</form>
		</c:otherwise>
	
	</c:choose>
</header>