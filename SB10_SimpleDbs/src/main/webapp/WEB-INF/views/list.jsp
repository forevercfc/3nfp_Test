<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<!-- ===============부트스트랩 CSS================ -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<body>
	<div class="container">
		<c:forEach items="${list}" var="dto">
			<div class="container">
				<div class="row">
					<div class="col">
						<ul class="list-group list-group-flush">
						<hr>
							<li class="list-group-item"><a href="#"
								class="link-dark text-decoration-none">
									<div class="row pb-2">
										<div class="col-1">
											<h1 class="display-6 text-primary">${dto.id}</h1>
										</div>
										<div class="col-11">
											<div class="row h-100">
												<div class="col-12 col-md-8 d-flex align-items-center">
													${dto.writer} 님의 <a href="view?id=${dto.id}">${dto.title}</a>!
												</div>
												<div
													class="col-12 col-md-4 d-flex align-items-center justify-content-between">
													<span class="text-muted small">조회수 : ${dto.b_hit}</span> <span
														class="text-muted small"> ${dto.b_upd_date}</span>
													<button class="btn btn-sm bg-dark text-white">
														<a href="delete?id=${dto.id}"
															class="text-white text-decoration-none">삭제</a>
													</button>
													<button class="btn btn-sm bg-dark text-white">
														<a href="updateForm?id=${dto.id}">수정</a>
													</button>
												</div>
											</div>
										</div>
									</div>
							</a></li>
							
						</ul>
					</div>
				</div>
			</div>
		</c:forEach>
		



		<!--         <p><a href="/writeForm">글작성</a></p> -->
		<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <!-- "Prev" 버튼, 첫 페이지에서는 비활성화 -->
        <c:if test="${page > 1}">
            <li class="page-item">
                <a class="page-link" href="list?page=${page - 1}">Prev</a>
            </li>
        </c:if>
        <!-- 페이지 번호 -->
        <c:forEach begin="${startPage}" end="${endPage}" var="i">
            <li class="page-item ${page == i ? 'active' : ''}">
                <a class="page-link" href="list?page=${i}">${i}</a>
            </li>
        </c:forEach>
        <!-- "Next" 버튼, 마지막 페이지에서는 비활성화 -->
        <c:if test="${page < totalPage}">
            <li class="page-item">
                <a class="page-link" href="list?page=${page + 1}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>
<div class="row">
    <div class="col-4 offset-4">
<!-- 검색 폼에 검색 유형 선택 드롭다운 추가 -->
<!-- 검색 폼에 검색 유형 선택을 위한 드롭다운 추가 -->
<form action="/search" method="get">
    <div class="input-group">
        <select name="type" class="form-select">
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="writer">작성자</option>
        </select>
        <input type="text" name="query" class="form-control" placeholder="검색어 입력">
        <button type="submit" class="btn btn-primary">검색</button>
    </div>
   <!--  <button type="button" onclick="window.location.href='/list';">돌아가기</button> -->
</form>



        
    </div>
</div>

</div>

	




	<!-- ===============부트스트랩 JavaScript================ -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
