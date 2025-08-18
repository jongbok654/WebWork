<%@page import="java.util.ArrayList"%>
<%@page import="org.eclipse.tags.shaded.org.apache.xalan.xsltc.dom.ArrayNodeListIterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%--
	JSTL 사용 설정하기
	uri = "import 할 경로"
	prefix="접두어"
 --%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<% 
	//테스르를 위해 sample 데이터를 request scope 에 담는다
	List<String> names=new ArrayList<>();
	names.add("박종복");
	names.add("해골");
	names.add("원숭이");
	//"list" 라는 키 값으로 request scope 에 ArrayList 객체 담아두기
	request.setAttribute("list", names);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl/test01.jsp</title>
</head>
<body>
<%
	//request scope에 "list" 라는 키 값으로 저장된 친구 목록 얻어내기
	List<String> list= (List<String>)request.getAttribute("list");
%>
	<h1>친구목록</h1>
	<ul>
		<%for(String tmp:list){ %>
			<li><%=tmp %></li>
		<%} %>
	</ul>
	
	<h1>EL 과 JSTL 을 활용해서 위와 동일한 동작 해보기</h1>
	<ul> 
		<c:forEach var="tmp" items="${requestScope.list }">
			<li>${tmp }</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 인덱스 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>인덱스:${status.index }</strong></li>
		</c:forEach>
	</ul>
	<h1>친구 목록 순서 표시</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>${tmp } <strong>순서 : ${status.count }</strong></li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 첫번쨰 순서인지 여부</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>
			${tmp }
			<strong>첫번째 : ${status.first }</strong>
			<c:if test="${status.first }">
				제일 첫번째 친구
			</c:if>
			</li>
		</c:forEach>
	</ul>
	
	<h1>친구 목록 마지막 번째 순서인지 여부</h1>
	<ul>
		<c:forEach var="tmp" items="${list }" varStatus="status">
			<li>
			${tmp } 
			<strong>마지막 번째 : ${status.last }</strong>
			<c:if test="${status.last }">
				제일 마지막 친구
			</c:if>
			</li>
		</c:forEach>
	</ul>
	
</body>
</html>