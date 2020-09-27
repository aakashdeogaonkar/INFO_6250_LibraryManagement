package edu.book_tracking.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NamedNativeQuery;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.util.HibernateUtil;

public class BooknameDAO {

	public  List<Integer> getBookIdFromBookOrderModel(){
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<Integer> blist = null;
		
		try{			
			tx = session.beginTransaction();
			Query query = session.getNamedNativeQuery("BookOrderModel.getAllBookidfromBookOrder");
			blist = (List<Integer>)query.list();
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
		return blist;
	}

	public List<BookOrderModel> getBookOrderDetailsByBookname(Integer bid) {
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<BookOrderModel> blist = new ArrayList<>();
		List<Integer> bIdList=null;
		BookOrderModel bModel=new BookOrderModel();
		try {
			tx = session.beginTransaction();
			
			Query query = session.getNamedNativeQuery("BookOrderModel.getBookOrderDetailsByBookid");
			
			query.setInteger("bookorderdetailsbookid", bid);
			bIdList = (List<Integer>)query.list();
		
			
			if(bIdList!=null && bIdList.size()>0)
			{
				for(Integer bookorderid:bIdList){
					bModel=null;
					bModel=(BookOrderModel)session.get(BookOrderModel.class, bookorderid);
					if(bModel!=null)
					{
						blist.add(bModel);
					}
				}
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
		return blist;
	}
}
