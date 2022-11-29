package com.example.demo.controllers;

import com.example.demo.db.entities.Subject;
import com.example.demo.service.SubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SubController {

    @Autowired
    private SubService subService;

    @GetMapping("/subjects")
    public String showAllSubs(Model model) {
        List<Subject> listSubs = subService.listAll();
        model.addAttribute("subsList", listSubs);

        return "subjects/subjects-list";
    }
}
