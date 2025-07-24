<%@page import="test.dao.BoardDao"%>
<%@page import="test.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//수정할 글 정보를 얻어와서
    	int num=Integer.parseInt(request.getParameter("num"));
    	BoardDto dto =BoardDao.getInstance().getByNum(num);
    	//글 수정 등을 응답한다
    	
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/edit.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
<!-- Toast UI Editor CSS -->
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor.min.css" />
    
    <!-- Toast UI Editor JS -->
    <script src="https://uicdn.toast.com/editor/latest/toastui-editor-all.min.js"></script>
    
    <!-- 한국어 번역 파일 추가 -->
    <script src="https://uicdn.toast.com/editor/latest/i18n/ko-kr.js"></script>
</head>
<body>
	<div class="container pt-3">
		<nav aria-label="breadcrumb">
 	 	<ol class="breadcrumb">
    		<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/">Home</a>
    		</li>
    		<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/board/list.jsp">Board</a>
    		</li>
    		<li class="breadcrumb-item active">Edit
    		</li>
  		</ol>
		</nav>
		<h1>글 수정 페이지</h1>
		<form action="update.jsp" method="post" id="editForm">
			<div>
			<label class="form-label" for="num">글 번호</label>
			<input class="form-control" type="text" name="num" id="num" 
					value="<%=dto.getNum() %>" readonly/>
			</div>
			<div>
			<label class="form-label" for="writer">작성자</label>
			<input class = "form-control" type="text" name="writer" id="writer" 
			value="<%=dto.getWriter()%>"readonly />
			</div>
			<div>
				<label for="title" class="form-label">제목</label>
				<input type="text" class="form-control" name="title" id ="title"
				value="<%=dto.getTitle()%>"/>
			</div>
			<div>
				<label for="content" class="form-label">내용</label>
				
				<div id="editor"></div>
				
				<textarea class="form-control" name="content" id="hiddenContent"></textarea>
				
			</div>
			<button class="btn btn-success btn-sm">수정확인</button>
			<button class="btn btn-danger btn-sm">Reset</button>
		</form>
		<script>
		const editor = new toastui.Editor({
			el: document.querySelector('#editor'),
			height: '500px',
			initialEditType: 'wysiwyg',
			previewStyle: 'vertical',
			language: 'ko',
			initialValue:`<%=dto.getContent()%>`
		});
				document.querySelector("#editForm").addEventListener("submit",(e)=>{
					//에디터로 작성된 문자열 읽어오기
					const content = editor.getHTML();
					//텍스트로 콘솔에 출력하기
					console.log(content);
					//에디터로 작성된 문자열을 폼 전송이 될 수 있도록 textarea 의 value 로 넣어준다
					document.querySelector("#hiddenContent").value=content;
					//테스트 하기 위해 폼 전송 막기
					//e.preventDefault();
				});
		</script>
		
	</div>
</body>
</html>