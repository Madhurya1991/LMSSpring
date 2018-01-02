<%@page  import="com.gcit.lms.entity.Book"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.gcit.lms.entity.*"%>
<%@page import="java.util.List"%>
<%@page import="com.gcit.lms.service.AdminService"%>
<%@include file="include.html"%>

<%
AdminService adminService = new AdminService();
List<Book> books = new ArrayList<>();
if (request.getAttribute("books") != null) {
	books = (List<Book>) request.getAttribute("books");
} else { 
	books = adminService.readBooks(1,null);
	
} 
 Integer totalBooks = adminService.getBooksCount();
Integer totalPagesb = 1;
if (totalBooks % 10 > 0) {
	totalPagesb = totalBooks / 8 + 1;
} else {
	totalPagesb = totalBooks / 8;
} 
%>

<script>
	function searchBooks(){
		$.ajax({
		  url: "searchBooks",
		  data: { 
			  searchString: $('#searchStringb').val()
		  }
		}).done(function(data) {
		    $('#booksTable').html(data);
		});
	}
</script> 
<div class="container">
	<h3>Find the List of Books in LMS.</h3>
	${statusMessage}
	
	<input class="form-control mr-sm-2" type="text" placeholder="Search by Book title" aria-label="Search" id="searchStringb" oninput="searchBooks()">
	
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%
				for (int i = 1; i <= totalPagesb; i++) {
			%>
			<li class="page-item"><a class="page-link"
				href="pageBooks?pageNob=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav> 
	
	<table class="table table-striped" id = "booksTable">
		<tr>
			<th>#</th>
			<th>Title</th>
			<th>Author</th>
			<th>Genre</th>
			<th>Publisher</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%for(Book b: books){ %>
			<tr>
				<td><%out.println(books.indexOf(b)+1);%></td>
				<td><%=b.getTitle() %></td>
		<td>
				<%
					for (Author a : b.getAuthors()) {
							out.println(a.getAuthorName());
						}
				%>
			</td>
	 		<td>
				<%
					for (Genre g : b.getGenres()) {
							out.println(g.getGenreName());
						}
				%>
			</td> 
			<td>
				<%
					for (Publisher p : b.getPublisher()) {
							out.println(p.getPublisherName());
						}
				%>
			</td> 
				<td><button type="button" class="btn btn-primary" onclick="javascript:location.href='editBook.jsp?bookId=<%=b.getBookId()%>'">Edit</button></td>
				<td><button type="button" class="btn btn-danger" onclick="javascript:location.href='deleteBook?bookId=<%=b.getBookId()%>'">Delete</button></td>
			</tr>
		<%} %>
	</table>
</div>