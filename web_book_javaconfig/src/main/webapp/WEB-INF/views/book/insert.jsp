<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp"  %>
<h1>도서 정보 입력</h1>
<form action="" method="post">
	<div class="form-group row">
		<label for="code" class="col-sm-2 col-form-label">Code</label>
		<div class="col-sm-8">
			<input type="text" name="code" id="code" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label for="title" class="col-sm-2 col-form-label">Title</label>
		<div class="col-sm-8">
			<input type="text" name="title" id="title" class="form-control"/>
		</div>
	</div>
	<div class="form-group row">
		<label for="writer" class="col-sm-2 col-form-label">Writer</label>
		<div class="col-sm-8">
			<input type="text" name="writer" id="writer" class="form-control"/>
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
			<button type="submit" class="btn btn-primary">입력</button>
			<button type="reset" class="btn btn-danger">취소</button>
		</div>
	</div>
</form>
<script src="/resources/js/input.js"></script>
<script>
	let error = '${error}';
	if(error != ''){
		alert(error);
	}
</script>
<%@ include file = "../layout/footer.jsp"  %>