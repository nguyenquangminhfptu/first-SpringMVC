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

    public List<Department> getDepartments() {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select d from Department d", Department.class).getResultList();
        }catch(Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
