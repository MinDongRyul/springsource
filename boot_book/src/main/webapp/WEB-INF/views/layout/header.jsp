<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/bootstrap-4.6.1-dist/css/bootstrap.min.css" />
<script src="/resources/js/jquery-3.6.0.min.js"></script>
</head>
<body>
	<div class="jumbotron jumbotron-fluid" style ="margin-bottom:0">
  		<div class="container">
    		<h1 class="display-4">도서관리시스템</h1>
    		<p class="lead">model2</p>
 	 	</div>
	</div>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  	  <div class="collapse navbar-collapse" id="navbarNav">
    	<ul class="navbar-nav">
      	  <li class="nav-item active">
            <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
      	  </li>
      	  <li class="nav-item">
            <a class="nav-link" href="/book/insert">도서입력</a>
      	  </li>
      	  <li class="nav-item">
            <a class="nav-link" href="/book/list">도서목록</a>
      	  </li>
      	  <li class="nav-item">
            <a class="nav-link" href="/book/delete">도서삭제</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/book/search">도서검색</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/book/update">도서수정</a>
          </li>
    	</ul>
  	  </div>
	</nav>
<div class="container" style ="margin-top:10px">