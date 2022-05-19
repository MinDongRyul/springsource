<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                					<input class="form-control" name="writer">                				
                				</div>  
                				<input type="hidden" name="pageNum" value="1" />
								<input type="hidden" name="amount" value="10" />
								<input type="hidden" name="type" value="${cri.type}" />
								<input type="hidden" name="keyword" value="${cri.keyword}" />
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
    
<script src="/resources/js/upload.js"></script>
<script src="/resources/js/register.js"></script>
<%@include file="../includes/footer.jsp" %>       