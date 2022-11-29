package com.example.demo.db.repositories;

import com.example.demo.db.entities.Student;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE student_t SET group_id=NULL WHERE group_id=?1", nativeQuery = true)
    int clearGroupInStudentByGroup(int id);

}