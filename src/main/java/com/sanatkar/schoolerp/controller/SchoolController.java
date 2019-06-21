package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.School;
import com.sanatkar.schoolerp.model.repository.SchoolDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/15
 */
@Controller
@RequestMapping("/schools")
public class SchoolController {

    private SchoolDao schoolDao;

    public SchoolController(SchoolDao schoolDao) {
        this.schoolDao = schoolDao;
    }

    @GetMapping
    public String getSchools(Model model) {

        model.addAttribute("schools", schoolDao.findAll());

        return "school/schools";
    }

    @GetMapping("/{id}")
    public String getSchool(@PathVariable Long id, Model model) {

        School school = schoolDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school id: " + id));
        model.addAttribute("school", school);

        return "school/school-detail";
    }

    @GetMapping("/add")
    public String addSchool(@ModelAttribute School school) {
        return "school/school-add";
    }

    @PostMapping()
    public String createSchool(@ModelAttribute("school") @Valid School school, BindingResult result) {

        if (result.hasErrors()) {
            return "school/school-add";
        }

        schoolDao.save(school);

        return "redirect:/schools";
    }

    @GetMapping("/edit/{id}")
    public String editSchool(@PathVariable Long id, Model model) {

        School school = schoolDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school id: " + id));

        model.addAttribute("school", school);

        return "school/school-update";
    }

    @PostMapping("/update/{id}")
    public String updateSchool(@PathVariable Long id, @ModelAttribute @Valid School school, BindingResult result) {

        if (result.hasErrors()) {
            school.setId(id);
            return "school/school-update";
        }

        schoolDao.save(school);

        return "redirect:/schools";
    }

    @GetMapping("/delete/{id}")
    public String deleteSchool(@PathVariable Long id) {

        School school = schoolDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid school id: " + id));
        schoolDao.delete(school);

        return "redirect:/schools";
    }
}
