<%@page import="edu.book_tracking.model.BookModel"%>
<%@page import="edu.book_tracking.model.BookOrderModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="edu.book_tracking.model.UserModel"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Checkout Details by Bookname</title>
<link rel="stylesheet" href="styles.css" type="text/css" />
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" />
	  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
	  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
						<li><a href="book_checkout.jsp">Book Checkout</a></li>
						<li><a href="book_return.jsp">Book Return</a></li>
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
					<H2>Book Checkout Details by Bookname</H2>
	<form method="post" action="BookCheckoutByBooknameServlet">
		<div class="dropdown">
  			<select name="bookcheckoutdetailsbybookname">
				<c:forEach items="${bookModel}" var="bookModelselected">
					<option value="${bookModelselected.bookId}">${bookModelselected.bookName}</option>
				</c:forEach>
			</select>
		</div>	
  		<button class="dropbtn">Display Results</button>
				
	</form>
	
	<%
		List<BookOrderModel> bookOrderModelList= (List<BookOrderModel>)request.getAttribute("bookcheckoutbookname");
	
		if(bookOrderModelList!=null){
			if(bookOrderModelList.size()>0)
			{
				
			
	%>
	<form>
	
		<table border="1">
			<th>BookName</th>
			<th>StudentName</th>
			<th>DueDate</th>
			<th>ReturnDate</th>
			<th>Price</th>
			<th>Order_Type</th>
			<th>Check_Out_Date</th>	
			<c:forEach items="${bookcheckoutbookname}" var="bookcheckoutbooknamelist">
				<tr>
					<td><c:out value="${bookcheckoutbooknamelist.bookModelObj.bookName}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.studentModelObj.studentName}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.dueDate}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.returnDate}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.price}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.orderType}" /></td>
					<td><c:out value="${bookcheckoutbooknamelist.checkoutDate}" /></td>

				</tr>
			</c:forEach>
		</table>
	
	</form>
	<%
	
			}
		}
	%>
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