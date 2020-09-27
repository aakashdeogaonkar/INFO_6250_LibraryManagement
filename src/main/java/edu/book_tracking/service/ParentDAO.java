package edu.book_tracking.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.ParentModel;
import edu.book_tracking.model.StudentModel;
import edu.book_tracking.util.HibernateUtil;

public class ParentDAO {

	public ParentModel getParentById(String userId) {

		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		ParentModel parent=null;
		try {
			tx = session.beginTransaction();
			
			Query query =session.getNamedNativeQuery("ParentModel.getByUser");
			query.setString("userID", userId);
			
			List<Integer> parIdList= (List<Integer>)query.list();
			System.out.println("Parent obj list size="+parIdList.size());
			if(parIdList.size()==1)
			{
				int parId=parIdList.get(0);
				parent=(ParentModel)session.get(ParentModel.class, parId);
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
		
		return parent;
	}	

}
