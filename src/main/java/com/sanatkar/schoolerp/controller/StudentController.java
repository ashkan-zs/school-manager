package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Student;
import com.sanatkar.schoolerp.model.repository.EducationalLevelDao;
import com.sanatkar.schoolerp.model.repository.SchoolDao;
import com.sanatkar.schoolerp.model.repository.StudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/19
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private StudentDao studentDao;
    private SchoolDao schoolDao;
    private EducationalLevelDao levelDao;

    public StudentController(StudentDao studentDao, SchoolDao schoolDao, EducationalLevelDao levelDao) {
        this.schoolDao = schoolDao;
        this.studentDao = studentDao;
        this.levelDao = levelDao;
    }

    @GetMapping("/{id}")
    public String getStudent(@PathVariable Long id, Model model) {

        studentDao.findById(id)
                .ifPresent(o -> model.addAttribute("student", o));

        return "student/student-detail";
    }

    @GetMapping
    public String getStudents(Model model) {

        model.addAttribute("students", studentDao.findAll());

        return "student/students";
    }

    @GetMapping("/add")
    public String addStudent(@ModelAttribute("student") Student student, Model model) {

        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("levels", levelDao.findAll());

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

        return "student/student-update";
    }

    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id, @ModelAttribute @Valid Student student, BindingResult result) {

        if (result.hasErrors()) {
            student.setId(id);
            return "student/student-update";
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
