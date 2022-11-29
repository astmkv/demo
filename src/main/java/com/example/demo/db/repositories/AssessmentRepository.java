package com.example.demo.db.repositories;

import com.example.demo.db.entities.Assessment;
import org.springframework.data.repository.CrudRepository;

public interface AssessmentRepository extends CrudRepository <Assessment, Integer> {
}
