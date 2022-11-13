package com.example.demo.service;

import com.example.demo.db.Student;
import com.example.demo.db.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

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
}
