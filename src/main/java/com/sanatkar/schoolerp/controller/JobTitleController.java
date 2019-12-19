package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.JobTitle;
import com.sanatkar.schoolerp.model.repository.RoleDao;
import com.sanatkar.schoolerp.model.repository.JobTitleDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/jobs")
public class JobTitleController {

    private JobTitleDao jobTitleDao;
    private RoleDao roleDao;

    public JobTitleController(JobTitleDao jobTitleDao, RoleDao roleDao) {
        this.jobTitleDao = jobTitleDao;
        this.roleDao = roleDao;
    }

    @GetMapping
    public String getJobs(Model model) {

        model.addAttribute("jobs", jobTitleDao.findAll());

        return "job/jobs";
    }

    @GetMapping("/{id}")
    public String getJob(@PathVariable Long id, Model model) {

        model.addAttribute("job",
                jobTitleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid job id: " + id)));

        return "job/job-detail";
    }

    @GetMapping("/add")
    public String addJob(@ModelAttribute("job") JobTitle job, Model model) {
        model.addAttribute("auths", roleDao.findAll());

        return "job/job-add";
    }

    @PostMapping
    public String createJob(@ModelAttribute @Valid JobTitle job, BindingResult result) {

        if (result.hasErrors()) {
            return "job/job-add";
        }

        jobTitleDao.save(job);

        return "redirect:/jobs";
    }

    @GetMapping("/edit/{id}")
    public String editJob(@PathVariable Long id, Model model) {

        model.addAttribute("job",
                jobTitleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid job id: " + id)));

        return "job/job-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateJob(@PathVariable Long id, @ModelAttribute @Valid JobTitle job, BindingResult result) {

        if (result.hasErrors()) {
            job.setId(id);
            return "job/job-edit";
        }

        jobTitleDao.save(job);

        return "redirect:/jobs";
    }

    @GetMapping("/delete/{id}")
    public String deleteJob(@PathVariable Long id) {

        JobTitle job = jobTitleDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid job id: " + id));
        jobTitleDao.delete(job);

        return "redirect:/jobs";
    }

}
