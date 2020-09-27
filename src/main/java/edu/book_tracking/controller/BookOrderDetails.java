package edu.book_tracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.service.OrderDetailsDAO;

public class BookOrderDetails extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDetailsDAO Service= new OrderDetailsDAO();	
		
		if(request.getParameter("bookordertype")!=null)
		{
			String type = request.getParameter("bookordertype");
			List<BookOrderModel> list = Service.getBookOrderDetails(type);
			request.setAttribute("bookorderdetails", list);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/book_order_details.jsp");
			dispatcher.forward(request,response);
		}
	}
}