package com.example.demo.controllers;

import com.example.demo.db.Group;
import com.example.demo.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(value = "/groups")
public class GroupController {

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

}