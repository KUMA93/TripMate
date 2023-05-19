<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/views/include/nav_dark.jsp"%>
	<div style='height:50px'></div>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="findPWModalLabel">비밀번호 찾기</h1>
            </div>
            <form action="${root}/user/findPW" method="post">
            <div class="modal-body">
                <div class="mb-3 row">
                    <p class="col-form-label">회원정보에 등록한 아이디와 이메일을 입력해주세요.</p>
                </div>
                <div class="mb-3 row">
                    <label for="id" class="col-sm-4 col-form-label">아이디</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="find_id" name="id"
                            placeholder="아이디">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="email" class="col-sm-4 col-form-label">이메일</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="find_email" name="email"
                            placeholder="email@example.com">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn btn-secondary" href="${root}">취소</a>
                <button type="submit" class="btn btn-primary" id="findBtn"  disabled>확인</button>
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
let find_id = document.querySelector("#find_id");
let find_email = document.querySelector("#find_email");
let findBtn = document.querySelector('#findBtn');
find_id.addEventListener('keyup', findActiveEvent);
find_email.addEventListener('keyup', findActiveEvent);

function findActiveEvent() {
    if (!find_id.value || !find_email.value){
        findBtn.disabled = true;
    } else {
        findBtn.disabled = false;
    }
}
</script>
</html>