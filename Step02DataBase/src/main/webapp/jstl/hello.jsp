<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:forEach var="i" begin="0" end="4"> <%-- var = 변수값 지정 , begin = 변수 시작값,end = 변수 마지막값 --%>
		<p>
			Hello JSTL <strong> ${i }</strong>
		</p>
	</c:forEach>
</body>
</html>