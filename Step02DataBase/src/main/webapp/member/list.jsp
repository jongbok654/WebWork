<%@page import="test.dto.MemberDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
MemberDao dao = new MemberDao();
List<MemberDto> list = new MemberDao().selectAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<!-- 복사한 bootstrap css 로딩시키기 -->
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="member" name="thisPage" />
	</jsp:include>

	<div class="container">
		<a href="${pageContext.request.contextPath }/member/insertform.jsp">회원
			추가</a>
		<h1>회원 목록</h1>

		<table class="table table-striped">
			<thead class="table-dark">
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>주소</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>




			<%
			for (MemberDto dto : list) {
			%>
			<tr>
				<td><%=dto.getNum()%></td>
				<td><%=dto.getName()%></td>
				<td><%=dto.getAddr()%></td>
				<td><a
					href="${pageContext.request.contextPath }/member/updateform.jsp?num=<%=dto.getNum()%>">수정</a></td>
				<td><a href="javascript:" class="deleteLink"
					data-num="<%=dto.getNum()%>">삭제</a></td>
				<!-- 사용자 정의 변수 -->
			</tr>


			<%
			}
			%>

		</table>

	</div>
	<jsp:include page="/WEB-INF/include/footer.jsp"></jsp:include>

	<script>
		document.querySelectorAll(".deleteLink").forEach(tmp=>{
		tmp.addEventListener("click",(e)=>{
	//e.target 은 이벤트가 발생한 그 요소의 참조값
	//click 이벤트가 일어난 a 요소의 data-num 속성의 value 를 읽어오는법
	const num=e.target.getAttribute("data-num");
	const isDelete=confirm(num+" 번쨰 회원 삭제 하시겠습니까?");
	//delete.jsp 페이지로 이동하게 하면서 삭제할 회원의 번호도 같이 전달되도록하기
	
	if(isDelete){
		location.href="${pageContext.request.contextPath }/member/delete.jsp?num="+num;	
	}
	});
});
</script>
</body>
</html>