package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.CourseLevel;
import com.sanatkar.schoolerp.model.repository.CourseLevelDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2020/01/05
 */
@Controller
@RequestMapping("/courseLevels")
public class CourseLevelController {

    private CourseLevelDao courseLevelDao;

    public CourseLevelController(CourseLevelDao courseLevelDao) {
        this.courseLevelDao = courseLevelDao;
    }

    @GetMapping
    public String getAll(Model model) {

        model.addAttribute("courseLevels", courseLevelDao.findAll());

        return "course-level/courseLevels";
    }

    @GetMapping("/{id}")
    public String getOne(@PathVariable Long id, Model model) {

        CourseLevel courseLevel = courseLevelDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("courseLevel", courseLevel);

        return "course-level/courseLevel-detail";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute CourseLevel courseLevel) {
        return "course-level/courseLevel-add";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid CourseLevel courseLevel, BindingResult result) {

        if (result.hasErrors()) {
            return "course-level/courseLevel-add";
        }

        courseLevelDao.save(courseLevel);

        return "redirect:/courseLevels";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {

        CourseLevel courseLevel = courseLevelDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id));
        model.addAttribute("courseLevel", courseLevel);

        return "course-level/courseLevel-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute CourseLevel courseLevel, BindingResult result) {

        if (result.hasErrors()) {
            return "course-level/courseLevel-edit";
        }

        courseLevelDao.save(courseLevel);

        return "redirect:/courseLevels";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        courseLevelDao.delete(
                courseLevelDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id:" + id))
        );

        return "redirect:/courseLevels";
    }
}
