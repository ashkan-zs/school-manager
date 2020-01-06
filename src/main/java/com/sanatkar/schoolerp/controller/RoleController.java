package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Role;
import com.sanatkar.schoolerp.model.repository.PrivilegeDao;
import com.sanatkar.schoolerp.model.repository.RoleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/12/21
 */
@Controller
@RequestMapping("/roles")
public class RoleController {

    private RoleDao roleDao;
    private PrivilegeDao privilegeDao;

    public RoleController(RoleDao roleDao, PrivilegeDao privilegeDao) {
        this.roleDao = roleDao;
        this.privilegeDao = privilegeDao;
    }

    @GetMapping
    public String getRoles(Model model) {

        model.addAttribute("roles", roleDao.findAll());

        return "role/roles";
    }

    @GetMapping("/{id}")
    public String getRole(@PathVariable Long id, Model model) {

        Role role = roleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));

        model.addAttribute("role", role);

        return "role/role-detail";
    }

    @GetMapping("/add")
    public String addRole(@ModelAttribute("role") Role role, Model model) {

        model.addAttribute("privileges", privilegeDao.findAll());

        return "role/role-add";
    }

    @PostMapping
    public String createRole(@ModelAttribute @Valid Role role, BindingResult result) {

        if (result.hasErrors()) {
            return "role/role-add";
        }

        roleDao.save(role);

        return "redirect:/roles";
    }

    @GetMapping("/edit/{id}")
    public String editRole(@PathVariable Long id, Model model) {

        Role role = roleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));

        model.addAttribute("role", role);
        model.addAttribute("privileges", privilegeDao.findAll());

        return "role/role-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateRole(@PathVariable Long id, @ModelAttribute @Valid Role role, BindingResult result) {

        if (result.hasErrors()) {
            return "role/role-edit";
        }

        roleDao.save(role);

        return "redirect:/roles";
    }

    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Long id) {

        Role role = roleDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        roleDao.delete(role);

        return "redirect:/roles";
    }
}
