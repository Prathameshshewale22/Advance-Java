package Dao;

import pojos.Employee;
import static utils.HibernateUtils.getFactory;

import java.io.Serializable;

import javax.management.RuntimeErrorException;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDao implements IEmployeeDao {
       String msg=null;
	public String SaveUserDetails(Employee emp) {
		Session session=getFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		
		try{
			
			Integer id =(Integer) session.save(emp);
			tx.commit();
			 msg = "User regd with ID=" + id;
		}catch(RuntimeException e) {
			if(tx!=null) {
				tx.rollback();
			}
			throw e;
		}
		finally {
			if(session!=null){
				session.close();
			}
		}
		return msg;
	}
	
	

}