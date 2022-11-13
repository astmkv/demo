package com.example.demo.service;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping
    public String showHomePage(){
        // возвращаем представление
        return "index"; // index.html
    }

}
