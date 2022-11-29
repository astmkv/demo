package com.example.demo.service;

import com.example.demo.db.entities.Student;
import com.example.demo.db.entities.Subject;
import com.example.demo.db.repositories.StudentRepository;
import com.example.demo.db.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubService {
    @Autowired
    private SubjectRepository subRepository;

    public List<Subject> listAll() {
        return (List<Subject>) subRepository.findAll();
    }

    public Subject getSubById(Integer id){
        Optional<Subject> sub = subRepository.findById(id);

        return sub.orElse(null);
    }
}
