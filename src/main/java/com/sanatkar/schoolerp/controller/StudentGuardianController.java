package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.StudentsGuardian;
import com.sanatkar.schoolerp.model.repository.GuardianDao;
import com.sanatkar.schoolerp.model.repository.StudentDao;
import com.sanatkar.schoolerp.model.repository.StudentsGuardianDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/studentGuardians")
public class StudentGuardianController {

    private StudentsGuardianDao studentsGuardianDao;
    private StudentDao studentDao;
    private GuardianDao guardianDao;

    public StudentGuardianController(StudentsGuardianDao studentsGuardianDao, StudentDao studentDao, GuardianDao guardianDao) {
        this.studentsGuardianDao = studentsGuardianDao;
        this.studentDao = studentDao;
        this.guardianDao = guardianDao;
    }

    @GetMapping
    public String getStudentGuardians(Model model) {

        model.addAttribute("studentGuardians", studentsGuardianDao.findAll());

        return "student-guardian/student-guardians";
    }

    @GetMapping("/{id}")
    public String getStudentGuardian(@PathVariable Long id, Model model) {

        model.addAttribute("studentGuardian",
                studentsGuardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid studentGuardian id: " + id)));
        return "student-guardian/student-guardian-detail";
    }

    @GetMapping("/add")
    public String addStudentGuardian(@ModelAttribute("studentGuardian") StudentsGuardian studentsGuardian, Model model) {

        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("guardians", guardianDao.findAll());

        return "student-guardian/student-guardian-add";
    }

    @PostMapping
    public String createStudentGuardian(@ModelAttribute @Valid StudentsGuardian studentsGuardian, BindingResult result) {

        if (result.hasErrors()) {
            return "student-guardian/student-guardian-add";
        }
        studentsGuardianDao.save(studentsGuardian);

        return "redirect:/student-guardians";
    }

    @GetMapping("/edit/{id}")
    public String editStudentGuardian(@PathVariable Long id, Model model) {

        model.addAttribute("studentGuardian",
                studentsGuardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid studentGuardian id: " + id)));
        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("guardians", guardianDao.findAll());

        return "student-guardian/student-guardian-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudentGuardian(@PathVariable Long id, BindingResult result, @ModelAttribute @Valid StudentsGuardian studentsGuardian) {

        if (result.hasErrors()) {
            studentsGuardian.setId(id);
            return "student-guardian/student-guardian-edit";
        }
        studentsGuardianDao.save(studentsGuardian);

        return "redirect:/student-guardians";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentGuardian(@PathVariable Long id) {

        StudentsGuardian studentsGuardian = studentsGuardianDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid studentGuardian id: " + id));
        studentsGuardianDao.delete(studentsGuardian);

        return "redirect:/student-guardians";
    }
}
