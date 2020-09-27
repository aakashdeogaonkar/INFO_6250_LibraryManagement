<%@page import="edu.book_tracking.model.BookModel"%>
<%@page import="edu.book_tracking.model.BookOrderModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Checkout Details by Bookname</title>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
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
</body>
</html>