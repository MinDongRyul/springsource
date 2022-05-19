<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>비밀번호 변경</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <style>
      body {
        margin-top: 100px;
      }
    </style>
  </head>
  <body>
    <div class="card border-success mb-3 mx-auto" style="max-width: 25rem;">
      <div class="card-header">비밀번호 변경</div>
      <div class="card-body">
        <form id="changePwd" method="post">
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="password"
              name="password"
              placeholder="현재 비밀번호"
            />
            <small id="password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="new_password"
              name="new_password"
              placeholder="새 비밀번호"
            />
            <small id="new_password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <input
              type="password"
              class="form-control"
              size="50"
              id="confirm_password"
              name="confirm_password"
              placeholder="새 비밀번호 확인"
            />
            <small id="confirm_password" class="text-info"></small>
          </div>
          <div class="form-group row">
            <button type="submit" class="btn btn-primary btn-block">
              변경
            </button>
          </div>
        </form>
      </div>
    </div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.min.js"></script>

<script type="text/javascript">
  let userid='${loginDto.userid}';
</script>
<script src="/resources/change.js"></script>
<script>
$(function(){
	let error = '${error}';
	//error = 비밀번호 확인 => error != '' 
	//=> 다르므로 true => alert(error)실행
	if(error != ''){
		alert(error);
	}
})
</script>
  </body>
</html>
