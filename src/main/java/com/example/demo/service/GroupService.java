package com.example.demo.service;

import com.example.demo.db.Group;
import com.example.demo.db.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    // репозиторий групп
    @Autowired
    private GroupRepository groupRepository;
    // получение списка всех групп
    public List<Group> listAllGroups() {
        return (List<Group>)groupRepository.findAll();
    }
    // сохранить группу в БД
    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    // Получение студента по id
    public Optional<Group> getById(Integer id) {
        return groupRepository.findById(id);
    }

}
