<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="${root}/assets/js/user.js"></script>
<!-- 회원가입 Modal -->
<div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="registerModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="registerModalLabel">회원가입</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form class="modal-body" action="${root}/user/regist" method="post">
                <div class="mb-3 row">
                    <label for="name" class="col-sm-4 col-form-label">이름</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="name" name="name" placeholder="이름">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="id" class="col-sm-4 col-form-label">아이디</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="id" name="id" placeholder="아이디">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="password" class="col-sm-4 col-form-label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="password" name="pw"
                            placeholder="비밀번호">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="passwordCheck" class="col-sm-4 col-form-label">비밀번호 확인</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="passwordCheck" name="passwordCheck"
                            placeholder="비밀번호">
                    </div>
                </div>
                <div id="pwcheck-result" class="mb-3 text-danger"></div>
                <div class="mb-3 row">
                    <label for="email" class="col-sm-4 col-form-label">이메일</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="email" name="email"
                            placeholder="email@example.com">
                    </div>
                </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <input type="submit" class="btn btn-primary" id="regist" value="회원가입" disabled />
            </div>
            </form>
        </div>
    </div>
</div>

<!-- 로그인 Modal -->
<div class="modal fade" id="loginModal" tabindex="-1" aria-labelledby="loginModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="loginModalLabel">로그인</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <form action="${root}/user/login" method="post">
            <div class="modal-body">
                <div class="mb-3 row">
                    <label for="id" class="col-sm-4 col-form-label">아이디</label>
                    <div class="col-sm-8">
                        <input type="text" class="form-control" id="login_id" name="id" placeholder="아이디" value="${cookie.saveId.value}">
                    </div>
                </div>
                <div class="mb-3 row">
                    <label for="password" class="col-sm-4 col-form-label">비밀번호</label>
                    <div class="col-sm-8">
                        <input type="password" class="form-control" id="login_password" name="pw"
                            placeholder="비밀번호">
                    </div>
                </div>
                <div class="mb-3 row form-group form-check">
                <div class="col-sm-4"></div>
			      <label class="col-sm-8 col-form-label-sm">
			      	<c:choose>
			      		<c:when test="${empty cookie.saveId}">
			        		<input class="form-check-input" type="checkbox" name="remember"> 아이디 기억하기
			        	</c:when>
			        	<c:otherwise>
			        		<input class="form-check-input" type="checkbox" name="remember" checked="checked"> 아이디 기억하기
			        	</c:otherwise>
			        </c:choose>
			      </label>
			    </div>
                <div class="mb-3 row">
                    <div class="col-sm-4"></div>
                    <a class="col-sm-8 col-form-label-sm text-decoration-underline" href="${root}/user/findPW">비밀번호를 잊으셨나요?</a>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                <button type="submit" class="btn btn-primary" id="loginBtn" disabled>로그인</button>
            </div>
            </form>
        </div>
    </div>
</div>

<script>
// 회원가입
let registDisabled = true;
let registBtn = document.querySelector("#regist");
let id = document.querySelector('#id');
let pw = document.querySelector('#password');
let passwordCheck = document.querySelector('#passwordCheck');
let name = document.querySelector('#name');
let email = document.querySelector('#email');

id.addEventListener('keyup', registActiveEvent);
pw.addEventListener('keyup', registActiveEvent);
name.addEventListener('keyup', registActiveEvent);
email.addEventListener('keyup', registActiveEvent);
passwordCheck.addEventListener('keyup', registActiveEvent);

let disabled = true;
function pwCheck(value) {
	let resultDiv = document.querySelector("#pwcheck-result");
    if (pw.value != value) {
        resultDiv.textContent = "비밀번호가 일치하지 않습니다.";
        return true;
    }
    resultDiv.textContent = "";
    return false;
}

function registActiveEvent(){
    let check = pwCheck(passwordCheck.value);

	if (!id.value || !pw.value || !name.value || !email.value || !passwordCheck.value || check){
        disabled = true;
    } else {
        disabled = false;
    }

    registBtn.disabled = disabled;
}


// 로그인

let login_id = document.querySelector("#login_id");
let login_password = document.querySelector("#login_password");
login_id.addEventListener('keyup', loginActiveEvent);
login_password.addEventListener('keyup', loginActiveEvent);

function loginActiveEvent() {
    let loginBtn = document.querySelector("#loginBtn");
    if (!login_id.value || !login_password.value){
        loginBtn.disabled = true;
    } else {
        loginBtn.disabled = false;
    }
}


</script>