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
<title>/test/종복.jsp</title>
</head>
<body>
	<div class="container">
		<p>종복이 응답함</p>
		<p>원본 파일명:<%=orgFileName %></p>
		<p>저장된 파일명 : <%=saveFileName %></p>
		<p>파일의 크기 : <%=fileSize %></p>
		
		<%--el을 이용하면 casting 필요없이 바로 추출할 수도 있다 --%>
		<p>원본 파일명: ${requestScope.orgFileName }</p>
		<p>저장된 파일명: ${requestScope.saveFileName }</p>
		<%--requestScope. 은 생략 가능 --%>
		<p>파일의 크기 : ${fileSize }</p>
		
		<a href="${pageContext.request.contextPath }/test/download?orgFileName=${orgFileName}&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a>
		
	</div>
</body>
</html>