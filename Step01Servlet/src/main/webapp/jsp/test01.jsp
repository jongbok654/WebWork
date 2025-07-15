<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
int num = 1;
String name = "박종복";
String addr = "오산";

List<String> names=List.of("박종복","박종복2","박종복3");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/test01.jsp</title>
</head>
<body>

	<h1>회원 한 명의 정보 입니다.</h1>
	<p>
		번호:<%=num %> <strong></strong>
	</p>
	<p>
		이름:<%=name %> <strong></strong>
	</p>
	<p>주소:<%=addr %><strong></strong></p>
	<ul>
	<!-- 중괄호까지는 java이고, <li></li>는 자바 스크립트이기 때문에 이렇게 표현해야함 -->
	<%for(int i=0;i<3;i++){%>
	<li>목록</li>
	<%} %>
	</ul>
	
	
	<br><br>
	
	
	<ul>
	<%for(String item:names){%>
	<li><%=item %></li>
	<% }%>
	</ul>
</body>
</html>