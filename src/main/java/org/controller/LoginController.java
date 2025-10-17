package org.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.dao.User;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginpage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String tohome(User user, Model model, HttpSession session) {
        User us = userService.checkAccount(user.getUsername(), user.getPassword());
        session.setAttribute("user", us);

        if (us != null && us.getRole() < 3) {
            session.setAttribute("username", us.getUsername());
            return "redirect:/home";
        }
        else {
            session.setAttribute("username", "Account is invalid");
            return "login";
        }
    }
}


