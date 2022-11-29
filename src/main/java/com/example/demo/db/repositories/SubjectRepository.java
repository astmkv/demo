package com.example.demo.db.repositories;

import com.example.demo.db.entities.Student;
import com.example.demo.db.entities.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
}
