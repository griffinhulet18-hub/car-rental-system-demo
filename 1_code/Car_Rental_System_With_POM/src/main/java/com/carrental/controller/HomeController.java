package com.carrental.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    // Home page: login only
    @GetMapping("/")
    public String home(HttpSession session) {
        if (session.getAttribute("username") != null) {
            return "redirect:/dashboard";
        }
        return "home";
    }

    // Login page
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Handle login
    @PostMapping("/login")
    public String handleLogin(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        // Admin login
        if ("admin".equals(username) && "admin".equals(password)) {
            session.setAttribute("role", "ADMIN");
            session.setAttribute("username", "admin");
            return "redirect:/dashboard";
        }

        // Customer login: test
        if ("test".equals(username) && "test".equals(password)) {
            session.setAttribute("role", "CUSTOMER");
            session.setAttribute("username", "test");
            return "redirect:/dashboard";
        }

        // Customer login: test2
        if ("test2".equals(username) && "test2".equals(password)) {
            session.setAttribute("role", "CUSTOMER");
            session.setAttribute("username", "test2");
            return "redirect:/dashboard";
        }

        // Invalid login
        return "redirect:/login";
    }

    // Dashboard after login
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        return "dashboard";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
