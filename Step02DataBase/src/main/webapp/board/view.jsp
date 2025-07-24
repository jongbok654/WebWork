<%@page import="test.dto.CommentDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.CommentDao"%>
<%@page import="org.apache.commons.text.StringEscapeUtils"%>
<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%


//get 방식 파라미터로 전달되는 글 번호 얻어내기
int num =Integer.parseInt(request.getParameter("num"));
//DB 에서 해당글의 자세한 정보를 얻어낸다.
BoardDto dto = BoardDao.getInstance().getByNum(num);

String userName = (String) session.getAttribute("userName");
//만일 본인 글 자세히 보기가 아니면 조회수를 1 증가 시킨다
if (!dto.getWriter().equals(userName)) {
	BoardDao.getInstance().addViewCount(num);
}

//댓글 목록을 DB 에서 읽어오기
List<CommentDto> commentList=CommentDao.getInstance().selectList(num);

//로그인 했는지 알아내보기
boolean isLogin= userName==null ? false : true;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/view.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
	<div class="container pt-3">
		<nav aria-label="breadcrumb">
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/">Home</a></li>
				<li class="breadcrumb-item"><a
					href="${pageContext.request.contextPath }/board/list.jsp">Board</a>
				</li>
				<li class="breadcrumb-item active">Detail</li>
			</ol>
		</nav>
		<h1>게시글 상세보기</h1>
		<div class="btn-group mb-2">
			<a
				class="btn btn-outline-secondary btn-sm <%=dto.getPrevNum() == 0 ? "disabled" : ""%>"
				href="view.jsp?num=<%=dto.getPrevNum()%>"> <i
				class="bi bi-arrow-left "></i> Prev
			</a> <a
				class="btn btn-outline-secondary btn-sm <%=dto.getNextNum() == 0 ? "disabled" : ""%>"
				href="view.jsp?num=<%=dto.getNextNum()%>"> Next <i
				class="bi bi-arrow-right"></i>
			</a>
		</div>

		<table class="table table-striped">
			<colgroup>
				<col class="col-2" />
				<col class="col" />
			</colgroup>
			<tr>
				<th>글번호</th>
				<td><%=num%></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					<%
					if (dto.getProfileImage() == null) {
					%> <i style="font-size: 100px;"
					class="bi bi-person-circle"></i> <%
 } else {
 %> <img
					src="${pageContext.request.contextPath }/upload/<%=dto.getProfileImage() %>"
					style="width: 100px; height: 100px; border-radius: 50%;" /> <%
 }
 %> <%=dto.getWriter()%>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td><%=StringEscapeUtils.escapeHtml4(dto.getTitle())%></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><%=dto.getViewCount()%></td>
			</tr>

			<tr>
				<th>작성일</th>
				<td><%=dto.getCreatedAt()%></td>
			</tr>
		</table>
		<%--
			클라이언트가 작성한 글 제목이나 내용을 그대로 클라이언트에게 출력하는 것은 javascript 주입 공격을 받을 수 있다
			따라서 해당 문자열은 escape 해서 출력하는 것이 안전하다
		 --%>

		<div class="card mt-4">
			<div class="card-header bg-light">
				<strong>본문 내용</strong>
			</div>
			<div class="card-body p-1">
				<%=dto.getContent()%>
			</div>
		</div>
		<%
		if (dto.getWriter().equals(userName)) {
		%>
		<div class="text-end pt-2">
			<a class="btn btn-warning btn-sm"
				href="edit.jsp?num=<%=dto.getNum()%>">Edit</a> <a
				class="btn btn-danger btn-sm"
				href="delete.jsp?num=<%=dto.getNum()%>">Delete</a>
		</div>
		<%
		}
		%>
		<%--댓글 입력칸 --%>
		<div class="card my-3">
			<div class="card-header bg-primary text-white">댓글을 입력해 주세요</div>
			<div class="card-body">
				<!-- 원글의 댓글을 작성할 폼 -->
				<form action="save-comment.jsp" method="post">
					<!-- 숨겨진 입력값 -->
					<input type="hidden" name="parentNum" value="<%=dto.getNum()%>" />
					<input type="hidden" name="targetWriter"
						value="<%=dto.getWriter()%>" />

					<div class="mb-3">
						<label for="commentContent" class="form-label">댓글 내용</label>
						<textarea id="commentContent" name="content" rows="5"
							class="form-control" placeholder="댓글을 입력하세요"></textarea>
					</div>

					<button type="submit" class="btn btn-success">등록</button>
				</form>
			</div>
		</div>
