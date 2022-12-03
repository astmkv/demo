package com.example.demo.controllers;

import com.example.demo.db.entities.Assessment;
import com.example.demo.db.entities.Student;
import com.example.demo.db.entities.Subject;
import com.example.demo.service.AssessmentService;
import com.example.demo.service.StudentService;
import com.example.demo.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/assessments")
public class AssessmentController {


    @Autowired
    private AssessmentService assessmentService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubService subService;


    @GetMapping("/newAssessment/{id}")
    public String showNewAssessmentForm(@PathVariable("id") Integer id, Model model) {
        Student st = studentService.getStudentById(id);
        Assessment newAssessment = new Assessment();
        newAssessment.setStudent(st);
        model.addAttribute("assessment", newAssessment);
        List<Subject> subsList = subService.listAll();
        model.addAttribute("subsList", subsList);
        model.addAttribute("student",st);
        return "assessments/assessments-form";
    }

    @PostMapping("/newAssessment")
    public String saveNewAssessment(Assessment assessment, RedirectAttributes ra) {
        // 1. сохранение новую оценку в БД
        Assessment saved = assessmentService.saveAssessment(assessment);

        // 2. добавление сообщения о том, что оценка добавлен/не добавлен
        ra.addFlashAttribute("message",
                "Assessment " + saved.getValue() +
                        " save successfully");

        // 3. выполнить перенаправление
        // ВАЖНО: при перенаправлении указывается путь
        return "redirect:/students";
    }

}
