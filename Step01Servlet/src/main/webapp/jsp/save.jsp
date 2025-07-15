<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//1.form 전송되는 숫자를 읽어와서 실제 점수로 만들어 준다

//2.해당 숫자가 짝수면 "전송한 숫자가 x 는 짝수 입니다.
//3.해당 숫자가 짝수면 "전송한 숫자가 x 는 홀수 입니다.
//콘솔창이 아닌 클라이언트 웹브라우저에 출력하는 프로그래밍 해보기

int Num = Integer.parseInt(request.getParameter("inputNum"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/save.jsp</title>
</head>
<body>
	<%
	if (Num % 2 == 0) {
	%>
	<p><%=Num%>은 짝수입니다
	</p>
	<%
	}
	%>
	<%
	if (Num % 2 != 0) {
	%>
	<p><%=Num%>은 홀수입니다
	</p>
	<%
	}
	%>

	<h3>3항 연산자 이용</h3>
	<p><%=Num%2==0 ? "짝수":"홀수"%>입니다</p>

</body>
</html>