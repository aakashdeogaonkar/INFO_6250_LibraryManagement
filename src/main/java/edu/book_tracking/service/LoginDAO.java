package edu.book_tracking.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import edu.book_tracking.model.AccessLevel;
import edu.book_tracking.model.UserModel;
import edu.book_tracking.util.HibernateUtil;

public class LoginDAO {

	public boolean authenticateLoginUser(String userId,String password){
		
		UserModel um=getUserModelById(userId);
		if(um!=null && um.getUserId().equals(userId) && um.getPassword().equals(password))
		{
			return true;
		}
		else
			return false;
	}

	public UserModel getUserModelById(String userId) {
		Session session=HibernateUtil.openSession();
		UserModel user=null;
		Transaction tx=null;
		try {
			
			 tx=session.beginTransaction();
			 
			 Query query = session.getNamedQuery("UserModel.getById");
			 query.setString("userID", userId);
			 
			 List<UserModel> userModelList= (List<UserModel>)query.list();
			 
			 if(userModelList.size()==1)
				 user=userModelList.get(0);
			 
			session.getTransaction().commit();
		} catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
		return user;
	}
}
