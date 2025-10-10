package org.service;

import org.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public boolean checkUser(String username, String password) {
        return userDao.checkAccount(username, password);
    }
}
