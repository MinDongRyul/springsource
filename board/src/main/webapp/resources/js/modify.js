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
			
			//첨부파일 목록 가져가기
			let str = "";
			//li 태그 정보 수집하기
			$(".uploadResult ul li").each(function(idx, obj){
			//obj를 바로 쓸수 없어서 한번 담아줌
			//바로 쓸 수 없는 이유 (질문)
				var job = $(obj);
				
				//value 는 data("이름")과 js에서 만든 코드 이름을 맞춰준다
				//attachList.이름 은 AttachDTO에서 만든 변수와(BoradDTO에서 만든 이름) 이름을 맞춰준다
				str += "<input type='hidden' name='attachList["+idx+"].uuid' value='"+ job.data("uuid")+"'>";
				str += "<input type='hidden' name='attachList["+idx+"].uploadPath' value='"+ job.data("path")+"'>";
				str += "<input type='hidden' name='attachList["+idx+"].fileName' value='"+ job.data("filename")+"'>";
				str += "<input type='hidden' name='attachList["+idx+"].fileType' value='"+ job.data("type")+"'>";
			})
		
			console.log("form 태그 삽입 전");
			console.log(str);
			
			operForm.append(str);
			
		}else if(oper == "remove"){
			operForm.attr('action', '/board/remove');
		}else if(oper == "list"){
			operForm.find("[name='bno']").remove();
			operForm.attr('action', '/board/list');	
		}
		
		operForm.submit();
	})
	
	//첨부파일-------------------------
	
	//첨부파일 가지고 오기 - 무조건 실행
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno,
		},
		success:function(data){
			console.log(data);
			showUploadFile(data);
		}
	})
	//첨부파일 스크립트 종료
	
	//x버튼 클릭시 화면에서만 삭제
	//파일 최종삭제는 modify 버튼을 누른 후에 해야 함
	$(".uploadResult").on("click","button",function(){
		
		//buttton 태그가 속해있는 li태그 가져오기
		let targetLi = $(this).closest("li");

		if(confirm('정말로 파일을 삭제하시겠습니까?')){
			//li태그 제거
			targetLi.remove();	
		}
	})//x버튼 클릭시 화면에서만 삭제 종료
	
})