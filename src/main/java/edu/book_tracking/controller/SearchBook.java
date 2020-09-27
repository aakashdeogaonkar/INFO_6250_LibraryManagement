package edu.book_tracking.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.service.BookDAO;

public class SearchBook extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		
		BookDAO book=new BookDAO();
		List<String> bookNamesList=book.getAllBookNames();
		String j = new Gson().toJson(bookNamesList);
		response.getWriter().write(j);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bookName= request.getParameter("book_name");

		BookDAO book=new BookDAO();
		
		List<BookModel> List= book.getBookDetailsByName(bookName);
		boolean Result=false;
		if(List.size()!=0)
		{
			Result=true;
		}
		else
		{
			Result=false;
		}
		request.setAttribute("searchResult", Result);
		request.setAttribute("bookList", List);
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("/book_search.jsp");
		dispatcher.forward(request,response);
	}

}
