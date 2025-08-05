<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//request 객체에 특정 key 값으로 담긴 정보 추출하기
	String orgFileName=(String)request.getAttribute("orgFileName");
	String saveFileName=(String)request.getAttribute("saveFileName");
	long fileSize=(long)request.getAttribute("fileSize");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/file-form.jsp</title>
</head>
<body>
	<div class="container">
		<h1>파일 업로드 테스트</h1>
		<form action="${pageContext.request.contextPath }/test/fileup" 
		enctype="multipart/form-data" method="post">
			<input type="text" name="caption" placeholder="설명입력..." />
			<br/>
			<input type="file" name="myFIle" />
			<br/>
			<button type="submit">업로드</button>
		</form>
	</div>
</body>
</html>