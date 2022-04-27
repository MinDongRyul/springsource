<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Login</h1>
<!-- action을 아무것도 주지 않으면 현재 경로 http://localhost:9090/sample/login -->
<form action="" method="post">
	<div>	
		<label for="userid">userid</label>
		<input type="text" name="userid" id="userid" />
	</div>
	<div>	
		<label for="password">password</label>
		<input type="password" name="password" id="password" />
	</div>
	<div>	
		<label for="addr">addr</label>
		<input type="text" name="addr" id="addr" />
	</div>
	<div>	
		<label for="age">age</label>
		<input type="text" name="age" id="age" />
	</div>
	<div>
		<button type="submit">전송</button>
	</div>
</form>
</body>
</html>