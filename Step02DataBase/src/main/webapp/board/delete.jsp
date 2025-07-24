<%@page import="test.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//GET 방식 파라미터로 전달되는 글 번호를 읽어와서
    	int num=Integer.parseInt(request.getParameter("num"));
    
    	//글 작성자와 로그인된 userName 이 동일한지 비교해서 동일하지 않으면 에러를 응답한다
    	String writer=BoardDao.getInstance().getByNum(num).getWriter();
    	String userName=(String)session.getAttribute("userName");
    	//만일 글작성자와 로그인된 userName 하고 같지 않다면
    	if(!writer.equals(userName)){
    		//에러 페이지 응답
    		response.sendError(HttpServletResponse.SC_FORBIDDEN,"남의 글은 지울 수 없습니다.");
    		return;//메소드가 여기서 종료됨
    	}
    	
    	//DB에서 해당 글을 삭제하고
    	BoardDao.getInstance().deleteByNum(num);
    	
    	//응답한다
    	
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/delete.jsp</title>
</head>
<body>

</body>
</html>