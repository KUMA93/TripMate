<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<%@ include file="/WEB-INF/views/include/nav.jsp" %>
<!-- Page Header-->
<header class="masthead"
	style="background-image: url('${root}/assets/img/nightview.jpeg')">
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
          <h2 class="my-3 py-3 shadow-sm bg-light text-center">
            <mark class="sky">글쓰기</mark>
          </h2>
        </div>
        <div class="col-lg-8 col-md-10 col-sm-12">
          <form id="form-register" method="POST" action="${root}/board/regist">
            <div class="mb-3">
              <label for="userId" class="form-label">작성자 : </label>
              <input type="text" value="${userInfo.id}" id="userId" name="userId" readonly class="form-control"/>
              <label for="subject" class="form-label">제목 : </label>
              <input
                type="text"
                class="form-control"
                id="subject"
                name="subject"
                placeholder="제목..."
              />
            </div>
            <div class="mb-3">
              <label for="content" class="form-label">내용 : </label>
              <textarea class="form-control" id="content" name="content" rows="7"></textarea>
            </div>
            <div class="col-auto text-center">
            	<a class='btn btn-outline-primary mb-3' href='${root}/board/list'>게시글 목록</a>
              	<input type="submit" id="regist" class="btn btn-outline-primary mb-3" value="글 작성">
              	<button type="reset" class="btn btn-outline-danger mb-3">초기화</button>
              
            </div>
          </form>
        </div>
      </div>
    </div>
    <script>
      document.querySelector("#btn-register").addEventListener("click", function () {
        if (!document.querySelector("#subject").value) {
          alert("제목 입력!!");
          return;
        } else if (!document.querySelector("#content").value) {
          alert("내용 입력!!");
          return;
        }
      });
    </script>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>
