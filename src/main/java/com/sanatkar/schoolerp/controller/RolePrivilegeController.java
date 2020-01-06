package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.RolePrivilege;
import com.sanatkar.schoolerp.model.repository.RolePrivilegeDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2020/01/05
 */
@Controller
@RequestMapping("/rolePrivileges")
public class RolePrivilegeController {

    private RolePrivilegeDao rolePrivilegeDao;

    public RolePrivilegeController(RolePrivilegeDao rolePrivilegeDao) {
        this.rolePrivilegeDao = rolePrivilegeDao;
    }

    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("rolePrivilege", rolePrivilegeDao.findAll());

        return "role-privilege/rolePrivileges";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {

        RolePrivilege rolePrivilege = rolePrivilegeDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("rolePrivilege", rolePrivilege);

        return "role-privilege/rolePrivilege-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute RolePrivilege rolePrivilege) {

        return "role-privilege/rolePrivilege-add";
    }

    @PostMapping
    public String create(@ModelAttribute("rolePriv") @Valid RolePrivilege rolePrivilege, BindingResult result) {

        if (result.hasErrors()) {
            return "role-privilege/rolePrivilege-add";
        }

        rolePrivilegeDao.save(rolePrivilege);

        return "redirect:/rolePrivileges";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        RolePrivilege rolePrivilege = rolePrivilegeDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id" + id));
        model.addAttribute("rolePrivilege", rolePrivilege);

        return "role-privilege/rolePrivilege-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@ModelAttribute @Valid RolePrivilege rolePrivilege, @PathVariable Long id, BindingResult result) {

        if (result.hasErrors()) {
            return "role-privilege/rolePrivilege-edit";
        }

        rolePrivilegeDao.save(rolePrivilege);

        return "redirect:/rolePrivileges";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        RolePrivilege rolePrivilege = rolePrivilegeDao.findById(id)
                .orElseThrow(()->new IllegalArgumentException("Invalid id " + id));

        rolePrivilegeDao.delete(rolePrivilege);

        return "redirect:/rolePrivileges";
    }
}
