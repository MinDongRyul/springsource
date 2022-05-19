/**
 *  register.jsp 스크립트 
 */
$(function(){
	$(":submit").click(function(e){
		e.preventDefault();
		
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
		
		//폼보내기
		$("form").append(str)
		         .submit();
	})
	
	//X 버튼 클릭시 첨부 파일 삭제
	$(".uploadResult").on("click","button",function(){
		//button 태그의 data- 속성 가져오기 / data-file, data-type
		let targetFile = $(this).data("file");  // fileCallPath
		let type = $(this).data("type");        // file or image
		
		//buttton 태그가 속해있는 li태그 가져오기
		let targetLi = $(this).closest("li");
		
		$.ajax({
			url:'/deleteFile',
			//controller와 변수명을 맞춰준다
			data:{
				fileName:targetFile,
				type:type
			},
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			type:'post',
			success:function(result){
				console.log(result);
				
				//파일 삭제시 남는 이름 제거
				//register.jsp의 type=file
				$(":file").val("");
				
				//li태그 제거
				targetLi.remove();
			}
		})
	})
	
})











