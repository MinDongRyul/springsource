/**
 *  changePwd.jsp 관리
 */

$(function(){
	$(".btn-primary").click(function(e){
		
		e.preventDefault();
		
		let param = {
			// jsp 페이지 미리 선언(세션 혹은 dto)을 해놓고 부른 다음
			// 여기에서 선언해준다. 선언할 때는 ''제거 
			userid:userid,
			confirm_password:$("#confirm_password").val()
		}
		
		$.ajax({
			url:userid,
			type:'put',
			contentType:'application/json',
			data:JSON.stringify(param),  // 자바스크립트 객체를 json 형태로 변환시켜줌
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
	})
	
	//
})