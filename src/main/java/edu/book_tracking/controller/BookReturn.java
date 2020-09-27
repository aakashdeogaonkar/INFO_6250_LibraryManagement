package edu.book_tracking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.StudentModel;
import edu.book_tracking.service.BookDAO;

@WebServlet("/bookReturnServlet")
public class BookReturn extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String[] Checkboxes= request.getParameterValues("returnCheckboxFamily");
		BookDAO Service=new BookDAO();
		
		StudentModel stu=(StudentModel)request.getSession().getAttribute("studentModel");
		Map<Integer,BookModel> map= new HashMap<Integer,BookModel>();
		BookOrderModel book=new BookOrderModel();
		for(String bookid:Checkboxes){
			int bId= Integer.parseInt(bookid);
			
			book= Service.persistBookOrderReturn(bId);
			if(book!=null){
				map.put(bId, book.getBookModelObj());
			}
		}
		
		ArrayList<BookModel> List = (ArrayList<BookModel>) request.getSession().getAttribute("registeredBookNames");
		Set<Entry<Integer, BookModel>> entrySet = map.entrySet();
		for (Entry entry : entrySet) {
			
			BookModel bookModel= (BookModel) entry.getValue();
			boolean obj=false;
			for(BookModel listBookModel:List){
				if(bookModel.getBookId()==listBookModel.getBookId())
				{
					obj=true;
					break;
				}
			}
			if(obj==false)
			{
				List.add(bookModel);
			}
			
		}
		
		request.getSession().setAttribute("registeredBookNames", List);
		
		List<BookOrderModel> bookList= Service.getAllBookOrders(stu.getStudentId());
		request.getSession().setAttribute("bookOrdersList1", bookList);
		
		RequestDispatcher dispatcher= request.getRequestDispatcher("/book_return.jsp");
		dispatcher.forward(request,response);	
	}

}
