<%@page  import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Branch"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.LibraryService"%>
<%@include file="include.html"%>
<%@include file="updateLibrary.jsp"%>
<%-- 
<%
LibraryService libService = new LibraryService();
List<Branch> branch = new ArrayList<>();
branch = libService.readBranchByName(request.getParameter("branchName"));
%> --%>
<h3>Update the details of this library branch.</h3>
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
				<%-- <td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBranch?branchId=<%=b.getBranchId()%>'">Delete</button></td> --%>
			</tr>
		<%} %>
	</table>
</div>