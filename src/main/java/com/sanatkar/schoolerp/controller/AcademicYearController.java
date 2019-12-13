package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.AcademicYear;
import com.sanatkar.schoolerp.model.repository.AcademicYearDao;
import com.sanatkar.schoolerp.model.repository.SchoolDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/years")
public class AcademicYearController {

    private AcademicYearDao yearDao;
    private SchoolDao schoolDao;

    public AcademicYearController(AcademicYearDao yearDao, SchoolDao schoolDao) {
        this.yearDao = yearDao;
        this.schoolDao = schoolDao;
    }

    @GetMapping
    public String getYears(Model model) {

        model.addAttribute("years", yearDao.findAll());

        return "year/years";
    }

    @GetMapping("/{id}")
    public String getYear(@PathVariable Long id, Model model) {

        model.addAttribute("year",
                yearDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid year id: " + id)));

        return "year/year-detail";
    }

    @GetMapping("/add")
    public String addYear(@ModelAttribute("year") AcademicYear year, Model model) {

        model.addAttribute("schools", schoolDao.findAll());

        return "year/year-add";
    }

    @PostMapping
    public String createYear(@ModelAttribute @Valid AcademicYear year, BindingResult result) {

        if (result.hasErrors()) {
            return "year/year-add";
        }
        yearDao.save(year);

        return "redirect:/years";
    }

    @GetMapping("/edit/{id}")
    public String editYear(@PathVariable Long id, Model model) {

        model.addAttribute("year",
                yearDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid year id: " + id)));
        model.addAttribute("schools", schoolDao.findAll());

        return "year/year-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateYear(@PathVariable Long id, BindingResult result, @ModelAttribute @Valid AcademicYear year) {

        if (result.hasErrors()) {
            year.setId(id);
            return "year/year-edit";
        }
        yearDao.save(year);

        return "redirect:/years";
    }

    @GetMapping("/delete/{id}")
    public String deleteYear(@PathVariable Long id) {

        AcademicYear year = yearDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid year id: " + id));
        yearDao.delete(year);

        return "redirect:/years";
    }
}
