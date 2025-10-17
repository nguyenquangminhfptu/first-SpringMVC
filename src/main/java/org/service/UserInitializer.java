package org.service;

import jakarta.annotation.PostConstruct;
import org.dao.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInitializer {
    private UserService userService;
    @Autowired
    public UserInitializer(UserService userService) {
        this.userService = userService;
    }
    @PostConstruct
    public void insertAccount() {

        if (userService.findByUsername("admin") == null) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setRole(1);
            userService.addUser(admin);
        }
        if (userService.findByUsername("manager") == null) {
            User manager = new User();
            manager.setUsername("manager");
            manager.setPassword("manager");
            manager.setRole(2);
            userService.addUser(manager);
        }
        if (userService.findByUsername("guest") == null) {
            User guest = new User();
            guest.setUsername("guest");
            guest.setPassword("guest");
            guest.setRole(3);
            userService.addUser(guest);
        }
    }
}