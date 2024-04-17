<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="게시글 등록" name="title"/>

</jsp:include>

	<form action="/board/insert" method="post">
		<label>제목 :
			<input type="text" name="title">
		</label>
		<label> 내용 :
			<textarea rows="40" cols="40" name="content"></textarea>
		</label>
		<input type="hidden" name="writer" value='${member.id }'>
		<div style="color:red;">${msg}</div>
		<button type="submit">등록</button>
		<button type="button">취소</button>
	</form>
<jsp:include page="/WEB-INF/views/common/footer.jsp"></jsp:include>
