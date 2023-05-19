<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp" %>
	<header class="section-head">
	</header>
	<%-- 페이지만의 내용 --%>
	<div class="container p-4">
		오류가 발생했습니다.<br>
		오류 메세지 : ${msg} <br>
		<div>
			<a href="${root}" class='btn btn-secondary'>메인 화면으로 이동</a>
		</div>
	</div>
	<%-- --%>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>