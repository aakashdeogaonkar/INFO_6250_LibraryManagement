package edu.book_tracking.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.StudentModel;
import edu.book_tracking.util.HibernateUtil;

public class StudentDAO {

	public StudentModel getStudentById(String username) {

		Transaction tx = null;
		Session session = HibernateUtil.openSession();
		StudentModel stud=null;
		
		try {
			tx = session.beginTransaction();
			
			Query query =session.getNamedNativeQuery("StudentModel.getByUser");
			query.setString("userID", username);
			List<Integer> studIdList= (List<Integer>)query.list();
			if(studIdList.size()==1)
			{
				int stuId=studIdList.get(0);
				 stud=(StudentModel)session.get(StudentModel.class, stuId);
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
		return stud;
	}
}
