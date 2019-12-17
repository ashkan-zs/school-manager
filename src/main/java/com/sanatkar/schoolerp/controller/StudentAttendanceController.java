package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.StudentAttendance;
import com.sanatkar.schoolerp.model.repository.AcademicYearDao;
import com.sanatkar.schoolerp.model.repository.StudentAttendanceDao;
import com.sanatkar.schoolerp.model.repository.StudentDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/absences")
public class StudentAttendanceController {

    private StudentAttendanceDao studentAttendanceDao;
    private AcademicYearDao yearDao;
    private StudentDao studentDao;

    public StudentAttendanceController(StudentAttendanceDao studentAttendanceDao, AcademicYearDao yearDao, StudentDao studentDao) {
        this.studentAttendanceDao = studentAttendanceDao;
        this.yearDao = yearDao;
        this.studentDao = studentDao;
    }

    @GetMapping
    public String getAbsences(Model model) {

        model.addAttribute("studentAttendances", studentAttendanceDao.findAll());

        return "attendance/absences";
    }

    @GetMapping("/{id}")
    public String getAbsence(@PathVariable Long id, Model model) {

        model.addAttribute("studentAttendance",
                studentAttendanceDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid attendance id: " + id)));

        return "attendance/absence-detail";
    }

    @GetMapping("/add")
    public String addAbsence(@ModelAttribute("absence") StudentAttendance absence, Model model) {

        model.addAttribute("years", yearDao.findAll());
        model.addAttribute("students", studentDao.findAll());

        return "attendance/absence-add";
    }

    @PostMapping
    public String createAbsence(@ModelAttribute @Valid StudentAttendance absence, BindingResult result) {

        if (result.hasErrors()) {
            return "attendance/absence-add";
        }
        studentAttendanceDao.save(absence);

        return "redirect:/absences";
    }

    @GetMapping("/edit/{id}")
    public String editAbsence(@PathVariable Long id, Model model) {

        model.addAttribute("studentAttendance",
                studentAttendanceDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid attendance id: " + id)));
        model.addAttribute("years", yearDao.findAll());
        model.addAttribute("students", studentDao.findAll());

        return "attendance/absence-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateAbsence(@PathVariable Long id, @ModelAttribute @Valid StudentAttendance absence, BindingResult result) {

        if (result.hasErrors()) {
            absence.setId(id);
            return "attendance/absence-edit";
        }
        studentAttendanceDao.save(absence);

        return "redirect:/absences";
    }

    @GetMapping("/delete/{id}")
    public String deleteAbsence(@PathVariable Long id) {

        StudentAttendance attendance = studentAttendanceDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid attendance id: " + id));
        studentAttendanceDao.delete(attendance);

        return "redirect:/absences";
    }

}
