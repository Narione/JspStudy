<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="글 조회" name="title"/>
</jsp:include>
<form action="/board/view" method="post">
	<a href="/board/update?id=${board.no }">수정</a>
	<a href="board/delete?id="${board.no } >삭제</a>
	
	<div>
		제목: ${board.title }
	</div>
	<div>
		작성자: ${board.writer }
	</div>
	<div>
		내용: ${board.content }
	</div>
</form>

<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>