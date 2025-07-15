<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<% 
		String userName=(String)session.getAttribute("userName");
	%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/index.jsp</title>
<jsp:include page="/WEB-INF/include/resource.jsp"></jsp:include>
</head>
<body>
   <jsp:include page="/WEB-INF/include/navbar.jsp">
   		<jsp:param value="index" name="thisPage" />
   </jsp:include>
    	
	<div class="container">
	<%if(userName !=null){ %>
		<strong><%=userName %></strong>님 로그인중.....
	<%} %>
		<h1>인덱스 페이지 입니다.</h1>
		<ul>
			<li><a
				href="${pageContext.request.contextPath }/member/list.jsp">회원목록</a></li>
			<li><a href="${pageContext.request.contextPath }/book/list.jsp">책목록</a></li>
			<li><a href="${pageContext.request.contextPath }/user/signup-form.jsp">회원가입</a></li>
		</ul>

		<div id="carouselExampleIndicators" class="carousel slide">
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="0" class="active" aria-current="true"
					aria-label="Slide 1"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="1" aria-label="Slide 2"></button>
				<button type="button" data-bs-target="#carouselExampleIndicators"
					data-bs-slide-to="2" aria-label="Slide 3"></button>
			</div>
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="images/top01.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top02.jpg" class="d-block w-100" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/top03.jpg" class="d-block w-100" alt="...">
				</div>
			</div>
			<button class="carousel-control-prev" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
				<span class="carousel-control-prev-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Previous</span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
				<span class="carousel-control-next-icon" aria-hidden="true"></span>
				<span class="visually-hidden">Next</span>
			</button>
			<footer class="bg-primary text-white py-4 mt-5">
				<div
					class="container d-flex justify-content-between align-items-center flex-wrap">
					<div>
						<h5 class="mb-0">MyBrand</h5>
					</div>
					<div>
						<a href="#" class="text-white me-3"><i class="bi bi-facebook"></i></a>
						<a href="#" class="text-white me-3"><i class="bi bi-twitter"></i></a>
						<a href="#" class="text-white"><i class="bi bi-instagram"></i></a>
					</div>
				</div>
			</footer>
		</div>
	</div>
	
</body>
</html>