<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/edit-password.jsp</title>
</head>
<body>
	<div class="container">
		<h1>비밀번호 수정 양식</h1>
		<form action="update-password.jsp" method="post" id="editForm">
		<div>
			<label for="password">기존비밀번호</label> <input type="password"
				name="password" id="password" />
		</div>
		<div>
			<label for="newpassword">새 비밀번호</label> 
			<input type="password" name="newPassword" id="newPassword" />
		</div>
		<div>
			<label for="newPassword2">새 비밀번호 확인</label> 
			<input type="password" " id="newPassword2" /> <!-- name을 뻄으로써 제출X -->
		</div>
		<button type="submit">수정하기</button>
		</form>
	</div>
	<script>
		//id가 editForm 인 요소에 "submit" 이벤트가 일어 났을 때 실행할 함수 등록
			document.querySelector("#editForm").addEventListener("submit",(e)=>{
			/*
				여기서 해야할 일
				-폼에 입력한 내용의 유효성을 검증한다
				검증해서 유효하다면(잘 입력했다면) 관여하지 않는다(자동으로 폼이 제출된다)
			유효하지 않다면 e.preventDefault(); 를 실행해서 폼 제출을 막는다
			*/
			//기존 비밀번호
			const pwd=document.queryselector("#password").value;
			//새 비밀번호
			const newPwd= document.queryselector("#newPassword").value;
			//새 비밀번호 확인
			const newPwd2=document.queryselector("#newPassword2").value;
			
			if(pwd.trim()==""){
				alert("기존 비밀번호를 입력하세요!");
				e.preventDefault();
			}
			else if(newPwd.trim()==""){
				alert("새로운 비밀번호를 입력하세요!");
				e.preventDefault();
			}
			else if(newPwd !=newPwd2){
				alert("비밀번호를 다시  확인해보세요!");
				e.preventDefault();
				
			}
			
			
			
		});
	</script>
	
</body>
</html>