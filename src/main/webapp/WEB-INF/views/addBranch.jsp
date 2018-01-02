<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.List"%>

<%@include file="include.html"%>

<div class="container">
	<form action="saveBranch" method="post">
		<h3>Enter Branch Name to Save</h3>
		<input type="text" name="branchName"> <br />
		<h3>Enter Branch Address to Save</h3>
		<input type="text" name="branchAddress"> <br />
		<button type="submit">Save</button>
	</form>
</div>