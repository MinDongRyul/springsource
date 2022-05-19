/**
 *  book_test.jsp관리
 */
$(function(){
	//전체 목록 보여주기
	$("#all").click(function(){
		//ajax 방식으로 데이터 요청
		/* $.ajax({
			url:'',
			method:'',
			data:{
				
			},
			dataType:'json',
			success:function(){
				//서버의 응답코드가(HTTP 상태코드) 200일 때	
			} 
		})*/
		
		//가져올 데이터가 json이라면
		$.getJSON({
			url:'list', // GetMapping에서 연결되는 url
			            // post와 get이 둘다 있고 타입이 정해져있지않다면 기본은 get
			
			//성공하면 실행
			success:function(data){
				console.log(data);
				
				//본문 table 영역 변경하기
				let result = $("#result table");
				let str = "";
				
				//foreach느낌
				$.each(data, function(idx, item){
					str += "<tr>";
					str += "<td>"+item.code+"</td>";
					str += "<td>"+item.title+"</td>";
					str += "<td>"+item.writer+"</td>";
					str += "<td>"+item.price+"</td>";
					str += "</tr>";
				})
				result.html(str);
			}
		})
	})//all종료
	
	//특정 도서 정보 보기
	// /book/1000 + GET => 1000번 도서에 대한 정보 가져오기 
	$("#get").click(function(){
		$.getJSON({
			url:'1001',
			success:function(item){
				//본문 table 영역 변경하기
				let result = $("#result table");
				let str = "";
				
				str += "<tr>";
				str += "<td>"+item.code+"</td>";
				str += "<td>"+item.title+"</td>";
				str += "<td>"+item.writer+"</td>";
				str += "<td>"+item.price+"</td>";
				str += "</tr>";
			
				result.html(str);
			}
		})
	})//get종료
	
	//도서 정보 삭제
	$("#delete").click(function(){
		$.ajax({
			url:'1005',
			type:'delete', //@DeleteMapping 연결
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}//xhr:메세지를 담고 있음, status:에러 코드번호를 가지고있음, error:안옴
		})
	})//delete 종료
	
	//도서 정보 수정
	$("#update").click(function(){
		
		let param = {
			price:100000
		};
		
		// 'application/x-www-form-urlencoded;charset=UTF-8' not supported
		// JSON.stringify() : 자바스크립트 객체를 json 형태로 변환시켜줌
		$.ajax({
			url:'1000',
			type:'put',
			contentType:'application/json', // 안할 시 위 에러 발생
			data:JSON.stringify(param), // 자바스크립트 객체를 json 형태로 변환시켜줌
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
			
		})
	})// update 종료
	
	//도서 정보 입력 작업
	$("#insert").click(function(){
		let param = {
			code:$("#code").val(),
			title:$("#title").val(),
			writer:$("#writer").val(),
			price:$("#price").val(),
		}
		
		//데이터를 json형태로 넘길때
		/* $.ajax({
			url:"new",
			type:"post",
			contentType:"application/json",
			data:JSON.stringify(param),
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		}) */
		
		//데이터를 폼으로 보낼때
		$.ajax({
			url:"new2",
			type:"post",
			data:$("form").serialize(), //필수
			success:function(data){
				alert(data);
			},
			error:function(xhr,status,error){
				alert(xhr.responseText);
			}
		})
	})//insert 종료
})