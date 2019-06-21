package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Course;
import com.sanatkar.schoolerp.model.repository.CourseDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private CourseDao courseDao;

    public CourseController(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @GetMapping
    public String getCourses(Model model) {

        model.addAttribute("courses", courseDao.findAll());

        return "course/courses";
    }

    @GetMapping("/{id}")
    public String getCourse(@PathVariable Long id, Model model) {

        model.addAttribute("course",
                courseDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id)));

        return "course/course-detail";
    }

    @GetMapping("/add")
    public String addCourse(@ModelAttribute("course") Course course) {
        return "course/course-add";
    }

    @PostMapping
    public String createCourse(@ModelAttribute @Valid Course course, BindingResult result) {

        if (result.hasErrors()) {
            return "course/course-add";
        }
        courseDao.save(course);

        return "redirect:/courses";
    }

    @GetMapping("/edit/{id}")
    public String editCourse(@PathVariable Long id, Model model) {

        model.addAttribute("course",
                courseDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id)));

        return "course/course-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateCourse(@PathVariable Long id, BindingResult result, @ModelAttribute @Valid Course course) {

        if (result.hasErrors()) {
            course.setId(id);
            return "course/course-edit";
        }
        courseDao.save(course);

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {

        Course course = courseDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid course id: " + id));
        courseDao.delete(course);

        return "redirect:/courses";
    }

}
