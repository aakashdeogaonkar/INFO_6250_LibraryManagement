<%@page import="java.util.List"%>
<%@page import="edu.book_tracking.service.LoginDAO"%>
<%@page import="java.util.Date"%>
<%@page import="edu.book_tracking.model.UserModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Library</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
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
			<h2 style="color:#FF0000;">All books at one place</h2>
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
						<li><a href="book_checkout.jsp">Book Checkout</a></li>
						<li><a href="book_return.jsp">Book Return</a></li>
					</ul></li>
				<%} 
			%>
				<% if(userAccessLevel.equalsIgnoreCase("parent") ){
			%>
				<li class=""><a href="#">Parent</a>
					<ul class="dropdown">
						<li><a href="child_book_orders.jsp">Child Book Orders</a></li>
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
						<li><a href="student_book_checkout_details.jsp">Students book checkout details</a></li>
						<li><a href="DisplayUserAccessLevel.jsp">Users Access level </a></li>
						<li><a href="book_order_details.jsp">Book order details</a></li>
						<li><a href="book_checkout_details_by_bookname.jsp">Book checkout details by BookName</a></li>
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

					<h2>Welcome to Book Store</h2>

					<p>Please handle the books with care. Easy returns</p>

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

</body>

</html>