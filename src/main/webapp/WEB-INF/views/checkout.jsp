<%@page  import="com.gcit.lms.entity.*"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@include file="include.html"%>

<%
AdminService adService = new AdminService();
List<Branch> branch = new ArrayList<>();
branch = adService.readBranch();
%>

<div class="container">
	<h3>List of library branch.</h3>
<!-- 	<a href="updateLibraryBranch.jsp">Update the details of this library branch</a><br/>  -->
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<th>Select</th> 
		</tr>
		<%for(Branch b: branch){ %>
			<tr>
				<td><%out.println(branch.indexOf(b)+1); %></td>
				<td><%=b.getBranchName() %></td>
				<td><%= b.getBranchAddress() %></td>
				
				<td><button type="button" class="btn btn-primary">Select</button></td>
				<%-- <td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'">Delete</button></td> --%>
			</tr>
		<%} %>
	</table>
	<form action = updateLibrary.jsp method ="post">
		<h3>Enter Branch Name you want to checkout from :</h3>
		<input type="text" name="branchName"> <br />
		<button type="submit">Enter</button>
	</form>
</div>
