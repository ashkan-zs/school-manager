package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.UserRole;
import com.sanatkar.schoolerp.model.repository.UserRoleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2020/01/05
 */
@Controller
@RequestMapping("/userRoles")
public class UserRoleController {

    private UserRoleDao userRoleDao;

    public UserRoleController(UserRoleDao userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("userRoles", userRoleDao.findAll());

        return "user-role/userRoles";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {

        model.addAttribute("userRole",
                userRoleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id))
        );

        return "user-role/userRole-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute UserRole userRole) {
        return "user-role/userRole-add";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid UserRole userRole, BindingResult result) {

        if (result.hasErrors()) {
            return "user-role/userRole-add";
        }

        userRoleDao.save(userRole);

        return "redirect:/userRoles";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("userRole",
                userRoleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id))
        );

        return "user-role/userRole-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute @Valid UserRole userRole, BindingResult result) {

        if (result.hasErrors()) {
            return "user-role/userRole-edit";
        }

        userRoleDao.save(userRole);

        return "redirect:/userRoles";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        userRoleDao.delete(
                userRoleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id" + id))
        );

        return "redirect:/userRoles";
    }

}
