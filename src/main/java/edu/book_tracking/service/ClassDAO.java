package edu.book_tracking.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.internal.ClassmateContext;

import edu.book_tracking.model.AttendsModel;
import edu.book_tracking.model.ClassModel;
import edu.book_tracking.model.StudentModel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.util.HibernateUtil;

public class ClassDAO {
	public List<Integer> getAllClassesByUserId(UserModel user) {
		
		StudentModel stud=new StudentModel();
		String userId=user.getUserId();
		Session session=HibernateUtil.openSession();
		List<Integer> classList=null;
		Transaction tx=null;
		try {
			
			 tx=session.beginTransaction();
			 Query query =session.getNamedNativeQuery("StudentModel.getByUser");
			 query.setString("userID", userId);
			 
			 List<Integer> sList= (List<Integer>)query.list();
			 Integer stuId=0;
			 if(sList.size()==1)
				 stuId= sList.get(0);
			 Query attends=session.getNamedQuery("AttendsModel.getClassByStudentId");
			 attends.setInteger("studentIdPlaceholder",stuId);
			 
			 classList= (List<Integer>)attends.list();		 
			 tx.commit();
		} catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
		return classList;
	}

	public List<ClassModel> getClassNameList(List<Integer> cList) {
		
		Session session=HibernateUtil.openSession();
		ArrayList<ClassModel> classList=new ArrayList<ClassModel>();
		Transaction tx=null;
		try {
			
			 tx=session.beginTransaction();
			 for(Integer classId:cList){
				classList.add( (ClassModel)session.get(ClassModel.class, classId));
			 }
			 
			 tx.commit();
		}
		catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
		
		return classList;
	}

}
