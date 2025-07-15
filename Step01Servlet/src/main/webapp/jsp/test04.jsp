<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String cPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/test04.jsp</title>
</head>
<body>
	<h1></h1>
	<h1>context path 얻어내서 사용하기</h1>
	<a href="../index.html">인덱스 페이지</a>
	<a href="<%=cPath%>/index.html">getContextPath를 이용한 인덱스</a>

	<h1>Expression Language 를 활용 $(pageContext.request.contextPath)</h1>
	<p>EL 문 jsp 페이지에서 특별히 해석되는 언어이다 \${이 안에 작성하는 언어가 EL이다 }</p>

	<a href="${ pageContext.request.contextPath}/index.html">인덱스</a>
	<br>
	<br>
	<img src="${pageContext.request.contextPath }/images/Spain.png" />
	<script>
//javascript 에서 backtic 안에 ${}을 사용할 수 있따>
function pringMsg(msg){
	const result = `매개 변수에 전달된 내용:\${msg}`;
	console.log(result);
}
</script>
</body>
</html>