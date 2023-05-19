function regist() {
    let id = document.getElementById("id").value;
    let name = document.getElementById("name").value;
    let password = document.getElementById("password").value;
    let passwordCheck = document.getElementById("passwordCheck").value;
    let email = document.getElementById("email").value;
    let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    if (!id || !name || !password || !passwordCheck || !email) {
        alert("빈 칸이 없도록 입력해주세요.")
        return false;
    }

    if (password != passwordCheck) {
        alert("비밀번호가 일치하지 않습니다.")
        return false;
    }

    if (!regExp.test(email)) {
        alert("이메일 형식이 올바르지 않습니다.")
        return false;
    }

    return true;

}

function login() {
    let id = document.getElementById("login_id").value;
    let password = document.getElementById("login_password").value;

    if (!id || !password) {
        alert("빈 칸이 없도록 입력해주세요.")
        return false;
    }

    return true;
}

function findPassword() {
    let email = document.getElementById("find_email").value;
    let regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;

    if (!email) {
        alert("이메일을 입력해주세요.")
        return;
    }

    if (!regExp.test(email)) {
        alert("이메일 형식이 올바르지 않습니다.")
        return;
    }

    alert('이메일로 임시 비밀번호를 전송했습니다.')
}