<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
<%
	String id = request.getParameter("id");
%>
<%@ include file="/WEB-INF/views/include/nav_dark.jsp"%>
<!-- 비밀번호 갱신 -->
<div style='height:50px'></div>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="updatePWModalLabel">비밀번호 변경</h1>
            </div>
            <form action="${root}/user/updatePW" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <div class="modal-body">
                <div class="mb-3 row">
                    <p class="col-form-label">변경할 비밀번호를 입력하세요.</p>
                </div>
                <div class="mb-3 row">
                    <label for="password" class="col-sm-4 col-form-label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="updatePW" name="pw"
                            placeholder="비밀번호">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="passwordCheck" class="col-sm-4 col-form-label">비밀번호 확인</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="updatePWCheck" name="pwCheck"
                            placeholder="비밀번호">
                    <div id="updatePWCheck-result" class="mb-3 text-danger"></div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-secondary" href="${root}">취소</a>
                <button type="submit" class="btn btn-primary" id="updatePWBtn" disabled>확인</button>
            </div>
            </form>
        </div>
    </div>
</body>
<!-- Footer-->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
//비밀번호 찾기
let updatePW = document.querySelector('#updatePW');
let updatePWCheck = document.querySelector('#updatePWCheck');
let updatePWBtn = document.querySelector('#updatePWBtn');
let updatePWDiv = document.querySelector("#updatePWCheck-result");
updatePW.addEventListener('keyup', updatePWActiveEvent);
updatePWCheck.addEventListener('keyup', updatePWActiveEvent);

function updatePWActiveEvent() {
	console.log(updatePWCheck.value);
    if (!updatePW.value || !updatePWCheck.value){
    	updatePWBtn.disabled = true;
    } else if (updatePW.value != updatePWCheck.value){
    	updatePWDiv.textContent = "비밀번호가 일치하지 않습니다.";
    	updatePWBtn.disabled = true;
    } else {
    	updatePWDiv.textContent = "";
    	updatePWBtn.disabled = false;
    }
}
</script>
</html>