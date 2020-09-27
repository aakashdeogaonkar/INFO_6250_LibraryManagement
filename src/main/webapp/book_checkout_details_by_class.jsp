<%@page import="edu.book_tracking.model.BookModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Checkout Details by Class</title>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
	<H2>Book Checkout Details by Class</H2>
	<form method="post" action="BookOrderDetailsServlet">
		<div class="dropdown">
  			<select name="bookordertype">
				<c:forEach items="${bookordertype}" var="bookordertypeselected">
					<option value="${bookordertypeselected}">${bookordertypeselected}</option>
				</c:forEach>
			</select>
		</div>	
  		<button class="dropbtn">Display Results</button>
		<table border="1">
			<th>SrNo.</th>
			<th>BookName</th>
			<th>StudentName</th>
			<th>DueDate</th>
			<th>ReturnDate</th>
			<th>Price</th>
			<th>Order_Type</th>
			<th>Check_Out_Date</th>	
			<c:forEach items="${bookorderdetails}" var="bookorderdetailslist">
				<tr>
					<td><c:out value="${bookorderdetailslist.getBookOrderId()}" /></td>
					<td><c:out value="${bookorderdetailslist.bookModelObj.bookName}" /></td>
					<td><c:out value="${bookorderdetailslist.studentModelObj.studentName}" /></td>
					<td><c:out value="${bookorderdetailslist.dueDate}" /></td>
					<td><c:out value="${bookorderdetailslist.returnDate}" /></td>
					<td><c:out value="${bookorderdetailslist.price}" /></td>
					<td><c:out value="${bookorderdetailslist.orderType}" /></td>
					<td><c:out value="${bookorderdetailslist.checkoutDate}" /></td>
				</tr>
			</c:forEach>
		</table>		
	</form>
</body>
</html>