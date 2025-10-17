package org.dao;

import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    public User checkAccount(String username, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery("select u from User u where u.username = :us and u.password = :ps", User.class)
                    .setParameter("us", username)
                    .setParameter("ps", password)
                    .getSingleResult();

        } catch (NoResultException e) {
            System.out.println("User not found");
            return null;
        } finally {
            em.close();
        }
    }

    public User findByUsername(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT u FROM User u WHERE u.username = :username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean addUser(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            return true;
        } catch(Exception e){
            System.out.println("error : " + e.getMessage());
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            em.close();
        }
        return false;
    }

}
