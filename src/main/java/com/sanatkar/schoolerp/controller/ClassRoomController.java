package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.ClassRoom;
import com.sanatkar.schoolerp.model.repository.ClassRoomDao;
import com.sanatkar.schoolerp.model.repository.SchoolDao;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/classes")
public class ClassRoomController {

    private ClassRoomDao classRoomDao;
    private SchoolDao schoolDao;

    public ClassRoomController(ClassRoomDao classRoomDao, SchoolDao schoolDao) {
        this.classRoomDao = classRoomDao;
        this.schoolDao = schoolDao;
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('SHOW_CLASS')")
    @GetMapping
    public String getClasses(Model model) {

        model.addAttribute("classes", classRoomDao.findAll());

        return "class/classes";
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('SHOW_CLASS')")
    @GetMapping("/{id}")
    public String getClass(@PathVariable Long id, Model model) {

        model.addAttribute("class",
                classRoomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid class id: " + id)));

        return "class/class-detail";
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('ADD_CLASS')")
    @GetMapping("/add")
    public String addClass(@ModelAttribute("classRoom") ClassRoom classRoom, Model model) {

        model.addAttribute("schools", schoolDao.findAll());

        return "class/class-add";
    }

    @PostMapping
    public String createClass(@ModelAttribute @Valid ClassRoom classRoom, BindingResult result) {

        if (result.hasErrors()) {
            return "class/class-add";
        }
        classRoomDao.save(classRoom);

        return "redirect:/classes";
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('EDIT_CLASS')")
    @GetMapping("/edit/{id}")
    public String editClass(@PathVariable Long id, Model model) {

        model.addAttribute("class",
                classRoomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid class id: " + id)));
        model.addAttribute("schools", schoolDao.findAll());


        return "class/class-edit";
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('UPDATE_CLASS')")
    @PostMapping("/edit/{id}")
    public String updateClass(@PathVariable Long id, @ModelAttribute @Valid ClassRoom classRoom, BindingResult result) {

        if (result.hasErrors()) {
            classRoom.setId(id);
            return "class/class-edit";
        }
        classRoomDao.save(classRoom);

        return "redirect:/classes";
    }

    @PreAuthorize("hasRole('ADMIN') or hasAuthority('DELETE_CLASS')")
    @GetMapping("/delete/{id}")
    public String deleteClass(@PathVariable Long id) {

        ClassRoom classRoom = classRoomDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid class id: " + id));
        classRoomDao.delete(classRoom);

        return "redirect:/classes";
    }

}
