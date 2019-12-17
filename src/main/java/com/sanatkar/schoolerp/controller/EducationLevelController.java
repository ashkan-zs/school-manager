package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.EducationLevel;
import com.sanatkar.schoolerp.model.repository.EducationalLevelDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/levels")
public class EducationLevelController {

    private EducationalLevelDao levelDao;

    public EducationLevelController(EducationalLevelDao levelDao) {
        this.levelDao = levelDao;
    }

    @GetMapping
    public String getLevels(Model model) {

        model.addAttribute("levels", levelDao.findAll());

        return "level/levels";
    }

    @GetMapping("/{id}")
    public String getLevel(@PathVariable Long id, Model model) {

        model.addAttribute("level",
                levelDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid level id: " + id)));

        return "level/level-detail";
    }

    @GetMapping("/add")
    public String addLevel(@ModelAttribute("level") EducationLevel level) {
        return "level/level-add";
    }

    @PostMapping
    public String createLevel(@ModelAttribute @Valid EducationLevel level, BindingResult result) {

        if (result.hasErrors()) {
            return "level/level-add";
        }
        levelDao.save(level);

        return "redirect:/levels";
    }

    @GetMapping("/edit/{id}")
    public String editLevel(@PathVariable Long id, Model model) {

        model.addAttribute("level",
                levelDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid level id: " + id)));

        return "level/level-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateLevel(@PathVariable Long id, @ModelAttribute @Valid EducationLevel level, BindingResult result) {

        if (result.hasErrors()) {
            level.setId(id);
            return "level/level-edit";
        }
        levelDao.save(level);

        return "redirect:/levels";
    }

    @GetMapping("/delete/{id}")
    public String deleteLevel(@PathVariable Long id) {

        EducationLevel level = levelDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid level id: " + id));
        levelDao.delete(level);

        return "redirect:/levels";
    }

}
