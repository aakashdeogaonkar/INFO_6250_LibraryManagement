<%@page import="edu.book_tracking.model.BookModel"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.book_tracking.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Library</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	  <script>
	  
	  console.log("In javascript function");
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	      if (this.readyState == 4 && this.status == 200) {
	          console.log("Ajax call ha ha ha successful.");
	          //document.getElementById("inputbox").innerHTML = this.responseText;
	          var responseJson = this.responseText;
			  var array = JSON.parse(responseJson);          
	          $( "#inputbox" ).autocomplete({
	              source: array
	            });
	      }
	  };
	  xhttp.open("GET", "http://localhost:8080/highschool_book_tracking/BookSearchServlet", true);
	  xhttp.send();
	  </script>
</head>
<%
UserModel user = (UserModel) session.getAttribute("user");
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
 <body>
<div id="container">
		<header>
			<h1>
				<a href='/'>Library<span>Store</span></a>
			</h1>
			<h2>All books at one place</h2>
		</header>
		<h3>
			Welcome :<b> <%= user.getUserId() %></b>
		</h3>
<nav>

			<ul class="test selected">
				<%
		String userAccessLevel=user.getAccessLevelObj().getAccessLevel();
    	if(userAccessLevel.equalsIgnoreCase("student")){
    		%>
				<li class="start selected"><a href="#">Home</a></li>
				<li class=""><a href="#">Student</a>
					<ul class="dropdown">
						<li><a href="#">Book Checkout</a></li>
					</ul></li>
				<%} 
			%>
				<% if(userAccessLevel.equalsIgnoreCase("parent") ){
			%>
				<li class=""><a href="#">Parent</a>
					<ul class="dropdown">
						<li><a href="#">Child Book Orders</a></li>
					</ul></li>
				<%} 
            %>

				<li class=""><a href="#">Book</a>
					<ul class="dropdown">
						<li><a href="book_search.jsp">Search Books</a></li>
					</ul></li>
				<% if(userAccessLevel.equalsIgnoreCase("admin")){
			%>
				<li class=""><a href="#">Admin</a>
					<ul class="dropdown">
						<li><a href="#">Add Books</a></li>
						<li><a href="#">Delete Books</a></li>
						<li><a href="#">Update Books</a></li>
					</ul></li>
				<%} 
            %>
				<% if(userAccessLevel.equalsIgnoreCase("admin")||userAccessLevel.equalsIgnoreCase("teacher")){
			%>
				<li class=""><a href="#">Reports</a>
					<ul class="dropdown">
						<li><a href="#">Students book checkout </a></li>
						<li><a href="#">Student Due Amount </a></li>
						<li><a href="#">Users Access level </a></li>
						<li><a href="#">Book details</a></li>
						<li><a href="#">Book order </a></li>
						<li><a href="#">Book checkout </a></li>
					</ul></li>
				<%} 
            %>
				<li class="end"><a href="logout.jsp">Sign Out</a></li>
			</ul>
		</nav>
		<img class="header-image" src="img_index1.jpg" width="100%"
			height="100%" alt="Index Page Image" />
		<div id="body">

			<section id="content">

				<article>

					<h2>Search Book in application</h2>

					<form method="post" action="BookSearchServlet">
						<table cellpadding='2' cellspacing='1'>
						    <tr>
						        <td>Enter name of book</td>
						        <td><input type="text" size="15" name="book_name" id="inputbox"></input></td>
						    </tr>
						    <tr>
						        <td colspan='2'>
						            <center><input type="submit" value="Search" /></center>
						        </td>
						    </tr>
						</table>		
				 	</form>
 					 <form method="post" action="#">
 	

			 		<%
			 			Boolean searchStatus= (Boolean)request.getAttribute("searchResult");
			 			if(null != searchStatus && searchStatus==true)
			 			{
			 		%>
			 			<table border="2">
			 					<tr>
									<td>Book Id</td>
									<td>Book Name</td>
									<td>ISBN No</td>
									<td>Book Price</td>
									<td>Book Qty</td>
								</tr>
							<c:forEach items="${bookList}" var="bookItem">
								<tr>
									<td><c:out value="${bookItem.bookId}" /></td>
									<td><c:out value="${bookItem.bookName}" /></td>
									<td><c:out value="${bookItem.isbnNo}" /></td>
									<td><c:out value="${bookItem.bookPrice}" />$</td>
									<td><c:out value="${bookItem.bookQty}" /></td>
								</tr>
							</c:forEach>
						</table>
					<%} else if(searchStatus != null && searchStatus==false)
					{%>
					<h3>No book found for the bookname searched</h3>
					<%} %>
			
 					</form>
				</article>
			</section>

			<aside class="sidebar">

				<ul>
					<li>
						<h4>Quick Links</h4>
						<ul>
							<li><a href="#">Book Checkout</a></li>
							<li><a href="#">Child Book Orders</a></li>
							<li><a href="#">Search Books</a></li>
						</ul>
					</li>

					<li>
						<h4>About us</h4>
						<ul>
							<li class="text">
								<p style="margin: 0;">Library management system.</p>
							</li>
						</ul>
					</li>

					<li>
						<h4>About US</h4>
						<ul>
							<li><a href="https://www.northeastern.edu/" title="">About US</a></li>
							<li><a href="https://www.northeastern.edu/" title="">Contact US</a></li>
						</ul>
					</li>
				</ul>
			</aside>
			<div class="clear"></div>
		</div>
		<footer>
			<div class="footer-content">
				<ul>
					<li><h4>See more books</h4></li>
					<li><a href="#">Search Books</a></li>
				</ul>
				<div class="clear"></div>
			</div>

			<div class="footer-bottom">
				<p>Web Tools And Methods Project</p>
			</div>

		</footer>
	</div>

 		
 </form>


</body>

</html>
