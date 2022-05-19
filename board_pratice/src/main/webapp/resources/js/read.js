/**
 *  read.jsp 스크립트
 */
$(function(){
	//operForm 가져오기
	let operForm = $("#operForm");
	
	//list버틀 클릭시 /board/list이동
	$(".btn-info").click(function(){
		
		// operForm bno 태그 제거
		operForm.find("input[name='bno']").remove();
		// operForm action 수정
		operForm.attr("action","/board/list");
		// operForm 보내기
		operForm.submit();
	})
	
	//modify 버튼 클릭시 operForm이동
	$(".btn-default").click(function(){
		operForm.submit();
	})
	
	//-------------------------------------------------
	
	//read.jsp(실험)
	//게시물 아래 게시물 리스트에서 다른 게시물로 클릭시
	let readForm = $("#readForm");
	
	$(".move").click(function(e){
		e.preventDefault();
		
		//a가 가지고 있는 href가지고 오기
		let href = $(this).attr('href');
		
		//actionForm안에 bno태그를 추가하기(값을 href가 가지고 있는 값으로)
		//read에서 뒤로가기를 누르면 남아있는 bno를 계속 추가되는 것을 방지하기 위해 구문을 나눔
		if(readForm.find("[name='bno']").length!=0){			
			readForm.find("[name='bno']").val(href);
		}else{
			readForm.append("<input type='hidden' name ='bno' value = '"+href+"'>");		
		}
		
		//actionForm action변경 => /board/read
		readForm.attr("action","/board/read");
		
		//actionForm 보내기
		readForm.submit();
	})
	//게시물에서 보여주는 리스트에서 다른 게시물로 넘어가는 form

	// ----------------------------------------
	
	//첨부파일 가져오기 - 무조건 실행
	$.getJSON({
		url:'getAttachList',
		data:{
			bno:bno,
		},
		success:function(data){
			console.log(data);
		}
	})


})









