window.onload = function () {
    const user = JSON.parse(window.localStorage.getItem("user"));
    document.getElementById("user_name").value = `${user.name}`
    document.getElementById("user_email").value = `${user.email}`
    document.getElementById("user_id").value = `${user.id}`
}

function modify() {
    let name = document.getElementById("user_name").value
    let email = document.getElementById("user_name").value
    let id = document.getElementById("user_name").value
    let pw = document.getElementById("user_password").value

    const user = JSON.parse(window.localStorage.getItem("user"));
    user.name = name;
    user.email = email;
    user.id = id;

    if (pw) {
        user.pw = pw;
    }

    window.localStorage.setItem("user", user);
    alert("수정되었습니다.")
}