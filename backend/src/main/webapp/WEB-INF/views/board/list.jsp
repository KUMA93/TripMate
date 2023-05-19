<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
    window.onload=function(){
            document.querySelector("#search").addEventListener('click', function() {
                let form = document.querySelector("#frm");
                document.querySelector("#pageNo").value='1'
                form.submit();
            })
            document.querySelector("#word").value='${param.word}'
            
            <c:choose>
                <c:when test='${!empty param.pageNo}'>
                    document.querySelector("#pageNo").value='${param.pageNo}'
                </c:when>
                <c:otherwise>
                    document.querySelector("#pageNo").value='1'
                </c:otherwise>
            </c:choose>
            <c:if test='${!empty param.key}'>
                document.querySelector("#key").value='${param.key}'
            </c:if>
             
               let pages = document.querySelectorAll(".page-link");
               pages.forEach(function (page) {
                 page.addEventListener("click", function () {
                      document.querySelector("#pageNo").value = this.getAttribute("data-pg");
                   let form = document.querySelector("#frm");
                   form.setAttribute("action", "${root}/board/list");
                   form.submit();
                 });
               });
    }
</script>
<%@ include file="/WEB-INF/views/include/nav.jsp"%>


<!-- Page Header-->
<header class="masthead"
	style="background-image: url('${root}/resources/static/assets/img/nightview.jpeg')">
	<div class="container position-relative px-4 px-lg-5">
		<div class="row gx-4 gx-lg-5 justify-content-center">
			<div class="col-md-10 col-lg-8 col-xl-7">
				<div class="site-heading text-nowrap">
					<h1>핫플 공유 게시판</h1>
				</div>
			</div>
		</div>
	</div>
</header>
<div class="row justify-content-center">
	<div class="col-lg-8 col-md-10 col-sm-12">
		<div class="row align-self-center mb-2">
			<div class='text-right pr-4'>
				<a class='btn btn-primary' href='${root}/board/regist'>등록</a>
			</div>
			<c:if test="${empty articles}">
				<table class='table table-hover'>
					<tr>
						<td>등록된 데이타가 없습니다.</td>
					</tr>
				</table>
			</c:if>

			<c:if test="${!empty articles}">
				<table class='table table-hover'>
					<thead>
						<tr>
							<th>글번호</th>
							<th>작성자</th>
							<th>제목</th>
							<th>조회수</th>
							<th>작성시간</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles}">
							<input type="hidden" name="content" value="${article.content}"/>
							<tr>
								<td>${article.articleNo}</td>
								<td>${article.userId}</td>
								<td><a
									href="${root}/board/detail?articleNo=${article.articleNo}">${article.subject}</a></td>
								<td>${article.hit}</td>
								<td>${article.registerTime}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="row">
					<div class="col">${pageBean.pageLink }</div>
				</div>
			</c:if>
			<div class="col-md-7 offset-3">
				<form id='frm' action="${root}/board/list">
					<input type='hidden' name='pageNo' id='pageNo' />
					<div class="row">
						<div class="col">
							<select name='key' id='key'>
								<option value='all'>---선택하세요---</option>
								<option value='articleNo'>글번호</option>
								<option value='user_id'>작성자명</option>
								<option value='subject'>제목</option>
								<option value='content'>내용</option>
							</select>
						</div>
						<div class="col">
							<input type="text" class="form-control" placeholder="검색어 입력"
								name="word" id="word">
						</div>
						<div class="col">
							<input type="button" class="btn btn-primary" value="검색"
								id='search'>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
<form id="form-param" method="get" action=""></form>
<%@ include file="/WEB-INF/views/include/footer.jsp"%>
