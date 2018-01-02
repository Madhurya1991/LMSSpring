<%@include file="include.html" %>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.*"%>
<%
AdminService adminService = new AdminService();
List<Branch> branch = new ArrayList<>();
branch = adminService.readBranch();
%>
<%
LibraryService libService = new LibraryService();
List<Branch> branch2 = new ArrayList<>();
branch2 = libService.readBranchByName("branchName");
%>

<div class="container">
	<h3>Hi Librarian! List of Library Branches :</h3>
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Branch Name</th>
			<th>Branch Address</th>
			<!-- <th>Edit</th>
			<th>Delete</th> -->
		</tr>
		<%for(Branch b: branch){ %>
			<tr>
<%-- 				<td><%out.println(branch.indexOf(b)+1); %></td> --%>
				<td><%=b.getBranchId() %></td>
				<td><%=b.getBranchName() %></td>
				<td><%= b.getBranchAddress() %></td>
				<%-- <td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBranch.jsp?branchId=<%=b.getBranchId()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'">Delete</button></td> --%>
			</tr>
		<%} %>
	</table>
	
	<div class="container">
	<form action = updateLibrary.jsp method ="post">
		<h3>Enter Branch Name you manage :</h3>
		<input type="text" name="branchName"> <br />
		<input type="hidden" name="branchId" value="<%=branch.get(0)%>"> <br />
		<button type="submit">Enter</button>
	</form>
</div>
</div>



<%-- <div class="container">
	<h3>Find the List of Library branches in LMS.</h3>
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
<div class="container">
<h3>Add New Library Branch </h3>
<button type="button" class="btn btn-primary" onclick="javascript:location.href='addBranch.jsp'">Add</button> 
</div>


 <a href="updateLibrary.jsp">Update Library branches</a><br /> 
	<a href="editNocopies.jsp">Edit no of copies</a><br />  --%>
	

