package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.ClassTeacher;
import com.sanatkar.schoolerp.model.repository.ClassRoomDao;
import com.sanatkar.schoolerp.model.repository.ClassTeacherDao;
import com.sanatkar.schoolerp.model.repository.EmployeeDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/teachers")
@Log4j2
public class ClassTeacherController {

    private ClassTeacherDao classTeacherDao;
    private EmployeeDao employeeDao;
    private ClassRoomDao classRoomDao;

    public ClassTeacherController(ClassTeacherDao teacherDao, EmployeeDao employeeDao, ClassRoomDao classRoomDao) {
        this.classTeacherDao = teacherDao;
        this.employeeDao = employeeDao;
        this.classRoomDao = classRoomDao;
    }

    @GetMapping
    public String getTeachers(Model model) {

        model.addAttribute("classTeachers", classTeacherDao.findAll());

        return "teacher/teachers";
    }

    @GetMapping("/{id}")
    public String getTeacher(@PathVariable Long id, Model model) {

        model.addAttribute("classTeacher",
                classTeacherDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid classTeacher id: " + id)));

        return "teacher/teacher-detail";
    }

    @GetMapping("/add")
    public String addTeacher(@ModelAttribute("teacher") ClassTeacher teacher, Model model) {

        model.addAttribute("classes", classRoomDao.findAll());
        model.addAttribute("teachers", employeeDao.findAll());

        return "teacher/teacher-add";
    }

    @PostMapping
    public String createTeacher(@ModelAttribute @Valid ClassTeacher teacher, BindingResult result) {

        if (result.hasErrors()) {
            return "teacher/teacher-add";
        }
        classTeacherDao.save(teacher);

        return "redirect:/teachers";
    }

    @GetMapping("/edit/{id}")
    public String editTeacher(@PathVariable Long id, Model model) {

        model.addAttribute("classTeacher",
                classTeacherDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid classTeacher id: " + id)));
        model.addAttribute("classes", classRoomDao.findAll());
        model.addAttribute("teachers", employeeDao.findAll());

        return "teacher/teacher-edit";
    }

    @PostMapping("/edit/{id}") // the order of @PathVariable and @ModelAttribute is important :|
    public String updateTeacher(@PathVariable Long id, @ModelAttribute @Valid ClassTeacher teacher, BindingResult result) {

        if (result.hasErrors()) {
            teacher.setId(id);
            return "teacher/teacher-edit";
        }
        classTeacherDao.save(teacher);

        return "redirect:/teachers";
    }

    @GetMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable Long id) {

        ClassTeacher classTeacher = classTeacherDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid classTeacher id: " + id));
        classTeacherDao.delete(classTeacher);

        return "redirect:/teachers";
    }

}
