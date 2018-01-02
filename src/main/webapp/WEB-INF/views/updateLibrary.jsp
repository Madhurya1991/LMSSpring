<%@page  import="com.gcit.lms.entity.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@include file="include.html"%>

<%
LibraryService libService = new LibraryService();
List<Branch> branch = new ArrayList<>();
BookCopies bc = new BookCopies();
/* List<BookCopies> bcs = new ArrayList<>();
bcs = (List<BookCopies>) request.getAttribute("bcs");
System.out.println(bcs.get(0)); */
branch = libService.readBranchByName(request.getParameter("branchName"));
/* bc = libService.readBookCopies(Integer.parseInt(request.getParameter("branchId")));  */
%>

<div class="container">
	<h3>Update the details of this library branch.</h3>
<!-- 	<a href="updateLibraryBranch.jsp">Update the details of this library branch</a><br/>  -->
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Edit</th>
<!-- 			<th>Delete</th> -->
		</tr>
		<%for(Branch b: branch){ %>
			<tr>
				<td><%out.println(branch.indexOf(b)+1); %></td>
				<td><%=b.getBranchName() %></td>
				<td><%= b.getBranchAddress() %></td>
				
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBranch.jsp?branchId=<%=b.getBranchId()%>'">Update</button></td>
				<%-- <td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch2?branchId=<%=b.getBranchId()%>'">Delete</button></td> --%>
			</tr>
		<%} %>
	</table>
</div>
<!-- <div class="container"> -->
<div class="container">
<h3>No Of Copies of this library branch.</h3>
<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Book Id</th>
			<th>Branch Id</th>
			<th>Num of copies</th>
			<th>Edit</th>
<!-- 			<th>Delete</th> -->
		</tr>

			<%-- <tr>

				<td><%=bc.getBookId()%></td>
				<td><%= bc.getBranchId() %></td>
				<td><%= bc.getNoOfCopies()%></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editNoOfCopies.jsp?branchId=<%=bc.getBranchId()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch2?branchId=<%=b.getBranchId()%>'">Delete</button></td>
			</tr> --%>

	</table>
<!-- <a href="editNoOfCopies.jsp"> Click </a> -->
</div>
<%-- <h3>Edit No Of Copies of this library branch.</h3>
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
		<%for(BookCopies b : bc){ %>
			<tr>
				<td><%out.println(branch.indexOf(b)+1); %></td>
				<td><%=b.getBookId() %></td>
				<td><%= b.getBook().getTitle() %></td>
				<td><%= b.getBranchId() %></td>
				<td><%= b.getNoOfCopies() %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBranch.jsp?branchId=<%=b.getBranchId()%>'">Update</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>
</div> 

	
 --%>