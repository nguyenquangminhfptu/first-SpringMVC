package org.service;
import org.dao.User;
import org.dao.UserDao;
import org.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User checkAccount(String username, String password) {
        return userRepository.checkAccount(username, password);
    }

    public boolean addUser(User user) {
        return userRepository.addUser(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
