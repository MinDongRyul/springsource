<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>
<h1>Sample</h1>
<div>
	<%-- 모든 요청은 컨트롤러로 가야 함 --%>
	<p><a href="/sample/basic">basic</a></p>
	<p><a href="/sample/doA">doA</a></p>
	<p><a href="/sample/login">login</a></p>
	<p><a href="/sample/insert">insert</a></p>
</div>

<h1>Board</h1>
<div>
	<%-- 모든 요청은 컨트롤러로 가야 함 --%>
	<p><a href="/board/insert">insert</a></p>
	<p><a href="/board/modify">modify</a></p>
	<p><a href="/board/read">read</a></p>
	<p><a href="/board/list">list</a></p>
</div>
</body>
</html>
