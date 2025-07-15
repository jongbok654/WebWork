<%@page import="org.mindrot.jbcrypt.BCrypt"%>
<%@page import="test.dto.UserDto"%>
<%@page import="test.dao.UserDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//폼 전송되는 아이디와 비밀번호 추출하기
String userName = request.getParameter("userName");
String password = request.getParameter("password");

//아이디 비밀번호가 유효한 정보인지 여부
boolean isValid=false;

//Db에서 userName을 이용해서 select 되는 정보가 있는지 select 해본다.
UserDto dto= new UserDao().getByUserName(userName);
//만일 select 딘 정보가 있다면(최소한 userName 은 존재한다는 것)
if(dto !=null){
	//raw 비밀번호와 DB에 저장된 암호화된 비밀번호를 비교해서 일치 하는지 확인한다.
	//Bcrypt.checkpw(입력한 비밀번호,암호화된 비밀벊)
	isValid = BCrypt.checkpw(password, dto.getPassword());
	
}
/*
	만일 입력한 아이디와 비밀번호가 유효한 정보라면 로그인 처리를 한다
	jsp 에서 기본 제공해주는 HttpSession 객체에 userName 을 저장한다
	HttpSession 객체에 저장하면 응답을 한 이후에 다음번 요청에서 HttpSession 객체에 저장된 정보를 읽어올 수 있다.
	세션 객체에 담긴 정보는 어떤 요청도 하지 않고 일정 시간이 흐름녀 자동 삭제한다.
	필요하다면(로그아웃이 요청되면) 강제로 세션 객체에 담긴 정보를 삭제할 수도 있다.
	세션 격체에 담긴 로그인 정보를 삭제하는 것이 "로그아웃" 이고
	세션 객체에 로그이니 정보(userName)를 저장하는 것이 "로그인"이다.
*/
if(isValid){
	//HttpSession 객체에 "userName" 이라는 키 값으로 userName을 저장한다.
	session.setAttribute("userName",userName);
	//세션 유지시간 설정(초단위)
	session.setMaxInactiveInterval(60*10);
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/login.jsp</title>
</head>
<body>
<div class="container">
	<%if(isValid){ %>
	<p>
		<strong><%=userName %></strong>회원님 로그인 되었습니다.
		<a href="${pageContext.request.contextPath }/">인덱스 페이지로</a>
	</p>
	<%}else{ %>
	<p>
		아이디 혹은 비밀번호가 틀려요
		<a href="loginform.jsp">다시 로그인</a>
	</p>
	<%} %>
</div>

</body>
</html>