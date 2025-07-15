<%@page import="java.util.jar.Attributes.Name"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%
//DB에 저장되어 있었던 nick name 이라고 가정하자
String nickName = "종복";
String gender = "man";
String job = "developer";
//textarea 에 입력한 내용이 DB에 저장되어 있었다고 가정
String comment = "	날씨가 좋다 \n음 \n좋다 \t 들여쓰기";
//DB에 저장된 취미 정보라고 가정
String hobbys = "[\"game\",\"music\"]"; //     \을 이용해서  문자열에 "" 표시
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/jsp/test05.jsp</title>

<style>
fieldset {
	background-color: #F7E7CE;
}
</style>
</head>
<body>
	<h1>어떤 정보를 수정하는 폼</h1>
	<form action="update.jsp" method="get">
		<div>
			<label for="nick">닉네임</label> <input type="text" name="nick"
				id="nick" value="<%=nickName%>" />

		</div>

		<fieldset>
			<legend>성별</legend>



			<label> <input type="radio" name="gender" value="man"
				<%=gender.equals("man") ? "checked" : ""%> /> 남자
			</label> <label> <input type="radio" name="gender" value="woman"
				<%=gender.equals("woman") ? "checked" : ""%> />여자
			</label>
		</fieldset>
		<div>
			<label for="job">직업</label> <select name="job" id="job">
				<option value="student" <%=job.equals("student") ? "selected" : ""%>>학생</option>

				<option value="developer"
					<%=job.equals("student") ? "selected" : ""%>>개발자</option>

				<option value="etc" <%=job.equals("student") ? "selected" : ""%>>기타</option>

			</select>
		</div>
		<div>
			<label for="comment">하고 싶은 말</label>
			<textarea name="comment" id="comment" rows="5"> <%=comment%></textarea>
		</div>

		<fieldset>
			<legend>취미(여러개 선택 가능)</legend>
			<label> <input type="checkbox" name="hobby" value="game"
				<%=hobbys.contains("game") ? "checked " : ""%> />게임
			</label> <label> <input type="checkbox" name="hobby" value="sports"
				<%=hobbys.contains("sports") ? "checked" : ""%> />스포츠
			</label> <label> <input type="checkbox" name="hobby" value="music"
				<%=hobbys.contains("music") ? "checked" : ""%> />음악
			</label>

		</fieldset>


		<button type="submit">수정</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>