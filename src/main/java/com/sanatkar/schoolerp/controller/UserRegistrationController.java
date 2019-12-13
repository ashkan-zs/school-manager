package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.controller.dto.UserRegistrationDto;
import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.repository.AuthorityDao;
import com.sanatkar.schoolerp.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/15
 */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    private UserService userService;
    private AuthorityDao authorityDao;

    public UserRegistrationController(UserService userService, AuthorityDao authorityDao) {
        this.userService = userService;
        this.authorityDao = authorityDao;
    }

    @ModelAttribute("user")
    private UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegisterForm(Model model) {

        model.addAttribute("auths", authorityDao.findAll());

        return "login/registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

        User existUser = userService.findByUsername(userDto.getUsername());

        if (existUser != null) {
            result.reject("username", "There is already an account registered with this username");
        }

        if (result.hasErrors()) {
            return "login/registration";
        }

        userService.save(userDto);

        return "redirect:/registration?success";
    }
}
