<%@page import="test2.dto.BookDto"%>
<%@page import="test2.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BookDao dao = new BookDao();
BookDto dto = new BookDto();


int num = Integer.parseInt(request.getParameter("num"));
String title = request.getParameter("title");
String author = request.getParameter("author");
String publisher = request.getParameter("publisher");

dto.setNum(num);
dto.setTitle(title);
dto.setAuthor(author);
dto.setPublisher(publisher);

boolean isSuccess = dao.update(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book/update.jsp</title>
</head>
<body>
<p>
		<%
		if (isSuccess) {
		%>

		<strong><%=num%></strong>번 째 회원의 정보를 수정했습니다.
			<a href="list.jsp">확인</a>
		</p>
		<%
		} else {
		%>
		<p>수정 실패! <a href="${pageContext.request.contextPath }/book/updateform.jsp?num=<%=num%>">다시 수정하기</a></p>
		<%
		}
		%>
</body>
</html>