package org.dao;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myMVC"); // khớp persistence.xml của bạn

    public List<Employee> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Employee> q = em.createQuery("SELECT e FROM Employee e ORDER BY e.id", Employee.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Employee findById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public Employee save(Employee e) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee out;
            if (e.getId() == 0) {
                em.persist(e);
                out = e;
            } else {
                out = em.merge(e);
            }
            em.getTransaction().commit();
            return out;
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }

    public void delete(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Employee e = em.find(Employee.class, id);
            if (e != null) em.remove(e);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}
