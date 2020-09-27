package edu.book_tracking.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.util.HibernateUtil;

public class CheckoutDetailsDAO {

	public List<BookOrderModel> getStudentBookOrderDetails(){
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<BookOrderModel> orderdetailslist = null;
		try{
			
			tx = session.beginTransaction();

			Query orderdetails = session.getNamedQuery("BookOrderModel.getStudentBookOrderDetails");
			orderdetailslist = orderdetails.list();
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
		return orderdetailslist;
	}
}
