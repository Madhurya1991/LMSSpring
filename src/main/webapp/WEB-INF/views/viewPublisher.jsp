<%@page  import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>

<%
AdminService adminService = new AdminService();
List<Publisher> publishers = new ArrayList<>();
publishers = adminService.readPublisher();
%>
<div class="container">
	<h3>Find the List of Publishers in LMS.</h3>
	${statusMessage}
	<table class="table table-striped">
		<tr>
			<th>#</th>
			<th>Publisher Name</th>
			<th>Publisher Address</th>
			<th>Publisher Phone</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Publisher p: publishers){ %>
			<tr>
				<td><%out.println(publishers.indexOf(p)+1); %></td>
				<td><%=p.getPublisherName() %></td>
				<td><%= p.getPublisherAddress() %></td>
				<td><%= p.getPublisherPhone()  %></td>
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editPublisher.jsp?publisherId=<%=p.getPublisherId()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deletePublisher?publisherId=<%=p.getPublisherId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>
</div>