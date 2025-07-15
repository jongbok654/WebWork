<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
//여기는 Java Coding영역임.
//서블릿의 service() 메소드 안쪽 영역이라 생각하면 쉬움

String url = request.getRemoteHost();

//입력값 추출
String name = request.getParameter("name");
String msg = request.getParameter("msg");

System.out.println(url + ":" + name + "  :  " + msg);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>send.jsp</title>
</head>
<body>
	<h1>Jsp 응답합니다.</h1>
	<p>보낸 사람 이름:<%=name %></p>  
	<p>보낸 메시지:<%=msg %></p>
</body>
</html>