<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>

<div class="container">
	<form action="saveAuthor" method="post">
		<h3>Enter Author Name to Save</h3>
		<input type="text" name="authorName"> <br />
		
		<button type="submit">Save</button>
	</form>
</div>