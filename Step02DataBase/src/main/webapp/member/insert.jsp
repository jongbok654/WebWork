<%@page import="test.dto.MemberDto"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberDao dao = new MemberDao();
MemberDto dto = new MemberDto();

String name = request.getParameter("name");
String addr = request.getParameter("addr");

dto.setName(name);
dto.setAddr(addr);

boolean isSucces = dao.insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	if (isSucces) {
	%>

	<p>
		
		<strong><%=name%></strong>님의 정보를 성공적으로 저장했습니다
		<a href="${pageContext.request.contextPath }/member/list.jsp">확인</a>

	</p>
	<%
	} else {
	%>

	<p>저장 실패</p>
	<a href="${pageContext.request.contextPath }/member/insertform.jsp">다시 입력하기</a>
	<%
	}
	%>
	
	<%
	    //응답인 response의 sendRedirect메소드로 list.jsp로 페이지 전환됨.
	    //response.sendRedirect("list.jsp");
		
	%>

</body>
</html>