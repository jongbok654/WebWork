<%@page import="test.dao.UserDao"%>
<%@page import="test.dto.UserDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//get 방식ㅇ ㅏ라미터로 넘어오는 입력한 id 을 읽어온다
	String inputId=request.getParameter("inputId");
	//dao 를 이용해서 정보가 있는지 select 한다
	UserDto dto = UserDao.getInstance().getByUserName(inputId);
	
	boolean canUse = dto==null ? true: false;
%>
("canUse":<%=canUse %>;)
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/check-id.jsp</title>
</head>
<body>

</body>
</html>