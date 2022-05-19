<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>
<%-- 화면 위치 --%>
<h1>도서 정보 삭제</h1>

<p>삭제할 도서의 코드 번호를 입력하세요</p>
<form action="" method="post">
	<div class="form-group row">
		<label for="code" class="col-sm-2 col-form-label">Code</label>
		<div class="col-sm-8">
			<input type="text" name="code" id="code" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">삭제</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</div>
	</div>
</form>
<%@ include file = "../layout/footer.jsp"%>