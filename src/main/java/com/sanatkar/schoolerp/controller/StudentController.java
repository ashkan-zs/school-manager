package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.*;
import com.sanatkar.schoolerp.model.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

/**
 * Create by ashkan on 2019/06/19
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentDao studentDao;
    private EmployeeDao employeeDao;
    private SchoolDao schoolDao;
    private EducationalLevelDao levelDao;
    private ClassRoomDao classRoomDao;

    public StudentController(StudentDao studentDao, EmployeeDao employeeDao, SchoolDao schoolDao, EducationalLevelDao levelDao, ClassRoomDao classRoomDao) {
        this.studentDao = studentDao;
        this.employeeDao = employeeDao;
        this.schoolDao = schoolDao;
        this.levelDao = levelDao;
        this.classRoomDao = classRoomDao;
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable Long id, Model model, Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        System.out.println(user.getId() + " " + user.getAuthorities());

        studentDao.findById(id)
                .ifPresent(o -> model.addAttribute("student", o));

        return "student/student-detail";
    }

    @GetMapping
    public String getStudents(Model model, Authentication authentication, HttpServletRequest request) {


        UserDetailsImp user = (UserDetailsImp) authentication.getPrincipal();
        Employee teacher = employeeDao.findByUser(user.getUser());
        System.out.println(teacher);

        // TODO: check user role if it's admin show all students
        if (request.isUserInRole("ADMIN")) {
            model.addAttribute("students", studentDao.findAll());
        } else {
//            model.addAttribute("students", studentDao.findByClassRoom());
        }

        return "student/students";
    }

    @GetMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student, Model model) {

        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("levels", levelDao.findAll());
        model.addAttribute("classes", classRoomDao.findAll());

        return "student/student-add";
    }

    @PostMapping
    public String createStudent(@ModelAttribute @Valid Student student, BindingResult result) {

        if (result.hasErrors()) {
            return "student/student-add";
        }
        studentDao.save(student);

        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable Long id, Model model) {

        Student student = studentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        model.addAttribute("student", student);

        return "student/student-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute @Valid Student student, BindingResult result) {

        if (result.hasErrors()) {
            student.setId(id);
            return "student/student-edit";
        }
        studentDao.save(student);

        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {

        Student student = studentDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + id));
        studentDao.delete(student);

        return "redirect:/students";
    }
}
