<%@page import="com.gcit.lms.entity.*"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>
<%AdminService adminService = new AdminService();
Book book = adminService.readBookByPk(Integer.parseInt(request.getParameter("bookId")));
%>
<div class="container">
	<form action="editBook" method="post">
		<h3>Enter Book Name to Edit : </h3>
		<input type="text" name="title" value="<%=book.getTitle()%>"> <br />
		<input type="hidden" name="bookId" value="<%=book.getBookId()%>"> <br />
		<button type="submit">Save</button>
	</form>
</div>