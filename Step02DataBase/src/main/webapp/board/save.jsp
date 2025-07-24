<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//폼 전송되는 title 과 content
String title = request.getParameter("title");
String content = request.getParameter("content");
//글 작성자는 세션 객체로 부터 얻어낸다.
String writer = (String) session.getAttribute("userName");
//DB 에 저장하기
BoardDto dto = new BoardDto();
dto.setWriter(writer);
dto.setTitle(title);
dto.setContent(content);

//글 번호를 미리 얻어내기
int num = BoardDao.getInstance().getSequence();
//글 번호를 dto에 담는다
dto.setNum(num);
//응답하기
boolean isSuccess = BoardDao.getInstance().insert(dto);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/save.jsp</title>
</head>
<body>
	<div class="contanier">
		<%
		if (isSuccess) {
		%>
		<script>
		
			alert("저장했습니다");
			location.href="view.jsp?num=<%=num%>
			";
		</script>
		<%
		} else {
		%>
		<p>
			글 저장 실패! <a href="new-form.jsp">다시작성</a>
		</p>
		<%
		}
		%>

		



</body>
</html>