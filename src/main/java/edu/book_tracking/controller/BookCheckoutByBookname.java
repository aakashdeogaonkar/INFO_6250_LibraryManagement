package edu.book_tracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.service.BooknameDAO;

public class BookCheckoutByBookname extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BooknameDAO bookcheckoutbybooknameService= new BooknameDAO();	
		
		if(request.getParameter("bookcheckoutdetailsbybookname")!=null)
		{
			Integer bookid = Integer.parseInt(request.getParameter("bookcheckoutdetailsbybookname"));
			List<BookOrderModel> list = bookcheckoutbybooknameService.getBookOrderDetailsByBookname(bookid);
			request.setAttribute("bookcheckoutbookname", list);
			RequestDispatcher dispatcher= request.getRequestDispatcher("/book_checkout_details_by_bookname.jsp");
			dispatcher.forward(request,response);
		}		
	}

}
