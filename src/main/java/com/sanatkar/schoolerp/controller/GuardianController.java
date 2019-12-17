package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Guardian;
import com.sanatkar.schoolerp.model.repository.GuardianDao;
import com.sanatkar.schoolerp.model.repository.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/guardians")
public class GuardianController {

    private GuardianDao guardianDao;
    private UserDao userDao;

    public GuardianController(GuardianDao guardianDao, UserDao userDao) {
        this.guardianDao = guardianDao;
        this.userDao = userDao;
    }

    @GetMapping
    public String getGuardians(Model model) {

        model.addAttribute("guardians", guardianDao.findAll());

        return "guardian/guardians";
    }

    @GetMapping("/{id}")
    public String getGuardian(@PathVariable Long id, Model model) {

        model.addAttribute("guardian",
                guardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid guardian id: " + id)));

        return "guardian/guardian-detail";
    }

    @GetMapping("/add")
    public String addGuardian(@ModelAttribute("guardian") Guardian guardian, Model model) {

        model.addAttribute("users", userDao.findAll());

        return "guardian/guardian-add";
    }

    @PostMapping
    public String createGuardian(@ModelAttribute @Valid Guardian guardian, BindingResult result) {

        if (result.hasErrors()) {
            return "guardian/guardian-add";
        }
        guardianDao.save(guardian);

        return "redirect:/guardians";
    }

    @GetMapping("/edit/{id}")
    public String editGuardian(@PathVariable Long id, Model model) {

        model.addAttribute("guardian",
                guardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid guardian id: " + id)));
        model.addAttribute("users", userDao.findAll());

        return "guardian/guardian-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateGuardian(@PathVariable Long id, @ModelAttribute @Valid Guardian guardian, BindingResult result) {

        if (result.hasErrors()) {
            guardian.setId(id);
            return "guardian/guardian-edit";
        }
        guardianDao.save(guardian);

        return "redirect:/guardians";
    }

    @GetMapping("/delete/{id}")
    public String deleteGuardian(@PathVariable Long id) {

        Guardian guardian = guardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid guardian id: " + id));
        guardianDao.delete(guardian);

        return "redirect:/guardians";
    }

}
