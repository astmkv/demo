package com.example.demo.service;

import com.example.demo.db.entities.Group;
import com.example.demo.db.entities.Student;
import com.example.demo.db.repositories.StudentRepository;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;
//    private Cache HibernateUitl;

    public List<Student> listAll() {
        return (List<Student>) repository.findAll();
    }

    // метод сохранения студента в БД
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public Student getStudentById(Integer id){
        Optional<Student> student = repository.findById(id);

        return student.orElse(null);
    }
    
    // обновить поля студента
    public Student updateStudent (Student student) {
        return repository.save(student);

    }

    // удалить студента по id
    public void deleteStudentById(Integer id){
        // 1. найти удаляемого студента
        Optional<Student> deleted = repository.findById(id);

        // 2. если такой студент есть, то удалить
        deleted.ifPresent(student -> repository.delete(student));
    }

//    public void udpateGroupIdToNull(Student student, Group group){
//        if (student.getGroup().getId() == group.getId()) {
//            student.setGroup(null);
//        }
//    }
//
//    public void clearGroup(Group group){
//
//    }
}
