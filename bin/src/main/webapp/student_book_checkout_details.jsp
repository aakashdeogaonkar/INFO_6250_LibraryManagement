<%@page import="edu.book_tracking.model.BookModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student book Checkout Details</title>
</head>
<body>

	<H2>Student book Checkout Details</H2>

	<form method="get" action="StudentBookCheckoutDetailsServlet">
		<H3><input type="submit" value="Student book checkout Details" /></H3>
		
		<table border="1">
			<th>StudentID</th>
			<th>StudentName</th>
			<th>EmailID</th>
			<th>AdvisorID</th>
			<th>UserID</th>
			<th>BookOrderID</th>
			<th>BookName</th>
			<th>DueDate</th>
			<th>ReturnDate</th>
			<th>CheckoutDate</th>
			<c:forEach items="${studentbookorderdetails}" var="studentbookorderdetailslist">
				<tr>
					<td><c:out value="${studentbookorderdetailslist.studentModelObj.studentId}" /></td>
					<td><c:out value="${studentbookorderdetailslist.studentModelObj.studentName}" /></td>
					<td><c:out value="${studentbookorderdetailslist.studentModelObj.emailId}" /></td>
					<td><c:out value="${studentbookorderdetailslist.studentModelObj.advisorId}" /></td>
					<td><c:out value="${studentbookorderdetailslist.studentModelObj.studentId}" /></td>
					<td><c:out value="${studentbookorderdetailslist.bookOrderId}" /></td>
					<td><c:out value="${studentbookorderdetailslist.bookModelObj.bookName}" /></td>
					<td><c:out value="${studentbookorderdetailslist.dueDate}" /></td>
					<td><c:out value="${studentbookorderdetailslist.returnDate}" /></td>
					<td><c:out value="${studentbookorderdetailslist.checkoutDate}" /></td>						
				</tr>
			</c:forEach>
		</table>
		
	</form>

</body>
</html>