<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
	<form action="saveBook" method="post">
		<h3>Enter Book Name you want to add :</h3>
		<input type="text" name="title"> <br />
		<button type="submit">Add</button>
	</form>
</div>