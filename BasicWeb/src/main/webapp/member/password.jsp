<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 수정</title>
<style>
	label {
		display: block;
	}
	.error {
		color: red;
	}
</style>
</head>
<body>
<h1>비밀번호 수정</h1>
<form action="/member/password" method="post" id="passwordForm"> 
	<input type="hidden" name="id" readonly="readonly" value="${deletedId }">
	<label>현재 비밀번호: 
		<input type="password" name="password">
	</label>
	<label>수정 비밀번호: 
		<input type="password" name="modiPw" id="modiPw">
	</label>
	<label>수정 비밀번호 확인: 
		<input type="password" name="confirmedPw" id="confirmedPw">
	</label>
	<button type="submit">저장</button>
	<button type="button">취소</button>

</form>
<script>
	const passwordForm = document.querySelector("#passwordForm")
	 passwordForm.addEventListener("submit", function(e){
		 e.preventDefault();
		 const modiPw = document.querySelector("#modiPw");
		 const confirmedPw = document.querySelector("#confirmedPw");
		 if(modiPw.value == confirmedPw.value){
			 this.submit();
		 }else{
			 const span = document.createElement("span");
			 span.classList.add("error")
			 span.textContent="비밀번호가 일치하지 않습니다."
			 confirmedPw.parentNode.appendChild(span);
			 confirmedPw.select();
		 }
		 
	 })
</script>
</body>
</html>