package edu.book_tracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.service.CheckoutDetailsDAO;

@WebServlet("/StudentBookCheckoutDetailsServlet")
public class StudentBookCheckoutDetails extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CheckoutDetailsDAO studentbookcheckoutdetailsservice = new CheckoutDetailsDAO();
		List<BookOrderModel> studentbookorderdetailslist = studentbookcheckoutdetailsservice.getStudentBookOrderDetails();
		request.setAttribute("studentbookorderdetails", studentbookorderdetailslist);
		RequestDispatcher dispatcher= request.getRequestDispatcher("/student_book_checkout_details.jsp");
		dispatcher.forward(request,response);
	}
}
