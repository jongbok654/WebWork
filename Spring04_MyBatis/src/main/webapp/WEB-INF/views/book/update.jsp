<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/book/update.jsp</title>
</head>
<body>
	<div class="container">
		<p>
			<strong>${param.title }</strong> 책의 정보를 수정했습니다.
			<a href="${pageContext.request.contextPath}/book/list">목록보기</a>
		</p>
	</div>
</body>
</html>