<%@page import="org.apache.tomcat.jakartaee.commons.lang3.StringUtils"%>
<%@page import="test.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@page import="test.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //검색 keyword 가 있는지 읽어와본다
    String keyword=request.getParameter("keyword");
    System.out.println(keyword);
    
    //System.out.println(keyword); // null or "" 또는 "검색어..."
    if(keyword==null){
    	keyword="";
    }
    
    //기본 페이지 번호는 1 로 설정하고
    int pageNum=1;
    
    
    //페이지 번호를 읽어와서
    String strPageNum=request.getParameter("pageNum");
    //전달되는 페이지 번호가 있다면
    if(strPageNum !=null){
    	//해당 페이지 번호를 숫자로 변경해서 사용한다
    	pageNum = Integer.parseInt(strPageNum);
    }
  

    //한 페이지에 몇개의 표시 할 것인지
    final int PAGE_ROW_COUNT=5;
    
    //하단 페이지를 몇개씩 표시할 것인지
    final int PAGE_DISPLAY_COUNT = 5;
    
    
    //보여줄 페이지의 시작 ROWNUM
    int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
    //보여줄 페이지의 끝 ROWNM
    int endRowNum=pageNum*PAGE_ROW_COUNT;
    
    
 	 //하단 시작 페이지 번호 (정수를 정수로 나누면 소수점이 버려진 정수가 나온다)
  	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
  	//하단 끝 페이지 번호
  	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
  	
	/*
		StringUtils 클래스의 isEmpty() static 메소드를 이용하면 문자열이 비었는지 여부를 알 수 있다
		null 또는 "" 빈 문자열은 비었다고 판정된다.
		
		StringUtils.isEmpty(keyword) 는
		keyword == null or "".equals() 를 대체할 수 있다.  		
	*/
  	//전체 글의 갯수
  	int totalRow=0;
  //만일 전달된 keyword 가 없다면 
  	if(StringUtils.isEmpty(keyword)){
  		
  		totalRow=BoardDao.getInstance().getCount();
  	}else{//있다면 
  		totalRow=BoardDao.getInstance().getCountByKeyword(keyword);
  		
  			
  	}
  	//전체 페이지의 갯수 구하기
  	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
  	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
  	if(endPageNum > totalPageCount){
  		endPageNum=totalPageCount; //보정해 준다. 
  	}	
    
    
    //dto 에 select할 row 의 정보를 담고
    BoardDto dto = new BoardDto();
    dto.setStartRowNum(startRowNum);
    dto.setEndRowNum(endRowNum);
    
    //글목록
    List<BoardDto> list = null;
    
    //만일 keyword 가 없다면
    if(StringUtils.isEmpty(keyword)){
    	//아무런 검색어도 없기 때문에  dao의 입력값을 가져옴 => 전체 글 목록 보여줌
    	list = BoardDao.getInstance().selectPage(dto);
    }else{//있다면
    	//dto에 keyword 를 담고
    	dto.setKeyword(keyword);
    	//키워드에 해당하는 글 목록을 얻어낸다
    	list = BoardDao.getInstance().selectPageByKeyword(dto);
    	
    }
    
    
    
    	//글목록 얻어오기
    	//List<BoardDto> list=BoardDao.getInstance().selectAll();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/board/list.jsp</title>

<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/include/navbar.jsp">
	<jsp:param value="board" name="thisPage"/>
</jsp:include>
	
	
	<div class="container pt-1">
	<a class="btn btn-outline-primary btn-sm" href="new-form.jsp">
			새글 작성
			<i class="bi bi-pencil-square"></i>
		</a>
	<h1>게시글 목록입니다</h1>
	<!-- 행을 만들고 -->
	<div class="row my-3 ">
		<!-- 4/12 인 칼럼을 만들고 margin-start:auto를 부여해서 왼쪽 마진 자동 부여 -->
		<div class="col-lg-4  col-md-6  ms-auto ">
			<form action="list.jsp" method="get">
				<div class="input-group">
					<input value="<%=StringUtils.isEmpty(keyword) ? "":keyword  %>" type="text" name="keyword" class="form-control" placeholder="검색어 입력...." />
					<button type="submit" class="btn btn-outline-secondary">검색</button>
				</div>
			</form>	
		</div>
	</div>
	

	
	
	
	
	
	<table class="table table-bordered">
		<thead>
			<tr>
				<th>글번호</th>
				<th>작성자</th>
				<th>제목</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<%for(BoardDto tmp:list){ %>
				<tr>
					<td><%=tmp.getNum() %></td>
					<td><%=tmp.getWriter() %></td>
					<td>
						<a href="view.jsp?num=<%=tmp.getNum()%>"><%=tmp.getTitle() %></a>
					</td>
					<td><%=tmp.getViewCount() %></td>
					<td><%=tmp.getCreatedAt() %></td>
				</tr>
			<%} %>
		</tbody>
	</table>
	<ul class="pagination">
		<li class="page-item">
		<!-- pageNum>startPageNum 도 가능하지만 아래가 좀 더 명확함 -->
			<%if(startPageNum != 1){%>	
				<a class="page-link" href="list.jsp?pageNum=<%=startPageNum-1%>
				&keyword=<%=keyword%>">&lsaquo;</a>
			<%}%>
			
		</li>
		<%for(int i=startPageNum;i<=endPageNum;i++){%>
		<li class="page-item">
		<a class ="page-link  <%=i==pageNum ? "active" : "" %>" href="list.jsp?pageNum=<%=i%>
		&keyword=<%=keyword%>"><%=i %></a>
		</li>
		
		<% }%>
	
		<li class="page-item">
			<!-- pageNum<endPageNum 도 가능하지만 아래가 좀 더 명확함 -->
			<%if(endPageNum < totalPageCount){%>
				<a class="page-link" href="list.jsp?pageNum=<%=endPageNum+1%>">&rsaquo;</a>
			<%} %>
			
		</li>
	</ul>
</div>
</body>
</html>