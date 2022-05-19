/**
 * uploadform_ajax 스크립트
 */
$(function(){
	$("#uploadBtn").click(function(){
		console.log("ajax파일 업로드 호출");
		
		//폼 객체 생성
		let formData = new FormData();
		
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		//폼 객체에 첨부파일들 추가
		for(let i=0; i<files.length; i++){
			formData.append("uploadFile", files[i]);
		}
		
		//enctype="multipart/form-data" 와 같은 코드의 의미 ↓
		
		//processData:false => 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		//                     기본값은 true(application/x-www-form-urlencoded)
		//contentType:false => 기본값은 true               
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			data:formData,
			dataType:'json',
			success:function(result){
				//console.log(result);
				showUploadFile(result);
			}
		})
		
	})//uploadBtn 종료
	
	function showUploadFile(result){
		//업로드 결과 영역 가져오기
		let uploadResult = $(".uploadResult ul");
		
		let str = "";
		
		$(result).each(function(idx, obj){
			
			if(obj.fileType){ //이미지 파일
			
				//썸네일 이미지 보여주기
				//썸네일 이미지 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
				
				//원본파일 이미지 경로
				let oriPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
				//\\를 /로 바꾸어서 저장
				oriPath = oriPath.replace(new RegExp(/\\/g),"/");
				
				//get 방식으로 /display 실행
				//직접 경로를 안넣는 이유 : uuid가 랜덤이기때문에
				//<a href="javascript:showImage('oriPath')">";
				str += "<li><a href=\"javascript:showImage(\'"+oriPath+"\')\">";			
				str += "<img src='/display?fileName="+fileCallPath+"'></a>";
				str += "<div>"+obj.fileName+"<span data-file='"+fileCallPath+"' data-type='image'> x </span>";
				str += "</div></li>";
				
			}else{ //txt파일
			
				// 다운로드 경로
				let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
				
				str += "<li><a href='/download?fileName="+fileCallPath+"'>";
				str += "<img src='/resources/img/attach.png'></a>";
				str += "<div>"+obj.fileName+"<span data-file='"+fileCallPath+"' data-type='file'> x </span>";
				str += "</div></li>";
				
			}
		});
		uploadResult.append(str);
	}// showUploadFile 종료
	
	//첨부파일 삭제 (X 동작)
	$(".uploadResult").on("click","span",function(){
		//span태그의 data- 속성 가져오기 / data-file, data-type
		let targetFile = $(this).data("file");  // fileCallPath
		let type = $(this).data("type");        // file or image
		
		//span 태그가 속해있는 li태그 가져오기
		let targetLi = $(this).closest("li");
		
		$.ajax({
			url:'/deleteFile',
			data:{
				fileName:targetFile,
				type:type
			},
			type:'post',
			success:function(result){
				console.log(result);
				//li태그 제거
				targetLi.remove();
			}
		})
		
	})	
	
	//이미지 종료
	$(".bigPictureWrapper").on("click", function(){
		//서서히 사진 감쳐주기
		$(".bigPicture").animate({width:'0',height:'0'},1000);
		
		//회색 배경 감추기
		setTimeout(function(){
			$(".bigPictureWrapper").hide();
		}, 1000);
	})
})

function showImage(fileCallPath){
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'}, 1000);
}