<!-- 댓글 몰록을 출력하기 -->
<div class="comments">
	<%for(CommentDto tmp:commentList){ %>
		<div class="card mb-3">               <!--flex-column이 flex-sm보다 더 우선순위임 그래서 동일한 조건일 경우 column이 적용됨-->
            <div class="card-body d-flex flex-column flex-sm-row ">
            	<%if(tmp.getProfileImage()==null) {%>
            	<i style="font-size:50px" class="bi bi-person-circle me-3 align-self-center"></i>
            		 
            	<%}else{ %>
                	<img src="${pageContext.request.contextPath }/upload/<%=tmp.getProfileImage() %>"
            		  alt="프로필 이미지"class="rounded-circle mg-3 align-self-center" 
            		  style="width:50px;height:50px">
               <%} %>
              
               
                <div class="flex-grow-1">
                    <div class="d-flex justify-content-between ">
                        <div>
                            <strong><%= tmp.getWriter()%></strong>
                            <span><%=tmp.getTargetWriter() %></span>
                        </div>
                        <small><%=tmp.getCreatedAt() %>></small>	
                    </div>
                    <pre><%=tmp.getContent() %>> </pre>
                    <%if(tmp.getWriter().equals(userName)){ %>
                    	
                    <%}else{ %>
	                    <button class="btn btn-sm btn-outline-primary show-reply-btn">댓글</button>
	                      <!--댓글 입력 폼 (처음에는 숨김)-->
	                    <div class="d-none form-div">
	                        <form action="comment-save.jsp" method="post">
	                            <textarea class="form-control" row="2" placeholder="댓글을 입력하세요.."></textarea>
	                            <button type="submit" class="btn btn-sm btn-success mg-2">등록</button>
	                            <button type="reset" class="btn btn-sm btn-secondary cancel-reply-btn">취소</button>
	                        </form>
	                    </div>
                    <%} %>
                    
                </div>
            </div>
        </div>
	<%} %>
</div>





		<%-- 
		<h4>댓글을 입력해주세요</h4>
		<!-- 원글의 댓글을 작설할 폼 -->
		<form action="save-comment.jsp" method="post">
			<input type="hidden" name="parentNum" value="<%=dto.getNum()%>"/>
			<input type="hidden" name="targetWriter" value="<%=dto.getWriter()%>"/>
			<textarea name="content" rows="10"></textarea>
			<button type="submit">등록</button>
		</form>
		--%>

	</div>
	<!-- container -->
	
	<script>

	const isLogin = <%=isLogin%>
	
	document.querySelector("#commentContent").addEventListener("input",()=>{
		//원글의 댓글 입력란에 포커스가 왔을 때 만일 로그인 하지 않았다면
		if(!isLogin){
			alert("댓글 작성을 위해 로그인 필요합니다.");
			location.href="${pageContext.request.contextPath }/user/loginform.jsp?url=${pageContext.request.contextPath }/board/view.jsp?num=<%=num%>";
		}
	});
	
    //모든 댓글 버튼에 이벤트 등록
    document.querySelectorAll(".show-reply-btn").forEach(item=>{
        //매개변수에 전달된 item은 댓글 button 의 참조값이다
        item.addEventListener("click",()=>{
            //버튼 클릭하면 d-none 속성을 제거함 => 아래 요소들이 보임
            item.nextElementSibling.classList.remove("d-none");
            //클릭한 버튼의 class 목록에 d-none 을 주기
            item.classList.add("d-none");
        });
    });
    
    document.querySelectorAll(".cancle-reply-btn").forEach(item=>{
        item.addEventListener("click",()=>{
            //가장 가까운 부모 요소중에 클래스 속성이 form-div인 함수 찾기
            const formDIv=item.closet(".form-div");
             //formDIv 에 d-none클래스 추가해서 안보이게 하고
            formDiv.classList.add("d-none");
            //formDIv 에 d-none클래스 추가해서 안보이게 하고
            formDiv.previousElmentsSiling.classList.remove("d-none");
           
            
        
        });

    });
</script>
</body>
</html>