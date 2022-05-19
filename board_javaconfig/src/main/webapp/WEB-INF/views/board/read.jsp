<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Read</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Read Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value = "${dto.bno}">                						
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title" readonly="readonly" value = "${dto.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content" readonly="readonly" >${dto.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly" value = "${dto.writer}">                				
                				</div>  
                				<button type="button" class="btn btn-default">Modify</button>     			
                				<button type="reset" class="btn btn-info">List</button>          			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
            
<%-- 파일 첨부 영역 --%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading"><i class="fa fas fa-file"></i> 첨부파일</div>
			<div class="panel-body">
				<div class="uploadResult">
					<ul><!-- 첨부파일 정보 --></ul>
				</div>
			</div>        		
		</div>
	</div>
</div>
<div class="bigPictureWrapper">
	<div class="bigPicture"></div>
</div>

<!-- 실험삼아 만든 댓글 넣기 -->        
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>
				댓글
				<button id="addReplyBtn2" class="btn btn-primary btn-xs pull-right">새 댓글 달기</button>
			</div>
			<!-- 댓글에서 닉네임을 같이 넣을 때 사용 
				<div calss="panel-body1">
				<label >닉네임 : </label>
				<input type="text" name="newReplyer" id="newReplyName" placeholder="닉네임을 적어 주세요."/>
			</div> -->
			<div calss="panel-body1">
				<!-- 
				<input type="text" name="newReply" id="newReplyContent" placeholder="댓글을 달아 주세요."/>
				 -->			
				<div>				
					<textarea name="newReply" id="newReplyContent" cols="100" rows="2" placeholder="익명으로 댓글을 다실 때는 서로 존중하면서 댓글을 답시다."></textarea>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 실험해보는 기능 -->
        
<%-- 댓글 리스트 영역 --%>
<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i>
				Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
			</div>
			<div class="panel-body">
				<ul class="chat">
					<li class="left clearfix" data-rno="12">
						<div>
							<div class="header">
								<!-- 유저 이름 -->
								<strong class="primary-font">user00</strong>
								<!-- 시간 -->
								<small class="pull-right text-muted">2022-05-04 15:52</small>					
							</div>
							<!-- 댓글 내용 -->
							<p>이게 뭔내용임?</p>
						</div>
					</li>
				</ul>
			<%-- 댓글 페이지 영역 --%>
			<div class="panel-footer"></div>
			</div>
		</div>
	</div>
</div>

<%-- 실험삼아 만든 게시물에서 다른 게시물로 넘어갈 수 있는 리스트 넣어주기 --%>
<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading">
        Board List Page
        <button id="regBtn" type="button" class="btn btn-xs pull-right">
          Register New Board
        </button>
      </div>
      <!-- /.panel-heading -->
      <div class="panel-body">
        <table class="table table-striped table-bordered table-hover">
          <thead>
            <tr>
              <th>번 호</th>
              <th>제 목</th>
              <th>작성자</th>
              <th>작성일</th>
              <th>수정일</th>
            </tr>
          </thead>
          <tbody>
          <!-- 게시판 리스트 반복문 -->
			<c:forEach var="dto" items="${list}"> 
				<tr>
					<td>${dto.bno}</td>
					<td><a href="${dto.bno}" class="move">${dto.title}</a><strong>[${dto.replycnt}]</strong></td>
					<td>${dto.writer}</td>
					<!-- 보여지는 시간의 pattern을 정의 -->
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.regdate}"/></td>
					<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${dto.updatedate}"/></td>
				</tr>          
			</c:forEach>
          </tbody>
        </table>
        <div class="text-center">
          <ul class="pagination">
          	<c:if test="${pageDto.prev}">
          		<!-- 맨처음페이지 보여주기
	            <li class="paginate_button previous"><a href="${1}">처음으로</a></li>      	          		
          		pageNum이 1인 상태에서 누르면 0이 되는 현상 고치기
	            <li class="paginate_button previous"><a href="${pageDto.cri.pageNum - 1}">Previous(1)</a></li>
          		-->
	            <li class="paginate_button previous"><a href="${pageDto.startPage - 1}">Previous</a></li>      	
          	</c:if>
          		      	 
            <c:forEach var="idx" begin="${pageDto.startPage}" end="${pageDto.endPage }">         
	            <li class="paginate_button ${pageDto.cri.pageNum == idx? 'active' : ''}"><a href="${idx}">${idx}</a></li>
            </c:forEach>
            	
            <c:if test="${pageDto.next}">            	      
	            <li class="paginate_button next"><a href="${pageDto.endPage + 1}">Next</a></li>
				<!-- 마지막페이지 보여주기
				<li class="paginate_button next"><a href="${pageDto.realEnd }">마지막으로</a></li> 
				pageNum이 제일 끝인 상태에서 누르면 초과 되는 현상 고치기
	            <li class="paginate_button next"><a href="${pageDto.cri.pageNum + 1}">Next(1)</a></li>
				-->
            </c:if>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
<%-- 실험삼아 만든 게시물에서 다른 게시물로 넘어갈 수 있는 리스트 넣어주기 --%>

<%-- 새로운 댓글 작성 모달폼  --%>
<div class="modal" tabindex="-1" id="replyModal" data-rno="1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
        <h5 class="modal-title">Reply</h5>
      </div>
      <div class="modal-body">
		<div class="form-group">
			<label for="">내용</label>
			<input type="text" name="reply" id="" class="form-control" placeholder="댓글 내용"/>
		</div>
		<div class="form-group">
			<label for="">작성자</label>
			<input type="text" name="replyer" id="" class="form-control" placeholder="작성자"/>
		</div>
		<div class="form-group">
			<label for="">작성일</label>
			<input type="text" name="replydate" id="" class="form-control" placeholder="작성일"/>
		</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-warning" id="modalRegisterBtn">등록</button>
        <button type="button" class="btn btn-success" id="modalModBtn">수정</button>
        <button type="button" class="btn btn-danger" id="modalRemoveBtn">삭제</button>
        <button type="button" class="btn btn-primary" id="modalCloseBtn" data-dismiss="modal">종료</button>
      </div>
    </div>
  </div>
</div>

<%-- modify / list 버튼 클릭시 이동할 폼 --%>
<form action="/board/modify" id="operForm">
	<input type="hidden" value="${dto.bno}" name="bno" />
	<input type="hidden" value="${cri.pageNum}" name="pageNum" />
	<input type="hidden" value="${cri.amount}" name="amount" />
	<input type="hidden" value="${cri.type}" name="type" />
	<input type="hidden" value="${cri.keyword}" name="keyword" />
</form>

<%-- 실험으로 넣어놓음 --%>
<%-- 다른 게시물로 넘어감 --%>
<form action="" id="readForm">
	<input type="hidden" value="${dto.bno}" name="bno" />
	<input type="hidden" value="${cri.pageNum}" name="pageNum" />
	<input type="hidden" value="${cri.amount}" name="amount" />
	<input type="hidden" value="${cri.type}" name="type" />
	<input type="hidden" value="${cri.keyword}" name="keyword" />
</form>
<%-- 실험용 ------------------------ --%>

<script>
	//현재 글번호, EL
	let bno = ${dto.bno};
	//나중에 닉네임을 받아서 알아서 들어가도록 작성해보기. EL 사용
</script>
<script src="/resources/js/read.js"></script>          
<script src="/resources/js/reply.js"></script>          
<script src="/resources/js/upload.js"></script>   
       
<%@include file="../includes/footer.jsp" %>       