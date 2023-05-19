<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>
<body>
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- Page Header-->
	<header class="masthead"
		style="background-image: url('${root}/assets/img/contact-bg.jpg')">
		<div class="container position-relative px-4 px-lg-5">
			<div class="row gx-4 gx-lg-5 justify-content-center">
				<div class="col-md-10 col-lg-8 col-xl-7">
					<div class="page-heading">
						<h1>my page</h1>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- Main Content-->
	<main class="mb-4">
	<div class="container px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="my-5">
					<form id="mypage" action="${root}/user/update" method="post">
						<div class="form-floating">
							<input class="form-control" id="user_name" name="user_name" type="text"
								value="${userInfo.name}" /> <label for="user_name">이름</label>
							<div class="invalid-feedback" id="nameRequire"></div>
						</div>
						<div class="form-floating">
							<input class="form-control" id="user_id" name="user_id" type="text"
								value="${userInfo.id}" disabled /> <label for="user_id" >아이디</label>
							<div class="invalid-feedback" id="emailRequire"></div>
						</div>
						<div class="form-floating">
							<input class="form-control" id="user_email" name="user_email" type="email"
								value="${userInfo.email}" /> <label for="user_email">이메일</label>
							<div class="invalid-feedback" id="emailRequire"></div>
						</div>
						<div class="form-floating">
							<input class="form-control" id="user_next_password" name="nextPw"
								type="password"></input> <label for="next_password">변경할
								비밀번호</label>
							<div class="invalid-feedback" id="nextPwRequire"></div>
						</div>
						<div class="form-floating">
							<input class="form-control" id="user_next_password_check"
								type="password"></input> <label for="next_password_check">변경할
								비밀번호 확인</label>
							<div class="text-danger" id="nextPwCheckRequire"></div>
						</div>
						<br />
						<!-- Submit Button-->
						<button type="submit" class="btn btn-primary" id="updateBtn" disabled>수정</button>
						<a class="btn btn-dark text-uppercase" href="${root}/user/remove">탈퇴</a>
					</form>
				</div>
			</div>
		</div>
	</div>
	</main>
	<!-- Footer-->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
//회원가입
let updateBtn = document.querySelector("#updateBtn");
let user_pw = document.querySelector('#user_password');
let nextPw = document.querySelector('#user_next_password');
let nextPwCheck = document.querySelector('#user_next_password_check');
let user_name = document.querySelector('#user_name');
let user_email = document.querySelector('#user_email');

user_name.addEventListener('keyup', updateActiveEvent);
user_email.addEventListener('keyup', updateActiveEvent);
nextPwCheck.addEventListener('keyup', updateActiveEvent);

function user_pwCheck(value) {
	let require = document.querySelector("#nextPwCheckRequire");
	if (!nextPw.value && !value){
		require.textContent = "";
		return true;
	}
	
	
    if (nextPw.value != value) {
    	require.textContent = "비밀번호가 일치하지 않습니다.";
        return false;
    }
    
    require.textContent = "";
    return true;
}

function updateActiveEvent(){
    let check = user_pwCheck(nextPwCheck.value);

	if (!user_name.value || !user_email.value || !check){
		updateBtn.disabled = true;
    } else {
    	updateBtn.disabled = false;
    }

}
</script>
</body>
</html>
