<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>

%>
<div class="container">
	<form action="saveBorrower" method="post">
		<h3>Enter Borrower Name to Save</h3>
		<input type="text" name="name"> <br />
		<h3>Enter Borrower Address to Save</h3>
		<input type="text" name="address"> <br />
		<h3>Enter Borrower Phone to Save</h3>
		<input type="text" name="phone"> <br />
		
		<button type="submit">Save</button>
	</form>
</div>