<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	//el,jstl 을 테스트 하기위한 숫자를 "jumsu" 라는 키 값으로 담기
	request.setAttribute("jumsu",75);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>
		획득한 점수는 <strong>${jumsu }</strong>입니다.
		<br/>
		점수는
		<c:if test="${jumsu%2 eq 0 }">짝수 입니다</c:if>
		<c:if test="${jumsu%2 eq 1 }">홀수 입니다.</c:if>
		<br/>
		점수는
		<c:choose>
			<c:when test="${jumsu%2 eq 0 }">
				짝수입니다.
			</c:when>
			<c:otherwise>
				홀수입니다.
			</c:otherwise>
		</c:choose>
		<br/>
		따라서 이번 학기 학점은
		<c:choose>
			<c:when test="${jumsu >=90 }">A</c:when>
			<c:when test="${jumsu >=80 }">B</c:when>
			<c:when test="${jumsu >=70 }">C</c:when>
			<c:when test="${jumsu >=60 }">D</c:when>
			<c:otherwise>F</c:otherwise>
		</c:choose>
	</p>
</body>
</html>