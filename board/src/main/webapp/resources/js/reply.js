/**
 * 	read.jsp에서 댓글관리하기 위한 js
 */
//모듈화(서버 쪽의 ajax요청을 모아 둔 함수)
let replyService=(function(){	
	
	//js는 함수를 넣을 수 있다
	//댓글 추가
	function add(reply, callback){
		console.log('add 메소드 실행');
		
		$.ajax({
			url:'/replies/new',
			type:'post',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			contentType:'application/json',
			data:JSON.stringify(reply), //reply자바객체를 json으로 바꿔서 보내줌
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}//add 종료
	
	//특정 원본글의 댓글 리스트
	function getList(param, callback){
		
		let bno = param.bno;
		let page = param.page;
		
		$.getJSON({
			url:'/replies/pages/'+bno+'/'+page,
			success:function(result){
				if(callback){
					//담겨져 있는게 replyCnt와 list가 담겨져있기에 두개를 보냄
					callback(result.replyCnt, result.list);
				}
			}
		})
		
	}// getList 종료
	
	//특정 댓글 가져오기
	function get(rno, callback){

		$.getJSON({
			url:'/replies/'+rno,
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
		
	}// get 종료
	
	//댓글 수정
	function update(reply, callback){

		$.ajax({
			url:'/replies/'+reply.rno,
			type:'put',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			contentType:'application/json',
			data:JSON.stringify(reply),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}//update 종료
	
	//댓글 삭제
	function remove(rno, replyer, callback){

		$.ajax({
			url:'/replies/'+rno,
			type:'delete',
			beforeSend:function(xhr){
				xhr.setRequestHeader(csrfHeaderName, csrfTokenValue)
			},
			contentType:'application/json',
			data:JSON.stringify({
				replyer:replyer
			}),
			success:function(result){
				if(callback){
					callback(result);
				}
			}
		})
	}//remove 종료
	
	// 1651634002000
	function displayTime(timeValue){
		
		// ms => 변환
		// 댓글 단 날짜가 오늘이라면 시분초
		//            오늘이 아니라면 년월일 
			
		let today = new Date();
		
		// 오늘시간 - ms 부터 지금까지의 날짜값
		let gap = today.getTime() - timeValue;
		let dateObj = new Date(timeValue);
		
		//오늘(24시간 미만) 이라면 시분초
		if(gap < (1000*60*60*24)){
			let hh = dateObj.getHours();
			let mi = dateObj.getMinutes();
			let ss = dateObj.getSeconds();
			
			//9보다 작으면 0을 붙혀라 => ex) 01, 02, 03
			return [(hh > 9 ? '':'0')+hh, ':', (mi > 9 ? '':'0')+mi, ':', (ss > 9 ? '':'0')+ss].join('');
		}else{ //오늘이 아니라면 년월일
			let yy = dateObj.getFullYear();
			let mm = dateObj.getMonth() + 1;
			let dd = dateObj.getDate();
			
			return [yy, '/',(mm > 9 ? '':'0')+mm, '/', (dd > 9 ? '':'0')+dd].join('');
		}		
	}//displayTime 종료
	
	// return을 통해서 내보내줘야한다.
	return {
		add:add,
		getList:getList,
		get:get,
		update:update,
		remove:remove,
		displayTime:displayTime
	}
	
})();

$(function(){
	
	//댓글 리스트 영역 가져오기
	let replyUl = $(".chat");
	
	//댓글 페이지 영역 가져오기
	let pageFooter = $(".panel-footer");
	
	//현재 페이지 정보
	let pageNum = 1;
	
	//댓글 전체 가져오기 호출
	showList(1);
	
	//모달 영역 가져오기
	let modal = $(".modal");
	//모달 창 안에 있는 input 요소 찾기
	let modalInputReply = modal.find("input[name='reply']");
	let modalInputReplyer = modal.find("input[name='replyer']");
	let modalInputReplyDate = modal.find("input[name='replydate']");
	
	
//새롭게 만든 창 실험------------------------------------------------------------------------------
	let row = $(".row")
	let modalInputReply1 = row.find("textarea[name='newReply']");
	//let modalInputReplyer1 = row.find("input[name='newReplyer']");
	
	//게시판이 있는곳에서 모달로 들어가지않고 바로바로 댓글을 달 수 있도록 구현해낸 기능(익명 기능)
	$("#addReplyBtn2").click(function(){
	
		//나중에 익명게시판을 만들 때 사용
		let replyer = "익명";
		
		//익명게시판에서 사용할 숫자 취급(1~10 랜덤하게 생성)
		const n = Math.floor(Math.random() * 10 + 1);
		
		
		let reply = {
			bno:bno, 
			reply:modalInputReply1.val(), 
			replyer:replyer+n
		};
		
		replyService.add(reply, function(result){
			if(result){
				//alert(result);
				
				//댓글 등록이 성공하면 
				//댓글 input에 들어있는 내용 지우기
				$("#newReplyName").val("");
				$("#newReplyContent").val("");
				
				//리스트 호출 / 바로 신규 댓글이 보이도록 해주기 위해 -1 삽입
				showList(-1);
			}
		});
	})
//실험기능 종료------------------------------------------------------------------
	
	
	//New Reply 클릭시
	$("#addReplyBtn").click(function(){
		
		//이미 생성된 댓글 클릭후 newreply클릭하면 남아있던 작성자 이름과 댓글내용 삭제
		modal.find("input").val("");
		
		//로그인 사용자 보여주기
		modalInputReplyer.val(replyer).attr("readonly","readonly");
		
		//날짜 input 숨기기, closest("div") : 가장 가까운 부모요소중에 div를 찾아라
		//new reply를 먼저 클릭하면 사라진다.
		modalInputReplyDate.closest("div").hide();
		
		//등록, 닫기 버튼만 보여주기
		//닫기 버튼을 제외한 모든 버튼 숨기기
		modal.find("button[id!='modalCloseBtn']").hide();
		
		//등록 버튼 보여주기
		modal.find("#modalRegisterBtn").show();
		
		//모달 창 보여주기
		modal.modal("show");
	})
	
	//댓글 모달 창 등록 버튼 클릭 시
	$("#modalRegisterBtn").click(function(){	
		let reply = {
			bno:bno, 
			reply:modalInputReply.val(), 
			replyer:modalInputReplyer.val()
		};
		
		replyService.add(reply, function(result){
			if(result){
				//alert(result);
				
				//댓글 등록이 성공하면 
				//모달 input에 들어있는 내용 지우기
				modal.find("input").val("");
				
				//모달 숨기기
				modal.modal("hide");
				
				//리스트 호출-페이지 나누기 전
				//showList(1);
				
				//페이지 나누기 후
				showList(-1);
			}
		});
	})
	
	//댓글 리스트 보여주기
	function showList(page){
		
		// page:page||1 : page 변수값이 들어오면 사용하고 안들어오면 1
		replyService.getList({bno:bno, page:page||1}, function(total, list){
			//console.log(data);
			
			//새 댓글 등록 시 그 리스트가 보이게끔 해주는 기능
			if(page == -1){
				pageNum = Math.ceil(total / 10.0);
				showList(pageNum);
				return;
			}
			
			if(list == null || list.length == 0){
				replyUl.html("");
				return;
			}
			
			let str ="";
			for(var i=0; i<list.length; i++){
				str += '<li class="left clearfix" data-rno="'+list[i].rno +'">';
				str += '<div>';
				str += '<div class="header">';
				str += '<strong class="primary-font">'+ list[i].replyer+'</strong>';
				str += '<small class="pull-right text-muted">'+replyService.displayTime(list[i].replydate)+'</small>';
				str += '</div>';
				str += '<p>'+list[i].reply+'</p>';
				str += '</div></li>';
			}
			
			replyUl.html(str);
			//페이지 나누기를 위한 함수 호출
			showReplyPage(total);
			
			
		});
	}
	
	//댓글 페이지 나누기
	//제일 처음 쓴 댓글이 먼저 보이는 상황 (가장 오래된 댓글)
	function showReplyPage(total){

		let endPage = Math.ceil(pageNum/10.0) * 10;
		let startPage = endPage - 9;		
		let prev = startPage != 1;
		let next = false;
		
		if(endPage * 10  >= total) {
			endPage = Math.ceil(total/10.0);
		}
		
		if(endPage * 10 < total){
			next = true;
		}
		
		let str = '<ul class="pagination pull-right">';
		
		if(prev){
			str += '<li class="paginate_button previous">';
			str += '<a href="'+(startPage - 1)+'">Previous</a></li>';
		}
		
		//페이지 버튼 누를시 표시해주는 기능
		for(let i=startPage; i<=endPage; i++){
			let active = pageNum == i ? 'active':'';
			
			str += '<li class="paginate_button '+active+'">';
			str += '<a href="'+i+'">'+ i +'</a></li>';	
		}
		
		if(next){
			str += '<li class="paginate_button next">';
			str += '<a href="'+(endPage + 1)+'">Next</a></li>';
		}
		
		str += '</ul>';
		
		pageFooter.html(str);
	}
	
	//댓글 페이지 나누기 클릭시
	//위임하는 이유 : js에서 넣어준 영역이기에 jsp에는 존재 x
	//li아래 a를 클릭하면 a기능 중지 후 href를 가져와 showList 호출
	pageFooter.on("click","li a",function(e){
		e.preventDefault();
		
		pageNum = $(this).attr("href");
		showList(pageNum);
	})
	
	
	//댓글 클릭 이벤트 - 이벤트 위임(chat을 이용해 이벤트를 걸고 실제 이벤트의
	//                        대상은 li 태그가 되는 형태)
	//위임 해주는 이유 : jsp상에는 안보이는 것을 js에서 강제로 삽입 해줬기에 위임을 통한 이벤트 발생
	$(".chat").on("click","li",function(){
		
		//사용자가 클릭한 li 안의 rno 값을 가져오기 - data값을 가져올 때는 attr사용 
		let rno = $(this).data("rno");
		
		//mapper에서 read부분
		replyService.get(rno, function(data){
			//console.log(data);
			
			//도착한 댓글을 모달 창의 값에 넣어주기
			modalInputReply.val(data.reply);
			modalInputReplyer.val(data.replyer)
							 .attr("readonly","readonly");
			modalInputReplyDate.val(replyService.displayTime(data.replydate))
			                   .attr("readonly","readonly");
			
			//rno 값을 추가 
			modal.data("rno",data.rno);
			
			//날짜 input 보여주기
			modalInputReplyDate.closest("div").show();
			
			//닫기 버튼을 제외한 모든 버튼 숨기기
			modal.find("button[id!='modalCloseBtn']").hide();
			//수정 버튼 보여주기
			modal.find("#modalModBtn").show();
			//삭제 버튼 보여주기	
			modal.find("#modalRemoveBtn").show();
			
			//모달창 보여주기
			modal.modal("show");
		});
		
	})
	
	//모달창에서 삭제버튼 클릭시 댓글 삭제
	$("#modalRemoveBtn").click(function(){	
		//replyer
		if(!replyer){
			alert('로그인 한 후 삭제가 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		//로그인 사용자(replyer)와 댓글 작성자(modalInputReplyer)가 같은 사람인지 확인
		if(modalInputReplyer.val() != replyer){
			alert('자신의 댓글만 삭제 가능합니다.');
			modal.modal('hide');
			return;
		}

		replyService.remove(modal.data("rno"), replyer, function(result){
			if(result){
				alert(result);
				
				//삭제 성공시 모달 닫기
				modal.modal("hide");
				//리스트 보여주기
				//showList(1);
				
				//페이지 나누기 후
				showList(pageNum);
			}
		});
	})//모달창에서 삭제버튼 클릭시 댓글 삭제
	
	//모달창에서 수정버튼 클릭시 댓글 수정
	$("#modalModBtn").click(function(){
		
		//replyer
		if(!replyer){
			alert('로그인 한 후 수정이 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		//로그인 사용자(replyer)와 댓글 작성자(modalInputReplyer)가 같은 사람인지 확인
		if(modalInputReplyer.val() != replyer){
			alert('자신의 댓글만 수정이 가능합니다.');
			modal.modal('hide');
			return;
		}
		
		let reply = {
			rno:modal.data("rno"), 
			reply:modalInputReply.val(),
			replyer:modalInputReplyer.val()
		};
		
		replyService.update(reply, function(result){
			if(result){
				alert(result);
				
				//수정 성공시 모달 닫기
				modal.modal("hide");
				//리스트 보여주기
				//showList(1);
				
				//페이지 나누기 후
				showList(pageNum);
			}
		});
		
		
	})//모달 수정버튼 클릭시 수정 종료
	
	
})