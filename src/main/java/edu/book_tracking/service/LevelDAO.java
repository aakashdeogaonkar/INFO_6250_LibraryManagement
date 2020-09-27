package edu.book_tracking.service;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.BookModel;
import edu.book_tracking.model.UserModel;

import edu.book_tracking.util.HibernateUtil;

public class LevelDAO {

	public List<UserModel> getusersaccess(){
		
		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		List<UserModel>  accessList = null;
		try{
			
			tx = session.beginTransaction();

			Query accesslevel = session.getNamedQuery("UserModel.getAllUsers");
			accessList = accesslevel.list();
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
		return accessList;
	}

}
