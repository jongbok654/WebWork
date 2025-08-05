
<%@page import="test.dto.GalleryDto"%>
<%@page import="test.dao.GalleryDao"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<GalleryDto> list=GalleryDao.getInstance().getListWithImages();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/list.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
<%--masonry 스크립트 로딩 --%>
<script src="https://unpkg.com/masonry-layout@4/dist/masonry.pkgd.min.js"></script>
<script src="https://unpkg.com/imagesloaded@4/imagesloaded.pkgd.min.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/include/navbar.jsp">
		<jsp:param value="gallery" name="thisPage"/>
	</jsp:include>
	<div class="container">
		<a class="btn btn-outline-primary btn-sm mt-2" href="new-form.jsp">
	       <i class="bi bi-image"></i> 
	       <i class="bi bi-plus"></i>
	       <span class="visually-hidden">겔러리 새글 작성</span>
	    </a>
		<h1>Gallery 목록</h1>
		<div class="row" id="masonry-grid">
<% for(GalleryDto tmp : list) {
     if(tmp.getImageList() != null && !tmp.getImageList().isEmpty()) {
         String name = tmp.getImageList().get(0).getSaveFileName();
         int count = tmp.getImageList().size();
%>
    <div class="col-sm-6 col-md-4 col-lg-3 mb-4">
        <div class="card">
            <img class="card-img-top" src="${pageContext.request.contextPath}/upload/<%=name %>" />
            <div class="card-body">
                <h4><%=tmp.getTitle() %></h4>
                <a class="btn btn-sm btn-success" href="view.jsp?num=<%=tmp.getNum()%>">
                    <%=count %> 개의 이미지
                </a>
            </div>
        </div>
    </div>
<%
     } // if
} // for
%>
</div>
	</div>
	<script>
	const grid = document.querySelector("#masonry-grid");
	imagesLoaded(grid, ()=>{
		new Masonry(grid, { percentPosition:true});
	});

	</script>
</body>
</html>


