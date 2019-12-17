package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.StudentLeaveRequest;
import com.sanatkar.schoolerp.model.repository.GuardianDao;
import com.sanatkar.schoolerp.model.repository.StudentDao;
import com.sanatkar.schoolerp.model.repository.StudentLeaveRequestDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/studentLeaves")
public class StudentLeaveRequestController {

    private StudentLeaveRequestDao leaveRequestDao;
    private StudentDao studentDao;
    private GuardianDao guardianDao;

    public StudentLeaveRequestController(StudentLeaveRequestDao leaveRequestDao, StudentDao studentDao, GuardianDao guardianDao) {
        this.leaveRequestDao = leaveRequestDao;
        this.studentDao = studentDao;
        this.guardianDao = guardianDao;
    }

    @GetMapping
    public String getStudentLeaves(Model model) {

        model.addAttribute("leaves", leaveRequestDao.findAll());

        return "leave/student-leaves";
    }

    @GetMapping("/{id}")
    public String getStudentLeave(@PathVariable Long id, Model model) {

        model.addAttribute("leave",
                leaveRequestDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid leave request id: " + id)));

        return "leave/student-leave-detail";
    }

    @GetMapping("/add")
    public String addStudentLeave(@ModelAttribute("studentLeave") StudentLeaveRequest studentLeave, Model model) {

        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("guardians", guardianDao.findAll());

        return "leave/student-leave-add";
    }

    @PostMapping
    public String createStudentLeave(@ModelAttribute @Valid StudentLeaveRequest studentLeave, BindingResult result) {

        if (result.hasErrors()) {
            return "leave/student-leave-add";
        }
        leaveRequestDao.save(studentLeave);

        return "redirect:/studentLeaves";
    }

    @GetMapping("/edit/{id}")
    public String editStudentLeave(@PathVariable Long id, Model model) {

        model.addAttribute("leave",
                leaveRequestDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid leave request id: " + id)));
        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("guardians", guardianDao.findAll());

        return "leave/student-leave-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateStudentLeave(@PathVariable Long id, @ModelAttribute @Valid StudentLeaveRequest studentLeave, BindingResult result) {

        if (result.hasErrors()) {
            studentLeave.setId(id);
            return "leave/studentLeave-edit";
        }
        leaveRequestDao.save(studentLeave);

        return "redirect:/studentLeaves";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudentLeave(@PathVariable Long id) {

        StudentLeaveRequest leaveRequest = leaveRequestDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid leave request id: " + id));
        leaveRequestDao.delete(leaveRequest);

        return "redirect:/studentLeaves";
    }

}


