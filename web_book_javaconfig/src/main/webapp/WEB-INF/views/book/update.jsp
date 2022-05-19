<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>
<%-- 화면 위치 --%>
<h1>도서 정보 수정</h1>
<form action="" method="post">
	<div class="form-group row">
		<label for="code" class="col-sm-2 col-form-label">Code</label>
		<div class="col-sm-8">
			<input type="text" name="code" id="code" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label for="price" class="col-sm-2 col-form-label">Price</label>
		<div class="col-sm-8">
			<input type="text" name="price" id="price" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="submit" class="btn btn-primary">수정</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</div>
	</div>
</form>
<script src="/resources/js/input.js"></script>
<%@ include file = "../layout/footer.jsp"%>