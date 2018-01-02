<%@page import="com.gcit.lms.entity.Publisher"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>
<%AdminService adminService = new AdminService();
Publisher publisher = adminService.readPublisherByPk(Integer.parseInt(request.getParameter("publisherId")));
%>
<div class="container">
	<form action="editPublisher" method="post">
		<h3>Enter Publisher Name to Edit</h3>
		<input type="text" name="publisherName" value="<%=publisher.getPublisherName()%>"> <br />
		<h3>Enter Publisher Address to Edit</h3>
		<input type="text" name="publisherAddress" value="<%=publisher.getPublisherAddress()%>"> <br />
		<h3>Enter Publisher Phone to Edit</h3>
		<input type="text" name="publisherPhone" value="<%=publisher.getPublisherPhone()%>"> <br />
		<input type="hidden" name="publisherId" value="<%=publisher.getPublisherId()%>"> <br />
		<button type="submit">Save</button>
	</form>
</div>