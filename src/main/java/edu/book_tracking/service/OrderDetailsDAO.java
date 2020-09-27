package edu.book_tracking.service;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.UserModel;

import edu.book_tracking.util.HibernateUtil;

public class OrderDetailsDAO {

	public  List<String> getBookOrderType(){
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<String>  bookorder = new ArrayList<String>();
		
		try{
			
			tx = session.beginTransaction();

			Query bookordertype = session.getNamedQuery("BookOrderModel.getOrderType");
			bookorder = (List<String> )bookordertype.list();
			
			for(String bookTypes:bookorder){
				System.out.println(bookTypes);
			}
		}
		catch(Exception e){
			if (tx != null) {
				tx.rollback();
		}
			e.printStackTrace();
		}
		finally{
			session.close();
		}
		
		return bookorder;
	}

	public List<BookOrderModel> getBookOrderDetails(String bookordertype) {
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<BookOrderModel> bookdetails=null;
		try {
			tx = session.beginTransaction();
			
			Query bookorder = session.getNamedQuery("BookOrderModel.getBookOrderDetails");
			
			bookorder.setString("orderTypePlaceHolder", bookordertype + "%");
			bookdetails = bookorder.list();
			
			for(BookOrderModel book_order_model:bookdetails){
				System.out.println("Book Order Id_-" + book_order_model.getBookOrderId());
			}
			
		}
		catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookdetails;
	}
}
