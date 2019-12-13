package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Employee;
import com.sanatkar.schoolerp.model.repository.EmployeeDao;
import com.sanatkar.schoolerp.model.repository.JobTitleDao;
import com.sanatkar.schoolerp.model.repository.SchoolDao;
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
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeDao employeeDao;
    private JobTitleDao jobTitleDao;
    private SchoolDao schoolDao;
    private UserDao userDao;

    public EmployeeController(EmployeeDao employeeDao, JobTitleDao jobTitleDao, SchoolDao schoolDao, UserDao userDao) {
        this.employeeDao = employeeDao;
        this.jobTitleDao = jobTitleDao;
        this.schoolDao = schoolDao;
        this.userDao = userDao;
    }

    @GetMapping
    public String getEmployees(Model model) {

        model.addAttribute("employees", employeeDao.findAll());

        return "employee/employees";
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {

        model.addAttribute("employee",
                employeeDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id)));
        return "employee/employee-detail";
    }

    @GetMapping("/add")
    public String addEmployee(@ModelAttribute("employee") Employee employee, Model model) {

        model.addAttribute("titles", jobTitleDao.findAll());
        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("users", userDao.findAll());

        return "employee/employee-add";
    }

    @PostMapping
    public String createEmployee(@ModelAttribute @Valid Employee employee, BindingResult result) {

        if (result.hasErrors()) {
            return "employee/employee-add";
        }
        employeeDao.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {

        model.addAttribute("employee",
                employeeDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id)));
        model.addAttribute("titles", jobTitleDao.findAll());
        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("users", userDao.findAll());

        return "employee/employee-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, BindingResult result, @ModelAttribute @Valid Employee employee) {

        if (result.hasErrors()) {
            employee.setId(id);
            return "employee/employee-edit";
        }
        employeeDao.save(employee);

        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {

        Employee employee = employeeDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid employee id: " + id));
        employeeDao.delete(employee);

        return "redirect:/employees";
    }

}
