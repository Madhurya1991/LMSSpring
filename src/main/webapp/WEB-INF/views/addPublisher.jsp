<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.List"%>

<%@include file="include.html"%>

<div class="container">
	<form action="savePublisher" method="post">
		<h3>Enter Publisher Name to Save</h3>
		<input type="text" name="publisherName"> <br />
		<h3>Enter Publisher Address to Save</h3>
		<input type="text" name="publisherAddress"> <br />
		<h3>Enter Publisher Phone to Save</h3>
		<input type="text" name="publisherPhone"> <br />
		
		<button type="submit">Save</button>
	</form>
</div>