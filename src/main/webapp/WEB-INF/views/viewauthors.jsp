<%@page import="com.gcit.lms.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@include file="include.html"%>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.context.annotation.AnnotationConfigApplicationContext" %>
<%@ page import="com.gcit.lms.LMSConfig" %>
<%
ApplicationContext c = new AnnotationConfigApplicationContext(LMSConfig.class);
	AdminService adminService = c.getBean(AdminService.class);
	if (request.getAttribute("authors") != null) {
		authors = (List<Author>) request.getAttribute("authors");
	} else {
		authors = adminService.readAuthors(1, null);
	}
	Integer totalAuthors = adminService.getAuthorsCount();
	Integer totalPages = 1;
	if (totalAuthors % 10 > 0) {
		totalPages = totalAuthors / 8 + 1;
	} else {
		totalPages = totalAuthors / 8;
	}
%>
<script>
	function searchAuthors(){
		$.ajax({
		  url: "searchAuthors",
		  data: { 
			  searchString: $('#searchString').val()
		  }
		}).done(function(data) {
		    $('#authorsTable').html(data);
		});
	}
</script>
<div class="container">
	<h3>Find the List of Authors in LMS.</h3>
	${statusMessage}

		<input class="form-control mr-sm-2" type="text" placeholder="Search by Author Name" aria-label="Search" id="searchString" oninput="searchAuthors()">
	</form>
	<nav aria-label="Page navigation example">
		<ul class="pagination">
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Previous"> <span aria-hidden="true">&laquo;</span> <span
					class="sr-only">Previous</span>
			</a></li>
			<%
				for (int i = 1; i <= totalPages; i++) {
			%>
			<li class="page-item"><a class="page-link"
				href="pageAuthors?pageNo=<%=i%>"><%=i%></a></li>
			<%
				}
			%>
			<li class="page-item"><a class="page-link" href="#"
				aria-label="Next"> <span aria-hidden="true">&raquo;</span> <span
					class="sr-only">Next</span>
			</a></li>
		</ul>
	</nav>
	<table class="table table-striped" id="authorsTable">
		<tr>
			<th>#</th>
			<th>Author Name</th>
			<th>Books Written</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<%
			for (Author a : authors) {
		%>
		<tr>
			<td>
				<%
					out.println(authors.indexOf(a) + 1);
				%>
			</td>
			<td><%=a.getAuthorName()%></td>
			<td>
				<%
					for (Book b : a.getBooks()) {
							out.println(b.getTitle() + " |");
						}
				%>
			</td>
			<td><button type="button" class="btn btn-primary"
					onclick="javascript:location.href='editauthor.jsp?authorId=<%=a.getAuthorId()%>'">Edit</button></td>
			<td><button type="button" class="btn btn-danger"
					onclick="javascript:location.href='deleteAuthor?authorId=<%=a.getAuthorId()%>'">Delete</button></td>
		</tr>
		<%
			}
		%>
	</table>
</div>