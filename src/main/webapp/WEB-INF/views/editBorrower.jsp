<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>
<%AdminService adminService = new AdminService();
Borrower borrower = adminService.readBorrowerByPk(Integer.parseInt(request.getParameter("cardNo")));
%>
<div class="container">
	<form action="editBorrower" method="post">
		<h3>Enter Borrower Name to Edit</h3>
		<input type="text" name="name" value="<%=borrower.getName()%>"> <br />
		<h3>Enter Borrower Address to Edit</h3>
		<input type="text" name="address" value="<%=borrower.getAddress()%>"> <br />
		<h3>Enter Borrower Phone to Edit</h3>
		<input type="text" name="phone" value="<%=borrower.getPhone()%>"> <br />
		<input type="hidden" name="cardNo" value="<%=borrower.getCardNo()%>"> <br />
		<button type="submit">Save</button>
	</form>
</div>