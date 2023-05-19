<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script src="${root}/assets/js/index.js"></script>
</head>

<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>

	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('${root}/assets/img/nightview.jpeg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="site-heading">
						<h1>Enjoy Trip</h1>
						<span class="subheading">여행지 정보를 알아보고 </span> <span
							class="subheading">나만의 여행 계획을 만들어보세요</span>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content-->
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-md-15 col-lg-8 col-xl-15">
				<!-- Post preview-->
				<div class="post-preview">
					<a href="${root}/trip/search">
						<h2 class="post-title">우리지역 관광지</h2>
						<h3 class="post-subtitle">우리 지역의 여행지에 대해 알아보세요.</h3>
					</a>
					<div class="card-container" id="destination"></div>
				</div>				<!-- Divider-->
				<hr class="my-4" />
				<!-- Post preview-->
				<div class="post-preview">
					<a href="${root}/hotplace/list">
						<h2 class="post-title">핫플 자랑하기</h2>
						<h3 class="post-subtitle">나만 알고있는 핫플을 자랑해주세요</h3>
					</a>
					<div class="card-container" id="hotPlace">
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Footer-->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>

</html>