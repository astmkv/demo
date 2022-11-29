package com.example.demo.db.repositories;


import com.example.demo.db.entities.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Integer> {
}
