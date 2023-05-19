<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- jstl 사용하기 위한 코드 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 프로젝트의 context 경로를 편하게 사용하기 위한 코드 --%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<c:if test='${!empty param.msg}'>
	<c:set var='msg' value="${param.msg}" />
</c:if>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>EnjoyTrip</title>
<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<!-- Font Awesome icons (free version)-->
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js"
	crossorigin="anonymous"></script>

<!-- Core theme CSS (includes Bootstrap)-->
<link href="${root}/assets/css/styles.css" rel="stylesheet" />

<script>
    <%-- request 객체에 msg가 들어있을 때 해당 내용 알림창 띄우기 --%>
	<c:if test="${!empty msg}">
		alert("${msg}");
	</c:if>
</script>