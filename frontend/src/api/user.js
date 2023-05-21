import http from "@/api/http"

async function login(user, success, fail) {
await http.post(`/rest/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(id, success, fail) {
http.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
await http.get(`/rest/user/info/${id}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
http.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
await http.post(`/rest/user/refresh`, user).then(success).catch(fail);
}

async function logout(id, success, fail) {
await http.get(`/rest/user/logout/${id}`).then(success).catch(fail);
}

async function remove(id, success, fail) {
await http.delete(`/rest/user/remove/${id}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, remove };
