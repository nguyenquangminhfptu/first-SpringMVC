package org.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myMVC"); // Đọc persistence.xml trong META-INF

    public boolean checkAccount(String username, String password) {
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<User> query = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                    User.class
            );
            query.setParameter("username", username.trim());
            query.setParameter("password", password.trim());

            // Nếu có kết quả => tài khoản tồn tại
            return query.getResultStream().findFirst().isPresent();

        } finally {
            em.close(); // Đóng EntityManager sau khi xong
        }
    }

    // Gọi khi shutdown ứng dụng (nếu cần)
    public static void closeFactory() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
