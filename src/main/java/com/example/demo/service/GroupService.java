package com.example.demo.service;

import com.example.demo.db.entities.Group;
import com.example.demo.db.repositories.GroupRepository;
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
    public Group getGroupById(Integer id) {
        Optional<Group> group = groupRepository.findById(id);
        return group.orElse(null);
    }
    public Group updateGroup (Group group) {
        return groupRepository.save(group);
    }

    public void deleteGroupById(Integer id){
        // 1. найти удаляемого студента
        Optional<Group> deletedGroup = groupRepository.findById(id);

        // 2. если такой студент есть, то удалить
        deletedGroup.ifPresent(group -> groupRepository.delete(group));
    }
}
