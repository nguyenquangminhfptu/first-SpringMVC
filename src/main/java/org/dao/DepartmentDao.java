package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao {
    @Autowired
    EntityManagerFactory entityManagerFactory;

    public List<Department> getAllDepartments() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select d from Department d", Department.class).getResultList();
        }catch(Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
    public Department getDepartment(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            return em.find(Department.class, id);
        }finally {
            em.close();
        }
    }
    public boolean addDepartment(Department department) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    public boolean updateDepartment(Department department) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(department);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }
    public boolean deleteDepartment(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            Department department = em.find(Department.class, id);
            em.getTransaction().begin();
            em.remove(department);
            em.getTransaction().commit();
            return true;
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            em.close();
        }
    }

}
