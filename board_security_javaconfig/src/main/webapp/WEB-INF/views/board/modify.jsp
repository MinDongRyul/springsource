<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- sec를 앞에 두고 명령어 사용 가능 -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Modify</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Modify Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Bno</label>
                					<input class="form-control" name="bno" readonly="readonly" value = "${dto.bno}">                				
                				</div> 
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title"  value = "${dto.title}">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content">${dto.content}</textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" readonly="readonly"  value = "${dto.writer}">                				
                				</div>
                				<input type="hidden" value="${principal.username}"/>
                				
                				<sec:authentication property="principal" var="info"/>  
                				<!-- 둘이 같아야 수정및 삭제 가능 -->
                				<sec:authorize access="isAuthenticated()">
	                				<c:if test="${info.username == dto.writer }">
	                					<button type="submit" data-oper='modify' class="btn btn-default">Modify</button>              			                				
	                					<button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>              			
	                				</c:if>
	                			</sec:authorize>
                				<button type="submit" data-oper='list' class="btn btn-info">List</button>  
                				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />            			
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
				<div class="form-group uploaddiv">
					<input type="file" name="uploadFile" id="" multiple/>
				</div>
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

<%-- remove와 list를 위한 폼--%>
<form action="" id="operForm">
	<input type="hidden" value="${dto.bno}" name="bno" />
	<input type="hidden" value="${dto.writer}" name="writer" />
	<input type="hidden" value="${cri.pageNum}" name="pageNum" />
	<input type="hidden" value="${cri.amount}" name="amount" />
	<input type="hidden" value="${cri.type}" name="type" />
	<input type="hidden" value="${cri.keyword}" name="keyword" />
</form>
<%-- 스크립트 --%>
<script>
	//js에서 post방식을 사용할 때 headerName과 Token을 함께 보내주기 위함
	let csrfHeaderName = "${_csrf.headerName}";
	let csrfTokenValue = "${_csrf.token}";
	
	//현재 글번호, EL
	let bno = ${dto.bno};
</script>
<script src="/resources/js/modify.js"></script>
<script src="/resources/js/upload.js"></script>
<%@include file="../includes/footer.jsp" %>       