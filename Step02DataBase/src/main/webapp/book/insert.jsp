<%@page import="test2.dto.BookDto"%>
<%@page import="test2.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BookDao dao = new BookDao();
BookDto dto = new BookDto();

String title = request.getParameter("title");
String author = request.getParameter("author");
String publisher = request.getParameter("publisher");

dto.setTitle(title);
dto.setAuthor(author);
dto.setPublisher(publisher);

boolean isSucces = dao.insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book/insert.jsp</title>
</head>
<body>
<%
	if (isSucces) {
	%>

	<p>
		
		<strong><%=author%></strong>님의 정보를 성공적으로 저장했습니다
		<a href="${pageContext.request.contextPath }/book/list.jsp">확인</a>

	</p>
	<%
	} else {
	%>

	<p>저장 실패</p>
	<a href="${pageContext.request.contextPath }/book/insertform.jsp">다시 입력하기</a>
	<%
	}
	%>
	
	<%
	    //응답인 response의 sendRedirect메소드로 list.jsp로 페이지 전환됨.
	    //response.sendRedirect("list.jsp");
		
	%>

</body>
</html>