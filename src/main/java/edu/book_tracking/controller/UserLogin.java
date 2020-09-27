package edu.book_tracking.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.ClassModel;
import edu.book_tracking.model.ParentModel;
import edu.book_tracking.model.StudentModel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.service.BookDAO;
import edu.book_tracking.service.BooknameDAO;
import edu.book_tracking.service.ClassDAO;
import edu.book_tracking.service.LoginDAO;
import edu.book_tracking.service.OrderDetailsDAO;
import edu.book_tracking.service.ParentDAO;
import edu.book_tracking.service.StudentDAO;

public class UserLogin extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userId");   
	    String password = request.getParameter("password");
	    List<BookModel> bookList=null;
	    
	    LoginDAO login= new LoginDAO();
	    boolean authentication=login.authenticateLoginUser(username, password);
	    
	    BookDAO bookDAO=new BookDAO();
	    if(authentication==true)
	    {
	    	UserModel userModel=login.getUserModelById(username);
	    	
	    	String accessLevel=userModel.getAccessLevelObj().getAccessLevel();
	    	if(accessLevel.equalsIgnoreCase("student"))
	    	{
	    		 bookList=fetchAllBooks(userModel);
	    		 
	    		 StudentDAO studentService=new StudentDAO();
	    		 StudentModel studentObj= studentService.getStudentById(username);
	    		 
	    		 Iterator<BookModel> Iterator= bookList.iterator();
	    		 while(Iterator.hasNext()){
	    			 
	    			 BookModel book=Iterator.next();
	    			 boolean invalidStatus= bookDAO.getValidBookObjects(book,studentObj.getStudentId());
	    			 if(invalidStatus==true)
	    			 {
	    				 Iterator.remove();
	    			 }
	    			 
	    		 }
	    		 request.getSession().setAttribute("registeredBookNames", bookList);
	    		
	    		 int studentId=studentObj.getStudentId();
	    		 
				 List<BookOrderModel> bList= bookDAO.getAllBookOrders(studentId);
				 
				 request.getSession().setAttribute("studentModel", studentObj); 
				 request.getSession().setAttribute("bookOrdersList1", bList);
	    	}
	    	else if(accessLevel.equalsIgnoreCase("parent")){
	    		
	    		ParentDAO parent=new ParentDAO();
	    		ParentModel Obj= parent.getParentById(username);
	    		
	    		
	    		int studentId=Obj.getStudentModelObj().getStudentId();
				List<BookOrderModel> bookOrdersList= bookDAO.getAllBookOrders(studentId);

				Integer dueAmount=0;
				if(bookOrdersList.size()>0){
				for(BookOrderModel bookOrder:bookOrdersList){
					int price= bookOrder.getPrice();
					Date today=Calendar.getInstance().getTime();
					if( bookOrder.getReturnDate()==null )
					{
						if(today.after(bookOrder.getDueDate())){
							dueAmount=dueAmount+price;
						}
					}
					else
					{
						if(bookOrder.getReturnDate().after(bookOrder.getDueDate()))
						{
							dueAmount=dueAmount+price;
						}
					}
				}
				}
				request.getSession().setAttribute("amountDue", dueAmount);
				request.getSession().setAttribute("childDetails", Obj.getStudentModelObj());
				
				request.getSession().setAttribute("childBookOrdersList", bookOrdersList);
				
	    	}
	    	request.getSession().setAttribute("user", userModel);
	    	response.sendRedirect("home.jsp");
	    	
	    	if(accessLevel.equalsIgnoreCase("admin"))
	    	{
	    		OrderDetailsDAO bookorderdetailsservice = new OrderDetailsDAO();
	    		List<String>  bookordertypelist = bookorderdetailsservice.getBookOrderType();
	    		request.getSession().setAttribute("bookordertype", bookordertypelist);	
	    	}
	    	
	    	if(accessLevel.equalsIgnoreCase("admin"))
	    	{
	    		List<BookModel> bookModelList=new ArrayList<>();
	    		
	    		BooknameDAO bookcheckoutbybooknameservice = new BooknameDAO();
	    		List<Integer> bookordermodelbookidlist = bookcheckoutbybooknameservice.getBookIdFromBookOrderModel();
	    		
	    		for(Integer bookId:bookordermodelbookidlist)
	    		{
	    			BookModel bookModel= bookDAO.getBooksById(bookId);
	    			
	    			if(bookModel!=null){
	    			bookModelList.add(bookModel);
	    			}
	    		}
	    		request.getSession().setAttribute("bookModel", bookModelList);		    		
	    	}	    	
	    }
	    else
	    {
	    	response.sendRedirect("error.jsp");
	    }
	}

	private ArrayList<BookModel> fetchAllBooks(UserModel user) {
		
		ClassDAO Service=new ClassDAO();
		List<Integer> ClassIdList=Service.getAllClassesByUserId(user);

		List<ClassModel> ModelList=Service.getClassNameList(ClassIdList);

		ArrayList<BookModel> bList=new ArrayList<BookModel>();
		for(ClassModel classModel:ModelList)
		{
			bList.add(classModel.getBookModelObj());
		}
		return bList;		
	}

}