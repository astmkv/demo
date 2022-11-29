package com.example.demo.controllers;

import com.example.demo.db.entities.Group;
import com.example.demo.db.repositories.StudentRepository;
import com.example.demo.service.GroupService;
import com.example.demo.service.StudentService;
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
@RequestMapping(value = "/groups")
public class GroupController {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // READ (получить все группы)
    @GetMapping
    public String showAllGroups(Model model) {
        List<Group> listGroups = groupService.listAllGroups();
        model.addAttribute("listGroups", listGroups);
        return "groups-list";
    }
    // CREATE (добавить группу)
    @GetMapping("/newGroup")
    public String showNewGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "groups-form";
    }
    // Обработчик для сохранения группы
    @PostMapping("/newGroup")
    public String saveNewGroup(Group group, RedirectAttributes attrs) {
        // 1. сохраняем новую группу в БД
        Group saved = groupService.saveGroup(group);
        // 2. добавить сообщение о том, что группа добавлена
        attrs.addFlashAttribute("message",
                "Group " + saved + " saved successfully");
        // 3. выполнить перенаправление
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    public String showUpdateGroupForm(@PathVariable("id") Integer id, Model model) {
        Group gr = groupService.getGroupById(id);
        model.addAttribute("group", gr);

        // ВАЖНО: при возврате представления указывается имя представления
        return "groups-update-form";
    }
    //
    @PostMapping("/update")
    public String updateGroup(Group group, RedirectAttributes ra) {
        Group updated = groupService.updateGroup(group);
        ra.addFlashAttribute("message",
                "Groups " + updated + " update successfully");
        return "redirect:/groups";
    }

    @GetMapping("/details/{id}")
    public String showDetailsAboutGroup(@PathVariable("id") Integer id, Model model) {
        Group gr = groupService.getGroupById(id);
        model.addAttribute("group", gr);
        return "groups-details";
    }
    @GetMapping("/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, RedirectAttributes ra) {
        studentRepository.clearGroupInStudentByGroup(id);
        groupService.deleteGroupById(id);
        ra.addFlashAttribute("message", "Group delete successfully");
//        while ()
        return "redirect:/groups";
    }


}