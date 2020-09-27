package edu.book_tracking.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.BookOrderModel;
import edu.book_tracking.util.HibernateUtil;

public class BookDAO {

	public List<BookModel> getBookDetailsByName(String bookName) {

		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<BookModel> bList=null;
		try {
			tx = session.beginTransaction();

			Query query=session.getNamedQuery("BookModel.getByName");			
			query.setString("bookNamePlaceHolder",bookName+"%");
			 bList= query.list();
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bList;
	}

	public List<String> getAllBookNames() {

		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<String> bList=null;
		
		try {
			tx = session.beginTransaction();
			Query query=session.getNamedQuery("BookModel.getAllBooks");			
			bList= query.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bList;
	}

	public BookModel getBookById(int bId) {
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		BookModel book=null;
		try {
			tx = session.beginTransaction();
			book=(BookModel)session.get(BookModel.class, bId);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return book; 
	}

	public boolean persistBookOrder(BookOrderModel Order) {
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		boolean pflag = true;
		try {
			tx = session.beginTransaction();
			int sId=Order.getStudentModelObj().getStudentId();			
			Query query=session.getNamedNativeQuery("BookOrderModel.getStudentByBookId");
			query.setInteger("bookID", Order.getBookModelObj().getBookId());
			
			boolean sFlag=false;
			List<Integer> studId= (List<Integer>)query.list();
			for(Integer stuId:studId){
				if(sId==stuId){
					sFlag=true;
					break;
				}
			}
			if(sFlag==false){
				session.save(Order);
			}
			else
			{
				pflag=false;
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			pflag = false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pflag;
	}

	public List<BookOrderModel> getAllBookOrders(int studentId) {

		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<Integer> bList=null;
		List<BookOrderModel> bOrderList=new ArrayList<BookOrderModel>();
		try {
			tx = session.beginTransaction();

			Query query=session.getNamedNativeQuery("BookOrderModel.getByStudentID");
			query.setInteger("studentIDPlaceholder", studentId);
			
			bList= (List<Integer>) query.list();
			
			for(Integer bookOrderId:bList)
			{
				BookOrderModel bookOrder= (BookOrderModel)session.get( BookOrderModel.class,bookOrderId );
				bOrderList.add(bookOrder);
			}
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}		
		return bOrderList;
		
	}


	public BookOrderModel persistBookOrderReturn(int bId) {
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		boolean status=false;
		BookOrderModel bOrder=null;
		try {
			tx = session.beginTransaction();
			
			bOrder=(BookOrderModel)session.get(BookOrderModel.class, bId);
			
			if(bOrder!=null){
			Date todayDate=Calendar.getInstance().getTime();
			bOrder.setReturnDate(todayDate);
			
			session.save(bOrder);
			status=true;
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			status=false;
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		return bOrder;
		
	}

	public boolean getValidBookObjects(BookModel book, int stuId) {
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<Integer> bookList=null;
		boolean invalidStatus=false;
		try {
			tx = session.beginTransaction();
			
			Query query=session.getNamedQuery("BookOrderModel.getBookByReturnDate");
			query.setInteger("bookID", book.getBookId());
			query.setInteger("studId", stuId);
			
			
			bookList= (List<Integer>)query.list();
			if(bookList!=null && bookList.size()>0)
			{
				Integer bookModelId=(Integer)bookList.get(0);
				invalidStatus=true;
			}
			
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return invalidStatus	;
	}

	public BookModel getBooksById(Integer bId) {
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		BookModel bModel=null;
		try {
			tx = session.beginTransaction();			
			 bModel=(BookModel)session.get(BookModel.class, bId);
			
			tx.commit();

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bModel;

	}
}
