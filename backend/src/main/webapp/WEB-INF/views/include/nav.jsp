<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- modal -->
    <%@ include file="modal.jsp" %>
<!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light" id="mainNav">
        <div class="container px-4 px-lg-5">
            <a class="navbar-brand" href="${root}">Enjoy Trip</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                Menu
                <i class="fas fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto py-4 py-lg-0">
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${root}/trip/search">지역별여행지</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4"
                            href="#">나의여행계획</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${root}/hotplace/list">핫플자랑하기</a></li>
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${root}/board/list">공유 게시판</a></li>
                    <c:if test="${!empty userInfo}">
                    <li class="nav-item"><a class="nav-link px-lg-3 py-3 py-lg-4" href="${root}/user/mypage" id="userName">${userInfo.name}님 안녕하세요.
                        </a></li>
                    </c:if>
                    <li class="nav-item dropdown">
                        <a class="nav-link px-lg-3 py-3 py-lg-4 dropdown-toggle d-inline-block" href="#" role="button"
                            data-bs-toggle="dropdown" aria-expanded="false" style="max-width: 30px">
                            <svg data-v-275e3ec0="" aria-hidden="true" focusable="false" data-prefix="fas"
                                data-icon="user-group" role="img" xmlns="http://www.w3.org/2000/svg"
                                viewBox="0 0 640 512" class="fa-2x svg-inline--fa fa-user-group">
                                <path data-v-275e3ec0="" fill="currentColor"
                                    d="M352 128c0 70.7-57.3 128-128 128s-128-57.3-128-128S153.3 0 224 0s128 57.3 128 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM609.3 512H471.4c5.4-9.4 8.6-20.3 8.6-32v-8c0-60.7-27.1-115.2-69.8-151.8c2.4-.1 4.7-.2 7.1-.2h61.4C567.8 320 640 392.2 640 481.3c0 17-13.8 30.7-30.7 30.7zM432 256c-31 0-59-12.6-79.3-32.9C372.4 196.5 384 163.6 384 128c0-26.8-6.6-52.1-18.3-74.3C384.3 40.1 407.2 32 432 32c61.9 0 112 50.1 112 112s-50.1 112-112 112z"
                                    class=""></path>
                            </svg>
                        </a>
                        <ul class="dropdown-menu">
                        <c:choose>
                        	<c:when test="${empty userInfo}">
                            <li><a class="dropdown-item" role="button" data-bs-toggle="modal"
                                    data-bs-target="#registerModal" id="regist">회원가입</a></li>
                            <li><a class="dropdown-item" role="button" data-bs-toggle="modal"
                                    data-bs-target="#loginModal" id="login">로그인</a></li>
                            </c:when>
                            <c:otherwise>
                            <li><a class="dropdown-item" role="button" id="logout" href="${root}/user/logout">로그아웃</a>
                            </li>
                            <li><a class="dropdown-item" role="button" id="mypage" href="${root}/user/mypage">마이페이지</a>
                            </li>
                            </c:otherwise>
                        </c:choose>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>