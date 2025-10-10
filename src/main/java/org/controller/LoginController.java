package org.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {


    private final UserService userService;
    @Autowired
    public LoginController(UserService userService) { // ✅ Spring tự truyền vào
        this.userService = userService;
    }

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String checkLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model,
                             HttpServletRequest request) { // 👉 thêm dòng này
        if (userService.checkUser(username, password)) {
            // --- Dòng quan trọng để lưu session ---
            request.getSession().setAttribute("username", username);
            return "redirect:/employees";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
}

//        // Simple check (for demo)
//        if ("admin".equals(username) && "123".equals(password)) {
//            model.addAttribute("username", username);
//            return "welcome"; // redirect to another JSP (will create below)
//        } else {
//            model.addAttribute("error", "Invalid username or password!");
//            return "login";
//        }

