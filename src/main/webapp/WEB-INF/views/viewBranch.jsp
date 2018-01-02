<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.*"%>
<%@include file="include.html"%>


<div class="container">
	<h3>Find the List of branches in LMS.</h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Branch b: branch){ %>
			<tr>
				<td><%out.println(branch.indexOf(b)+1); %></td>
				<td><%=b.getBranchName() %></td>
				<td><%= b.getBranchAddress() %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBranch.jsp?branchId=<%=b.getBranchId()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>
</div>