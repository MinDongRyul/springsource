<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%@include file="../includes/header.jsp" %>
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Board Register</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>            
            <div class="row">
                <div class="col-lg-12">
                	<div class="panel panel-default">
                        <div class="panel-heading">
                           Board Register Page
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                			<form action="" method="post" role="form">
                				<div class="form-group">
                					<label>Title</label>
                					<input class="form-control" name="title">                				
                				</div>  
                				<div class="form-group">
                					<label>Content</label>
                					<textarea class="form-control" rows="3" name="content"></textarea>               				
                				</div> 
                				<div class="form-group">
                					<label>Writer</label>
                					<input class="form-control" name="writer" value='<sec:authentication property="principal.username"/>' readonly>                				
                				</div>  
                				<input type="hidden" name="pageNum" value="1" />
								<input type="hidden" name="amount" value="10" />
								<input type="hidden" name="type" value="${cri.type}" />
								<input type="hidden" name="keyword" value="${cri.keyword}" />
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
                				<button type="submit" class="btn btn-default">Submit</button>              			
                				<button type="reset" class="btn btn-default">reset</button>              			
                			</form>
                		</div>
                	</div>
                </div>
            </div>
        <%-- 파일 첨부 영역 --%>    
        <div class="row">
        	<div class="col-lg-12">
        		<div class="panel panel-default">
					<div class="panel-heading">파일첨부</div>
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
<script>
	//js에서 post방식을 사용할 때 headerName과 Token을 함께 보내주기 위함
	let csrfHeaderName = "${_csrf.headerName}";
	let csrfTokenValue = "${_csrf.token}";
</script>
<script src="/js/upload.js"></script>
<script src="/js/register.js"></script>
<%@include file="../includes/footer.jsp" %>       