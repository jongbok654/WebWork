<%@page import="test.dto.MemberDto"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberDao dao = new MemberDao();
MemberDto dto = new MemberDto();

int num = Integer.parseInt(request.getParameter("num"));
String name = request.getParameter("name");
String addr = request.getParameter("addr");

dto.setNum(num);
dto.setName(name);
dto.setAddr(addr);

boolean isSuccess = dao.update(dto);

//응답인 response의 sendRedirect메소드로 list.jsp로 페이지 전환하는 방법도 있음
//response.sendRedirect("list.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-LN+7fdVzj6u52u30Kp6M/trliBMCMKTyK833zpbD+pXdCLuTusPj697FH4R/5mcr"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.13.1/font/bootstrap-icons.min.css">
</head>
<body>
	<div class="container">

		<%
		if (isSuccess) {
		%>
		<p class="alert alert-success mt-5">
			<i class="bi bi-check-circle-fill"></i> <strong><%=num%></strong>번 째
			회원의 정보를 수정했습니다. 
			<a class="alert-line" href="${pageContext.request.contextPath }/member/list.jsp">확인</a>
		</p>
		<%
		} else {
		%>
		<p class="alert alert-danger mt-5">
			수정 실패! 
			<a href="${pageContext.request.contextPath }/member/updateform.jsp?num=<%=num%>">
			다시수정하기</a>
		</p>
		<%
		}
		%>

	</div>

</body>
</html>