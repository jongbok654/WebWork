<%@page import="test.dao.CommentDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //삭제할 댓글 번호
    int num=Integer.parseInt(request.getParameter("num"));
    //리다일렉트 이동할 때 필요한 원글의 글 번호
    String parentNum=request.getParameter("parentNum");
    //dao 객체를 이용해서 삭제하고 
    CommentDao.getInstance().delete(num);
    //리다일렉트 이동
    String cPath=request.getContextPath();
    response.sendRedirect(cPath+"/board/view.jsp?num="+parentNum);
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>