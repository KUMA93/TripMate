<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>

<!-- Template Main CSS File -->
<link href="${root}/assets/css/style.css" rel="stylesheet">
<!-- Vendor CSS Files -->
<link href="${root}/assets/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${root}/assets/vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="${root}/assets/vendor/boxicons/css/boxicons.min.css"
	rel="stylesheet">
<link href="${root}/assets/vendor/quill/quill.snow.css" rel="stylesheet">
<link href="${root}/assets/vendor/quill/quill.bubble.css"
	rel="stylesheet">
<link href="${root}/assets/vendor/remixicon/remixicon.css"
	rel="stylesheet">
<link href="${root}/assets/vendor/simple-datatables/style.css"
	rel="stylesheet">

</head>

<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- Page Header-->
	<header class="section-head">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="page-heading">
						<h1>지역별 여행지</h1>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content-->
	<div class="container-fluid">
		<div class="row justify-content-center">
			<!--관광지 정보 조회 시작-->
			<!--map start-->
			<div class="col-xxl-8 col-12" id="map"></div>
			<!--map end-->
			<!--검색 폼 start-->
			<div class="col-xxl-4 col-12 border p-2 m-1">
				<!-- 관광지 검색 start -->
				<h2 class="h2">관광지 검색</h2>
				<form class="my-3" onsubmit="return false;" role="search">
					
					<div class="input-group mb-1">
						<label class="input-group-text" for="sido">시/도</label> 
						<select
class="form-select" id="sido" name="sido">
						</select>
					</div>
					<div class="input-group mb-1">
						<label class="input-group-text" for="gugun">구/군</label> 
						<select class="form-select" name="gugun" id="gugun">
						</select>
					</div>
					<div class="input-group mb-1">
						<label class="input-group-text" for="search-content-id">관광지
							유형</label> <select id="search-content-id" name="contentType" class="form-select">
							<option value="0" selected></option>
							<option value="12">관광지</option>
							<option value="14">문화시설</option>
							<option value="15">축제공연행사</option>
							<option value="25">여행코스</option>
							<option value="28">레포츠</option>
							<option value="32">숙박</option>
							<option value="38">쇼핑</option>
							<option value="39">음식점</option>
						</select>
					</div>
					<div class="input-group">
						<input id="search-keyword" name="keyword" class="form-control me-2" type="search"
							placeholder="검색어" aria-label="검색어" />
						<button id="btn-search" class="btn btn-outline-dark" type="button">검색</button>
					</div>
				</form>
				<!-- 관광지 검색 end -->
			</div>
			<!--검색 폼 end-->
			<!--관광지 정보 조회 끝-->
		</div>
	</div>
	<!-- 관광지 리스트 출력-->
	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col border" style="height: 500px; overflow: auto;">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>대표이미지</th>
							<th>관광지명</th>
							<th>주소</th>
							<th class="visually-hidden">위도</th>
							<th class="visually-hidden">경도</th>
							<th></th>
						</tr>
					</thead>
					<tbody id="trip-list">
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<!-- 관광지 리스트 출력 끝-->


	


	<!-- Vertically centered Modal -->
	<div class="modal fade" id="verticalycentered" tabindex="-1">
		<div class="modal-dialog modal-dialog-centered mw-100 w-50">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">상세정보</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<!-- Card with an image on top -->
					<div class="card w-100">
						<img id="detail-modal-img" src="" class="card-img-top" alt="...">
						<div class="card-body">
							<!-- Default Table -->
							<table class="table">
								<tr>
									<th scope="row">관광지명</th>
									<td id="detail-modal-title"></td>
								</tr>
								<tr>
									<th scope="row">주소</th>
									<td id="detail-modal-addr1"></td>
								</tr>
								<tr>
									<th scope="row">위도</th>
									<td id="detail-modal-lat"></td>
								</tr>
								<tr>
									<th scope="row">경도</th>
									<td id="detail-modal-long"></td>
								</tr>
								<tr>
									<th scope="row">세부정보</th>
									<td id="detail-modal-overview"></td>
								</tr>
							</table>
							<!-- End Default Table Example -->
						</div>
					</div>
					<!-- End Card with an image on top -->
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Vertically centered Modal-->
	<!-- map js -->
	<script> root = '${root}' </script>
<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d247b5bcabc7cfb6552be7b927085bf0&libraries=services,clusterer"></script>
<script src="${root}/assets/js/map.js"></script>
<script src="${root}/assets/js/attraction.js"></script>

	<!-- Footer-->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
</body>

</html>