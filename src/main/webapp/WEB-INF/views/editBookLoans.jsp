<%@page import="com.gcit.lms.entity.*"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>
<%AdminService adminService = new AdminService();
Integer bookId = Integer.parseInt(request.getParameter("bookId"));
Integer branchId = Integer.parseInt(request.getParameter("branchId"));
Integer cardNo = Integer.parseInt(request.getParameter("cardNo"));
String dueDate = request.getParameter("dueDate");
System.out.println("bo Id = "+bookId+" branch id ="+branchId+" cn ="+cardNo);
/* BookLoans bl = adminService.updateBookLoans(request.getParameter("dueDate"),0);  */
/* BookLoans bl = adminService.readBookLoansByPk(Integer.parseInt(request.getParameter("bookId"))); */
%>
<div class="container">
	<form action="editBookLoans" method="post">
		<h3>By How many days do you want to extend Due date :</h3>
		<input type="text" name="dd"> <br />
	<input type="hidden" name="bookId" value="<%=bookId%>"> <br /> 
	<input type="hidden" name="branchId" value="<%=branchId%>"> <br /> 
	<input type="hidden" name="cardNo" value="<%=cardNo%>"> <br /> 
	<input type="hidden" name="dueDate" value="<%=dueDate%>"> <br /> 
		<button type="submit">Save</button>
	</form>
</div>