<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../layout/header.jsp" %>
<%
	//List<BookDTO> list = (List<BookDTO>)request.getAttribute("list");
					  
	//Connection con = JdbcUtil.getConnection();
	//BookDAO dao = new BookDAO(con);	
	//List<BookDTO> list = dao.getList();
%>
<%-- 화면 위치 --%>
<h1>도서 목록 보기</h1>
<table class="table">
  	<thead>
      <tr>
   	    <th scope="col">code</th>
        <th scope="col">title</th>
      	<th scope="col">writer</th>
      	<th scope="col">price</th>
      </tr>
  	</thead>
  	<tbody>
  <%-- 도서 목록 --%>
  <%-- <% for(BookDTO dto : list) { 
  	<tr>
  		<td><%=dto.getCode() %></td>
  		<td><%=dto.getTitle() %></td>
  		<td><%=dto.getWriter() %></td>
  		<td><%=dto.getPrice() %></td>
  	</tr>
  	}  --%>
  
  	<c:forEach var="dto" items="${list}"> <!-- var = "변수명" items = "request에담겨있는" -->
  		<tr>
  		<td>${dto.code}</td>
  		<td>${dto.title}</td>
  		<td>${dto.writer}</td>
  		<td>${dto.price}</td>
  	  </tr>
  	</c:forEach>
  </tbody>
</table>
<%@ include file = "../layout/footer.jsp" %>