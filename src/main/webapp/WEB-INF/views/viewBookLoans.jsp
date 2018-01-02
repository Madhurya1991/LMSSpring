<%@page  import="com.gcit.lms.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>

<%
AdminService adminService = new AdminService();
List<BookLoans> bl = new ArrayList<>();
bl = adminService.readBookLoans();
Integer bookId = null;
Integer branchId = null;
Integer cardNo = null;
System.out.println("bl = "+bl);
%>
<div class="container">
	<h3>Find the List of Books in LMS.</h3>
	${statusMessage}
	<table id = "t1" class="table table-striped">
		<tr>
			<th>#</th>
			<th>Title</th>
			<th>Branch</th>
			<th>Borrower</th>
			<th>DueOut</th>
			<th>DueDate</th>
			<th>DueIn</th>
			<th>Override dueDate</th>
		</tr>
		<%for(BookLoans b: bl){ %>
			<tr>
			<td><%out.println(bl.indexOf(b)+1);%></td>
			<td>
				<%
					for (Book a : b.getBook()) {
					bookId = a.getBookId();
							out.println(a.getTitle());
						}
				%>
			</td> 
			<td>
				<%
					for (Branch a : b.getBranch()) {
						branchId = a.getBranchId();
							out.println(a.getBranchName());
							
						}
				%>
			</td> 
			<td>
				<%
					for (Borrower a : b.getBorrower()) {
						cardNo = a.getCardNo();
							out.println(a.getName());
						}
				%>
			</td> 
			<td><%=b.getDateIn() %></td>
			<td><%=b.getDueDate() %></td>
			<td><%=b.getDateOut() %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBookLoans.jsp?dueDate=<%=b.getDueDate()%>&branchId=<%=branchId%>&bookId=<%=bookId%>&cardNo=<%=cardNo%>'">Override</button></td>
			
			</tr>	
		<%} %> 
	</table>

</div>