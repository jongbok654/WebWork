<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
//폼 전송하는 수정할 회원의 정보 추출하기
String nick = request.getParameter("nick");
String gender = request.getParameter("gender");
String job = request.getParameter("job");
String comment = request.getParameter("comment");
//취미 정보는 넘지 않을 수도 있고 1개에서 3개까지 넘어올 수도 있음
String[] hobbys = request.getParameterValues("hobby");

System.out.println("nick" + nick);
System.out.println("gender" + gender);
System.out.println("job" + job);
System.out.println("comment" + comment);
System.out.println(hobbys);

if (hobbys != null) {
	for (String tmp : hobbys) {
		System.out.println("hobby:" + tmp);
	}
}

//다운로드에서 lib 폴더에 넣은 gson.xxx.jar 파일에 포함된 클래스로 생성된 객체를 이용해서
//String[]에 저장된 문자열을 json 문자열로 변환하기

//여기서 result를 선언해줘야함 이유는 선언하지 않으면 result는 if문 안에만 있는 죽은변수이기 때문에
//지역변수로 result를 선언해놔야 아래 html쪽에서 사용할 수 있음!
String result = "";
if (hobbys != null) {
	Gson gson = new Gson();
	result = gson.toJson(hobbys);
	//gson.tojson 자체가 String 타입을 반환하기 때문에 
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/update.jsp</title>
</head>
<body>
	<h1>수정했습니다</h1>
<p><%=result %></p>

</body>
</html>