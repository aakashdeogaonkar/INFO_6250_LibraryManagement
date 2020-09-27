<%@page import="edu.book_tracking.model.BookOrderModel"%>
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
</head>

<%
UserModel user = (UserModel) session.getAttribute("user");
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

					<H2>Book Checkout Page</H2>
	<%
	ArrayList<BookModel> bookNameList = (ArrayList<BookModel>) session.getAttribute("registeredBookNames");
	if(bookNameList!=null && bookNameList.size()!=0){
		
	pageContext.setAttribute("bookNames", bookNameList);
	for(BookModel book:bookNameList)
	{
		System.out.println("Bookid="+book.getBookId());
	}
	
	
	%>
	<form method="post" action="BookCheckoutServlet">

		<table cellpadding='2' cellspacing='2'>
			<tr>
				<td>Select Book for Checkout</td>
				<td><select name="bookDropDown">
						<c:forEach items="${bookNames}" var="bookItem">
							<option value="${bookItem.bookId}">${bookItem.bookName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Book Checkout" /></td>
				<td><input type="submit" value="Go back" onclick="history.back();"   /></td>
			</tr>

		</table>
	</form>
	
	<%} 
	else
	{
	%>
	<h3>You don't have any books available more for checkout</h3>
	<%} %>
		
		<%
			Boolean status= (Boolean)request.getAttribute("bookCheckoutStatus");
			if(status!=null)
			{
				if(status==true)
				{%>
					<form method="post" action="#">
					
						<h2>Your order placed successfully</h2>
				 <%}
				else
				{	
					String selectedBook=(String)request.getAttribute("selectedBookName");
				%>
						<h2>The book "<%=selectedBook %>" you selected is already checkout by you</h2>
				<% }
			}
		%>
			<% List<BookOrderModel> bookOrdList= (List<BookOrderModel>)session.getAttribute("bookOrdersList1");
				if(bookOrdList!=null){
					if(bookOrdList.size()>0)
					{
				%>
						<table border="2">
							<tr>
									<td style="font-weight:bold">Book OrderId</td>
									<td style="font-weight:bold">Book Name</td>
									<td style="font-weight:bold">Order Type </td>
									<td style="font-weight:bold">Checkout Date </td>
									<td style="font-weight:bold">Price</td>
									<td style="font-weight:bold">Due Date</td>
									<td style="font-weight:bold">Return Date</td>
									
									
									
									
								</tr>
							<c:forEach items="${bookOrdersList1}" var="bookorder">
								<tr>
									<td><c:out value="${bookorder.bookOrderId}" /></td>
									<td><c:out value="${bookorder.bookModelObj.bookName}" /></td>
									<td><c:out value="${bookorder.orderType}" /></td>
									<td><c:out value="${bookorder.checkoutDate}" /></td>
									<td>$<c:out value="${bookorder.price}" /></td>
									<td><c:out value="${bookorder.dueDate}" /></td>
									<td><c:out value="${bookorder.returnDate}" /></td>
									
									
								</tr>
				</c:forEach>
						</table>
						<%}
						} %>
						
						
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
							<li><a href="http://my.iit.edu" title="">About US</a></li>
							<li><a href="http://my.iit.edu" title="">Contact US</a></li>
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
				<p>CS 425 - Database Organization Team Project</p>
			</div>

		</footer>
	</div>


</body>

</html>
