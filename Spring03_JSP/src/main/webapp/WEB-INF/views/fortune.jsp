<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/WEB-INF/views/fortune.jsp</title>
</head>
<body>
	<div class="contanier">
		<p>
		<%--
			아래의 링크를 눌렀을 때
			1.FortuneController 의 특정 메소드가 동작하고
			2.
			3.
		 --%>
			오늘의 운세:<strong>${fortuneToday }</strong>
			<a href="${pageContext.request.contextPath}/">인덱스로</a>
		</p>
	</div>
</body>
</html>