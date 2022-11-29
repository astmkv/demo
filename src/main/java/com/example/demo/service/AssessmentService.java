package com.example.demo.service;

import com.example.demo.db.entities.Assessment;
import com.example.demo.db.entities.Group;
import com.example.demo.db.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssessmentService {

    @Autowired
    private AssessmentRepository assessmentRepository;

    public Assessment saveAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }

    public Assessment updateAssessment(Assessment assessment) {
        return assessmentRepository.save(assessment);
    }
    public void deleteAssessmentById(Integer id){
        Optional<Assessment> deletedAssessment = assessmentRepository.findById(id);

        deletedAssessment.ifPresent(assessment -> assessmentRepository.delete(assessment));
    }

}
