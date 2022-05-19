/**
 *  modify.jsp 에서 사용
 */
$(function(){
	//oper Form 가져오기
	let operForm = $("#operForm");
	
	$("button").click(function(e){
		e.preventDefault(); // submit 막기
		
		//현재 눌러진 버튼의 data- 값을 가져오기
		let oper = $(this).data("oper"); //modify를 누르면 oper = modify 
	
		if(oper == "modify"){
			//그대로 간다는 소리?
			//클릭되는 data-oper가 form에 들어감?
			operForm = $("[role='form']");
		}else if(oper == "remove"){
			operForm.attr('action', '/board/remove');
		}else if(oper == "list"){
			operForm.find("[name='bno']").remove();
			operForm.attr('action', '/board/list');	
		}
		
		operForm.submit();
		
	})
	
})