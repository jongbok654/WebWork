<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/home.jsp</title>
</head>
<body>
	<div class="container">
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a href="${pageContext.request.contextPath}/member/list">회원 목록보기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/insert">회원 추가하기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/delete">회원 삭제하기</a></li>
			<li><a href="${pageContext.request.contextPath}/member/update">회원 수정하기</a></li>
		</ul>
		<h2>공지사항</h2>
		<ul>
			<c:forEach var="tmp" items="${notice }">
				<li>${tmp }</li>
			</c:forEach>
			
		</ul>
	</div>
</body>
</html>