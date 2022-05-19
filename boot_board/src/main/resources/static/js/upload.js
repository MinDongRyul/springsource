/**
 * register.jsp / modify.jsp / read.jsp 파일 업로드 스크립트
 */
$(function(){
	//:file => type="file" / 변화가 일어나면 함수 호출
	$(":file").change(function(){
		console.log("ajax파일 업로드 호출");
		
		//폼 객체 생성
		let formData = new FormData();
		
		//첨부파일 목록 가져오기
		let inputFile = $("[name='uploadFile']");
		console.log(inputFile);
		
		let files = inputFile[0].files;
		
		//폼 객체에 첨부파일들 추가
		for(let i=0; i<files.length; i++){
			
			//사이즈와 확장자명 체크
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			// ajax컨트롤러에서 변수를 받는 이름과 맞춰줘야 한다.
			formData.append("uploadFile", files[i]);
		}
		
		//enctype="multipart/form-data" 와 같은 코드의 의미 ↓
		
		//processData:false => 데이터를 일반적인 쿼리 스트링 형태로 변환할 것인지 결정
		//                     기본값은 true(application/x-www-form-urlencoded)
		//contentType:false => 기본값은 true               
		
		//폼 객체 ajax 전송
		$.ajax({
			url:'/uploadAjax',
			type:'post',
			processData:false,
			contentType:false,
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			data:formData,
			dataType:'json',
			success:function(result){
				//console.log(result);
				
				//파일 첨부하자마 파일 선택 이름에 남아 있는 이름 제거
				//register.jsp의 type=file
				$(":file").val("");
				
				showUploadFile(result);
			}
		})
		
	})//:file에 파일 첨부 종료
		
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

//첨부파일 확장자 및 사이즈 확인
function checkExtension(fileName, fileSize){
	//확장자(정규식)
	let regex = new RegExp("(.*?)\.(png|gif|jpg|txt)$");
	
	//파일크기
	let maxSize = 3145728;// 3MB;
	
	if(!regex.test(fileName)){
		alert("해당 종류의 파일은 업로드 할 수 없습니다.");
		return false;
	}
	
	if(fileSize > maxSize ){
		alert("해당 파일은 사이즈를 초과합니다.");
		return false;
	}
	
	return true;
}

function showUploadFile(result){
		//업로드 결과 영역 가져오기
	let uploadResult = $(".uploadResult ul");
	
	let str = "";
	
	$(result).each(function(idx, obj){
		// obj : attachList
		
		if(obj.fileType){ //이미지 파일
		
			//썸네일 이미지 보여주기
			//썸네일 이미지 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\s_"+obj.uuid+"_"+obj.fileName);
			
			//원본파일 이미지 경로
			let oriPath = obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName;
			
			// \\를 /로 바꾸어서 저장
			oriPath = oriPath.replace(new RegExp(/\\/g),"/");
			
			//get 방식으로 /display 실행
			//직접 경로를 안넣는 이유 : uuid가 랜덤이고 아직 파일에 제대로 생성되지 않았기 때문?!?
			//<a href="javascript:showImage('oriPath')">";
			//실질적으로 보여지는건 image, fileName, button(<i>)
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str += "<a href=\"javascript:showImage(\'"+oriPath+"\')\">";			
			str += "<img src='/display?fileName="+fileCallPath+"'></a>"; // '/display?fileName="+fileCallPath+"' : return 값이 image 
			str += "<div>"+obj.fileName;
			str += " <button type='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='image'>";
			str += "<i class='fa fa-times'></i></button>"; // <i class='fa fa-times'></i> : 버튼안에 보여주는 x 표시
			str += "</div></li>";
			
		}else{ //txt파일
		
			// 다운로드 경로
			let fileCallPath = encodeURIComponent(obj.uploadPath+"\\"+obj.uuid+"_"+obj.fileName);
			
			str += "<li data-path='"+obj.uploadPath+"' data-uuid='"+obj.uuid+"' data-filename='"+obj.fileName+"' data-type='"+obj.fileType+"'>";
			str += "<a href='/download?fileName="+fileCallPath+"'>";
			str += "<img src='/img/attach.png'></a>";
			str += "<div>"+obj.fileName;
			str += " <button type='button' class='btn btn-warning btn-circle' data-file='"+fileCallPath+"' data-type='file'>";
			str += "<i class='fa fa-times'></i></button>";
			str += "</div></li>";
			
		}
	});
	console.log("업로드 파일 경로");
	console.log(str);
	uploadResult.append(str);
}// showUploadFile 종료


function showImage(fileCallPath){
	console.log("showImage 작동 확인");
	
	$(".bigPictureWrapper").css("display","flex").show();
	
	$(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"'>")
					.animate({width:'100%', height:'100%'}, 1000);
}
