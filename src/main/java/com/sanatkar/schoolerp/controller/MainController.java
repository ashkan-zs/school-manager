package com.sanatkar.schoolerp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Create by ashkan on 2019/06/13
 */
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("login")
    public String login(Model model) {
        return "login/login";
    }

    @GetMapping("login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login/login";
    }

    @GetMapping("accessDenied")
    public String accessDenied() {
        return "error/accessDenied";
    }
}
