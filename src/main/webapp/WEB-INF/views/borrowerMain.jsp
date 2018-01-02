<%@include file="include.html" %>
<%@page  import="com.gcit.lms.*"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>

<%-- 
<%
BorrowerService borService = new BorrowerService();
List<Borrower> bor = new ArrayList<>();
List<BookCopies> bc = new ArrayList<>();
bor.add(borService.cardValidation(Integer.parseInt(request.getParameter("cardNo"))));
%> --%>
<div class="container">


	<form action = cardNo method ="post">
		<h3 class = "cn">Enter Card Number:</h3>
		<input type="text" id = "cardNo" name ="cardNo" class = "cn"> <br />
		${statusMessage} <br/>
<button type="submit" >Submit</button>

	</form>
	


</div>