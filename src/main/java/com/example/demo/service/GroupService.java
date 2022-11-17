package com.example.demo.service;

import com.example.demo.db.Group;
import com.example.demo.db.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    // репозиторий групп
    @Autowired
    private GroupRepository repository;
    // получение списка всех групп
    public List<Group> listAllGroups() {
        return (List<Group>)repository.findAll();
    }
}
