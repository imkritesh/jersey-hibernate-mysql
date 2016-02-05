package com.leavemanager.hibernate;

import com.leavemanager.domain.Employee;
import com.leavemanager.domain.Vacations;
import com.leavemanager.dto.EmployeeDto;
import com.leavemanager.dto.VacationsDto;
import com.leavemanager.util.HibernateUtil;
import com.leavemanager.util.MappingUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ApplicationFacade {
    public List<EmployeeDto> getAllEmployee(){
        List<EmployeeDto> list = new ArrayList<EmployeeDto>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery("From Employee ");
            List<Employee> l = q.list();
            for (Employee e : l){
                list.add(MappingUtil.employeeToDto(e));
            }
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return list;
    }
    public EmployeeDto getEmployeeById(Long id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Employee> list = new ArrayList<>();
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery("From Employee where id = " + id);
           list = q.list();

        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        EmployeeDto employeeDto = new EmployeeDto();
        if (list.size() == 1)
            employeeDto = MappingUtil.employeeToDto(list.get(0));
        return employeeDto;
    }
    public void addEmployee(EmployeeDto employeeDto){
        Employee employee = MappingUtil.dtoToEmployee(employeeDto);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            try {
                session.save(employee);
                tx.commit();
            }catch (ConstraintViolationException e){
                System.out.println("HELLO:"+e.toString());
            }
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public void deleteEmployee(EmployeeDto employeeDto){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery("From Employee e where e.id = "+employeeDto.getId());
            List<Employee> l = q.list();
            if(l.size() >= 1){
                Employee e = l.remove(0);
                session.delete(e);
                tx.commit();
            }
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }

    public void updateEmployee(EmployeeDto employeeDto){
        Employee employee = MappingUtil.dtoToEmployee(employeeDto);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(employee);
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
    }
    public boolean addVacationsForEmployee(EmployeeDto employeeDto, VacationsDto vacationsDto){
        Employee employee = MappingUtil.dtoToEmployee(employeeDto);
        Vacations vacations = MappingUtil.dtoToVacations(vacationsDto);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            //employee.getVacations().add(vacations);
            vacations.setEmployee(employee);
            try {
                session.save(vacations);
            }catch (org.hibernate.exception.ConstraintViolationException e){
                return false;
            }
            tx.commit();
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        finally{
            session.close();
        }
        return true;
    }

    public List<VacationsDto> listVacationsForEmployee(EmployeeDto employeeDto){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        Employee employee = null;
        try{
            tx = session.beginTransaction();
            Query q = session.createQuery("From Employee e where e.id = "+employeeDto.getId());
            List<Employee> l = q.list();
            if(l.size() >= 1){
                employee = l.remove(0);
                tx.commit();
            }
        }catch(HibernateException e){
            if(tx != null) tx.rollback();
            e.printStackTrace();
        }
        List<Vacations> vacations = employee.getVacations();
        List<VacationsDto> vacationsDtos = new ArrayList<VacationsDto>();
        for(Vacations v : vacations){
            VacationsDto vacationsDto = MappingUtil.vacationsToDto(v);
            vacationsDtos.add(vacationsDto);
        }
        return  vacationsDtos;
    }
    public static void main(String args[]){
        ApplicationFacade ap = new ApplicationFacade();
        Employee e = new Employee("Akaash", "Parashar", "akku121@gmail.com", "777, Sec-37, Noida"
              ,"9582370250", "1/1/1994", "M", "Software Dev Intern");
       // ap.addEmployee(MappingUtil.employeeToDto(e));
       /* Session session = HibernateUtil.getSessionFactory().openSession();
       EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(Integer.toUnsignedLong(12));
        Vacations v = new Vacations("19/2/2016","21/2/2015","Fun Leave",e);
        ap.addVacationsForEmployee(employeeDto, MappingUtil.vacationsToDto(v));
        session.close();*/
      //  ap.addVacationsForEmployee(MappingUtil.employeeToDto(e), MappingUtil.vacationsToDto(v));
        //System.out.println(ap.getEmployeeById(Integer.toUnsignedLong(1)).getFistName());

      /*  EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(Integer.toUnsignedLong(12));
        ap.listVacationsForEmployee(employeeDto);*/
    }
}
