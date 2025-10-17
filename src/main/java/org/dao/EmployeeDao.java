package org.dao;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDao {
    @Autowired
    EntityManagerFactory entityManagerFactory;
    public boolean addEmployee(Employee employee){
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employee);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            System.out.println("error 1 : " + e.getMessage());
            return  false;
        }
        finally{
            em.close();
        }
    }

    public Employee getEmployee(int id){

        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select e from Employee e where  e.id = :ei", Employee.class)
                    .setParameter("ei", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }finally{
            em.close();
        }
    }

    public boolean deleteEmployee(int id ) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            if(employee != null){
                em.remove(employee);
                em.getTransaction().commit();
                return true;
            }
            else return false;

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public boolean updateEmployee(Employee employee) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(employee);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    public List<Employee> getAllEmployee() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select e from Employee e", Employee.class)
                    .getResultList();
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }
}
