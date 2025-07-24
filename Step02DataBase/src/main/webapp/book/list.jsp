<%@page import="test.dao.BookDao"%>
<%@page import="java.util.List"%>
<%@page import="test.dto.BookDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
BookDao dao = new BookDao();
List<BookDto> list = dao.selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book/list.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="book" name="thisPage"/>
	</jsp:include>
	<div class="container">
		<a href="${pageContext.request.contextPath }/book/insertform.jsp">책 추가</a>
		<h1>책 목록</h1>
		<table>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>저자</th>
				<th>출판사</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>




			<%
			for (BookDto dto : list) {
			%>
			<tr>
				<td><%=dto.getNum()%></td>
				<td><%=dto.getTitle()%></td>
				<td><%=dto.getAuthor()%></td>
				<td><%=dto.getPublisher()%></td>
				<td><a
					href="${pageContext.request.contextPath }/book/updateform.jsp?num=<%=dto.getNum()%>">수정</a></td>
				<td><a
					href="${pageContext.request.contextPath }/book/delete.jsp?num=<%=dto.getNum() %>">삭제</a></td>
			</tr>


			<%
			}
			%>
		</table>

	</div>
	
</body>
</html>