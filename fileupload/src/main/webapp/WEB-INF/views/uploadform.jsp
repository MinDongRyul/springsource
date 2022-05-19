<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!--  
	enctype="application/x-www-form-urlencoded" (기본)
	enctype="multipart/form-data" (파일첨부)
-->
	<form action="" method="post" enctype="multipart/form-data">
		<input type="file" name="uploadFile" id="" multiple="multiple"/>
		<button>올리기</button>
	</form>
</body>
</html>