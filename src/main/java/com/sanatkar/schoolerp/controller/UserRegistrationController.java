package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.controller.dto.UserRegistrationDto;
import com.sanatkar.schoolerp.model.entity.User;
import com.sanatkar.schoolerp.model.repository.RoleDao;
import com.sanatkar.schoolerp.model.repository.UserDao;
import com.sanatkar.schoolerp.model.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/15
 */
@Controller
@RequestMapping("/users")
public class UserRegistrationController {

    private UserService userService;
    private UserDao userDao;
    private RoleDao roleDao;

    public UserRegistrationController(UserService userService, UserDao userDao, RoleDao roleDao) {
        this.userService = userService;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @ModelAttribute("user")
    private UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String getUsers(Model model) {

        model.addAttribute("users", userDao.findAll());

        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {

        User user = userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id:" + id));
        model.addAttribute("user", user);

        return "user/user-edit";
    }

    @GetMapping("/add")
    public String showRegisterForm(Model model) {

        model.addAttribute("auths", roleDao.findAll());

        return "user/user-add";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Model model) {

        User user = userDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user id: " + id));
        model.addAttribute("user", user);

        return "user/user-edit";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result) {

        User existUser = userService.findByUsername(userDto.getUsername());

        if (existUser != null) {
            result.reject("username", "There is already an account registered with this username");
        }

        if (result.hasErrors()) {
            return "user/user-add";
        }

        userService.save(userDto);

        return "redirect:/users?success";
    }
}
