package com.sanatkar.schoolerp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by ashkan on 2019/06/13
 */
@Controller
public class MainController {

    @RequestMapping("/")
    public String home() {
        return "home/home";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login/login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/home";
    }
}
