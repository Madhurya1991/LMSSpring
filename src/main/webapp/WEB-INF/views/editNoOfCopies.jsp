<%@page  import="com.gcit.lms.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@include file="include.html"%>
<%
LibraryService libService = new LibraryService();
List<Branch> branch2 = new ArrayList<>();
List<BookCopies> bc = new ArrayList<>();
/* branch2 = libService.readBranchByPk(request.getParameter("branchId")); */
Integer branchId;
String branchName;
/* for(Branch b: branch2){
	bc = libService.readBookCopies(b.getBranchId());
} */
%>
<h3>Edit No Of Copies of this library branch.</h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book ID</th>
			<th>title</th>
			<th>Branch ID</th>
			<th>No Of Copies</th>
<!-- 			<th>Delete</th> -->
		</tr>
		<%for(BookCopies b1 : bc){ %>
			<tr>
				<td><%out.println(branch2.indexOf(b1)+1); %></td>
				<td><%=b1.getBookId() %></td>
				<td><%= b1.getBook().getTitle() %></td>
				<td><%= b1.getBranchId() %></td>
				<td><%= b1.getNoOfCopies() %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBranch.jsp?branchId=<%=b1.getBranchId()%>'">Update No Of Copies</button></td>
			</tr>
		<%} %>
	</table>
</div>

	