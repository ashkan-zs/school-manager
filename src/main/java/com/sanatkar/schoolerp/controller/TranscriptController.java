package com.sanatkar.schoolerp.controller;

import com.sanatkar.schoolerp.model.entity.Transcript;
import com.sanatkar.schoolerp.model.repository.AcademicYearDao;
import com.sanatkar.schoolerp.model.repository.CourseDao;
import com.sanatkar.schoolerp.model.repository.StudentDao;
import com.sanatkar.schoolerp.model.repository.TranscriptDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Create by ashkan on 2019/06/20
 */
@Controller
@RequestMapping("/transcripts")
public class TranscriptController {

    private TranscriptDao transcriptDao;
    private CourseDao courseDao;
    private AcademicYearDao yearDao;
    private StudentDao studentDao;

    public TranscriptController(TranscriptDao transcriptDao, CourseDao courseDao, AcademicYearDao yearDao, StudentDao studentDao) {
        this.transcriptDao = transcriptDao;
        this.courseDao = courseDao;
        this.yearDao = yearDao;
        this.studentDao = studentDao;
    }

    @GetMapping
    public String getTranscripts(Model model) {

        model.addAttribute("transcripts", transcriptDao.findAll());

        return "transcript/transcripts";
    }

    @GetMapping("/{id}")
    public String getTranscript(@PathVariable Long id, Model model) {

        model.addAttribute("transcript",
                transcriptDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid transcript id: " + id)));

        return "transcript/transcript-detail";
    }

    @GetMapping("/add")
    public String addTranscript(@ModelAttribute("transcript") Transcript transcript, Model model) {

        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("years", yearDao.findAll());

        return "transcript/transcript-add";
    }

    @PostMapping
    public String createTranscript(@ModelAttribute @Valid Transcript transcript, BindingResult result) {

        if (result.hasErrors()) {
            return "transcript/transcript-add";
        }
        transcriptDao.save(transcript);

        return "redirect:/transcripts";
    }

    @GetMapping("/edit/{id}")
    public String editTranscript(@PathVariable Long id, Model model) {

        model.addAttribute("transcript",
                transcriptDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid transcript id: " + id)));
        model.addAttribute("courses", courseDao.findAll());
        model.addAttribute("students", studentDao.findAll());
        model.addAttribute("years", yearDao.findAll());

        return "transcript/transcript-edit";
    }

    @PostMapping("/edit/{id}")
    public String updateTranscript(@PathVariable Long id, @ModelAttribute @Valid Transcript transcript, BindingResult result) {

        if (result.hasErrors()) {
            transcript.setId(id);
            return "transcript/transcript-edit";
        }
        transcriptDao.save(transcript);

        return "redirect:/transcripts";
    }

    @GetMapping("/delete/{id}")
    public String deleteTranscript(@PathVariable Long id) {

        Transcript transcript = transcriptDao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid transcript id: " + id));
        transcriptDao.delete(transcript);
        
        return "redirect:/transcripts";
    }
}
