<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>도서정보</h3>
		
		<button type="button" id="all" class="btn btn-secondary">도서목록</button>
		<button type="button" id="get" class="btn btn-primary">특정도서정보</button>
		<button type="button" id="delete" class="btn btn-danger">도서정보삭제</button>
		<button type="button" id="update" class="btn btn-success">도서정보수정</button>
		
		<div id="result">
			<table class="table">
				
			</table>
		</div>
		
		<div>
			<form action="" method="get">
				<div>
					<label>코드</label>
					<input type="text" name="code" id="code"/>
				</div>
				<div>
					<label>제목</label>
					<input type="text" name="title" id="title"/>
				</div>
				<div>
					<label>저자</label>
					<input type="text" name="writer" id="writer"/>
				</div>
				<div>
					<label>가격</label>
					<input type="text" name="price" id="price"/>
				</div>
				<div>	
					<button type="button" id="insert">입력</button>
				</div>
			</form>	
		</div>
	
	</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="/resources/book_test.js"></script>
</body>
</html>