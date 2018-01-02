<%@page  import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Borrower"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>

<%
AdminService adminService = new AdminService();
List<Borrower> borrowers = new ArrayList<>();
borrowers = adminService.readBorrower();
%>
<div class="container">
	<h3>Find the List of Borrowers in LMS.</h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Borrower Name</th>
			<th>Borrower Address</th>
			<th>Borrower Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Borrower b: borrowers){ %>
			<tr>
				<td><%out.println(borrowers.indexOf(b)+1);%></td>
				<td><%=b.getName() %></td>
				<td><%= b.getAddress() %></td>
				<td><%= b.getPhone()  %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBorrower.jsp?cardNo=<%=b.getCardNo()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBorrower?cardNo=<%=b.getCardNo()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>
</div>