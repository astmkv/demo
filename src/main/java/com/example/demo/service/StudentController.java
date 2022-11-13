package com.example.demo.service;

import com.example.demo.db.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService service; // сервис студентов

    // обработчик на получение страницы со списком всех студентов
    @GetMapping("/students")
    public String showAllStudents(Model model) {
        List<Student> listStudents = service.listAll();
        model.addAttribute("studentsList", listStudents);
        return "students-list";
    }

    // обработчик на получение формы для добавления студента
    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());

        // ВАЖНО: при возврате представления указывается имя представления
        return "students-form";
    }

    // обработчик для сохранения данных о пользователе
    @PostMapping("/students/new")
    public String saveNewStudent(Student student, RedirectAttributes ra) {
        // 1. сохранение нового студента в БД
        Student saved = service.saveStudent(student);

        // 2. добавление сообщения о том, что студент добавлен/не добавлен
        ra.addFlashAttribute("message",
                "Student " + saved + " save successfully");

        // 3. выполнить перенаправление
        // ВАЖНО: при перенаправлении указывается путь
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteStudentById(id);
        ra.addFlashAttribute("message", "Student delete successfully");
        return "redirect:/students";
    }

//    @GetMapping("/students/update/{id}")
//    public String showUpdateStudentForm(Model model, Student student) {
//        model.addAttribute("student", student);
//
//        // ВАЖНО: при возврате представления указывается имя представления
//        return "students-update-form";
//    }
//
//    @PostMapping("/students/update/{id}")
//    public String updateStudent(@PathVariable("id") Integer id, RedirectAttributes ra) {
//        Student updated = service.updateStudent(id);
//        ra.addFlashAttribute("message",
//                "Student " + updated + " update successfully");
//        return "redirect:/students";
//    }
    @GetMapping("/students/details/{id}")
   public String showDetails(@PathVariable("id") Integer id, RedirectAttributes ra) {
//        service.showDetails(id);
        ra.addFlashAttribute(service.showDetails(id));
        return "redirect:/students-details";
    }
}
