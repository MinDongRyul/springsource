/**
 *  step2.jsp
 */
$(function(){
	//회원가입 요청
	$(".btn-primary").click(function(e){
		e.preventDefault();		
		
		//입력 데이터 자바스크립트 객체로 생성
		let param = {
			userid:$("#userid").val(),
			password:$("#password").val(),
			name:$("#name").val(),
			gender:$("[type=radio]:checked").val(),
			//checkd를 해주지 않으면 무조건 남 만 넘어간다.
			email:$("#email").val(),
		}
		
		//ajax 통신
		$.ajax({
			url:"new",
			type:"post",
			contentType:"application/json", //json파일일 때 필수
			data:JSON.stringify(param), // 자바스크립트 객체를 json 형태로 변환시켜줌
			success:function(data){ //변수는 임의로 설정
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
		
	})//회원가입 요청 종료
	
	//
})