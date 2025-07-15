<%@page import="test2.dto.BookDto"%>
<%@page import="test2.dao.BookDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
BookDao dao = new BookDao();
BookDto dto = new BookDto();


//한번에 하기
int num = Integer.parseInt(request.getParameter("num"));
dao.deleteByNum(num);


%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>book/delete.jsp</title>
</head>
<body>
	<script>
		alert("삭제했습니다");
		location.href = "${pageContext.request.contextPath }/book/list.jsp"
	</script>

</body>
</html>