package org.repository;

import org.Irepository.IUserRepository;
import org.dao.User;
import org.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements IUserRepository {
    @Autowired
    UserDao userDao;
    public User checkAccount(String username, String password) {
        return userDao.checkAccount(username, password);
    }
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
    public boolean addUser(User user){
        return userDao.addUser(user);
    }
}
