package edu.book_tracking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.StudentModel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.service.BookDAO;
import edu.book_tracking.service.StudentDAO;

@WebServlet("/bookCheckoutServlet")
public class BookCheckout extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			BookDAO Service=new BookDAO();
			StudentDAO student=new StudentDAO();
			
			if(request.getParameter("bookDropDown")!=null)
			{
				int bId=Integer.parseInt(request.getParameter("bookDropDown"));			
				BookModel bObj= Service.getBookById(bId);
				UserModel user = (UserModel) request.getSession().getAttribute("user");
				
				StudentModel sObj= student.getStudentById(user.getUserId());

				Calendar calendar= Calendar.getInstance();
				 Date todayDate=calendar.getTime();
				 calendar.add(Calendar.DATE,10);
				 Date dueDate=calendar.getTime();
				
				BookOrderModel bOrderObj= new BookOrderModel();
				bOrderObj.setBookModelObj(bObj);
				bOrderObj.setStudentModelObj(sObj);
				bOrderObj.setDueDate(dueDate);
				bOrderObj.setOrderType("online");
				bOrderObj.setPrice(bObj.getBookPrice());
				bOrderObj.setCheckoutDate(todayDate);
				
				Boolean status=Service.persistBookOrder(bOrderObj);
				request.setAttribute("selectedBookName", bObj.getBookName());
				request.setAttribute("bookCheckoutStatus", status);
				
				if(status==true)
				{
					ArrayList<BookModel> List = (ArrayList<BookModel>) request.getSession().getAttribute("registeredBookNames");
	
					Iterator<BookModel> Iterator= List.iterator();
					while(Iterator.hasNext())
					{
						BookModel bookModel=Iterator.next();
						if(bookModel.getBookId()==bId)
						{
							Iterator.remove();
						}
					}
				}

				int sId=sObj.getStudentId();
				List<BookOrderModel> bList= Service.getAllBookOrders(sId);
				request.getSession().setAttribute("bookOrdersList1", bList);
				
				RequestDispatcher dispatcher= request.getRequestDispatcher("/book_checkout.jsp");
				dispatcher.forward(request,response);
			}	
	}
}
