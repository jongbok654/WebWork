<%@page import="test.dto.MemberDto"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberDao dao = new MemberDao();
MemberDto dto = new MemberDto();

String Num = request.getParameter("num");

int num = Integer.parseInt(Num);


//한번에 하기
//int num = Integer.parseInt(request.getParameter("num"));
dao.deleteByNum(num);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/delete.jsp</title>
</head>
<body>
	<script>
		alert("삭제했습니다");
		location.href = "${pageContext.request.contextPath }/member/list.jsp"
	</script>
</body>
</html>