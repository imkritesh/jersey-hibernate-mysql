package com.leavemanager.hibernate;

import com.leavemanager.domain.Employee;
import com.leavemanager.domain.Vacations;
import com.leavemanager.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Utilities {
    public void addEmployee(Employee employee)
    {

    }
   public static void main(String args[])
   {
       Session session = HibernateUtil.getSessionFactory().openSession();
       /*Transaction tx = null;
       try{
           tx = session.beginTransaction();
           Employee e = new Employee("Kritesh", "Semwal", "kritesh94semwal.ks@gmail.com", "1941 Sec-37, Noida"
                   ,"9582370250", "1/1/2016", "M", "Software Dev Intern");
           session.save(e);
           session.save(new Vacations("1/2/2016","5/2/2015","Sick Leave",e));
           tx.commit();
    }catch(HibernateException e){
        if(tx != null) tx.rollback();
        e.printStackTrace();
    }
    finally{
        session.close();
    }
  */
       Query q = session.createQuery("From Employee e where e.id = 3");
       List<Employee> e = q.list();
       for(Employee em: e){
           System.out.println(em.getFistName());
           List<Vacations> vs = em.getVacations();
           System.out.println(vs.size());
           for(Vacations v: vs){
               System.out.println(v.toString());
           }
       }
   }

}
